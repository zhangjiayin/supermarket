package com.linkwee.web.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.linkwee.api.request.crm.InvestCalendarDetailRequest;
import com.linkwee.api.request.crm.InvestCalendarRequest;
import com.linkwee.api.request.crm.InvestCalendarStatisticsRequest;
import com.linkwee.api.request.crm.RePaymentCalendarStatisticsRequest;
import com.linkwee.api.request.crm.RepamentCalendarRequest;
import com.linkwee.api.response.crm.CalendarStatisticsResponse;
import com.linkwee.api.response.crm.InsuranceInvestCalendarDetailResponse;
import com.linkwee.api.response.crm.InsuranceInvestCalendarResponse;
import com.linkwee.api.response.crm.InsuranceInvestCalendarStatisticsResponse;
import com.linkwee.api.response.crm.InvestCalendarDetailResponse;
import com.linkwee.api.response.crm.InvestCalendarResponse;
import com.linkwee.api.response.crm.InvestCalendarStatisticsResponse;
import com.linkwee.api.response.crm.P2pInvestCalendarStatisticsResponse;
import com.linkwee.api.response.crm.RepamentCalendarResponse;
import com.linkwee.api.response.crm.RepaymentCalendarStatisticsResponse;
import com.linkwee.core.base.api.PaginatorResponse;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.core.util.DateUtils;
import com.linkwee.web.dao.CimProductInvestRecordMapper;
import com.linkwee.web.enums.CfpJobGradeEnum;
import com.linkwee.web.model.CimFee;
import com.linkwee.web.model.CimInsuranceFeedetail;
import com.linkwee.web.model.CrmCfpLevelRecord;
import com.linkwee.web.model.acc.ActPersonAddfeeTicketExtends;
import com.linkwee.web.service.ActPersonAddfeeTicketSenduseDetailService;
import com.linkwee.web.service.CimFeeServiceNew;
import com.linkwee.web.service.CimInsuranceFeedetailService;
import com.linkwee.web.service.CimInsuranceNotifyService;
import com.linkwee.web.service.CrmCfpLevelRecordService;
import com.linkwee.web.service.PersonCenterService;
import com.linkwee.xoss.api.AppRequestHead;
import com.linkwee.xoss.helper.JsonWebTokenHepler;

@Service("personCenterService")
public class PersonCenterServiceImpl implements PersonCenterService {
	
	protected  final Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Resource
	private CimProductInvestRecordMapper cimProductInvestRecordMapper;
	@Resource
	private CimFeeServiceNew cimFeeServiceNew;
	@Resource
	private CrmCfpLevelRecordService  crmCfpLevelRecordService;
	@Resource
	private CimInsuranceNotifyService cimInsuranceNotifyService;
	@Resource
	private CimInsuranceFeedetailService cimInsuranceFeedetailService;
	@Resource
	private ActPersonAddfeeTicketSenduseDetailService actPersonAddfeeTicketSenduseDetailService;
	
	@Override
	public PaginatorResponse<RepamentCalendarResponse> queryRepamentCalendarPageList(AppRequestHead appRequestHead,RepamentCalendarRequest repamentCalendarRequest) {
		
		String userId = JsonWebTokenHepler.getUserIdByToken(appRequestHead.getToken());//当前用户
		repamentCalendarRequest.setUserId(userId);
		LOGGER.info("查询回款日历, RepamentCalendarRequest={}", JSONObject.toJSONString(repamentCalendarRequest));
		
		PaginatorResponse<RepamentCalendarResponse> paginatorResponse = new PaginatorResponse<RepamentCalendarResponse>();
		Page<RepamentCalendarResponse> page = new Page<RepamentCalendarResponse>(repamentCalendarRequest.getPageIndex(), repamentCalendarRequest.getPageSize());
		List<RepamentCalendarResponse> productPageListResponses = cimProductInvestRecordMapper.queryRepamentCalendarPageList(repamentCalendarRequest,page);
		paginatorResponse.setDatas(productPageListResponses);
		paginatorResponse.setValuesByPage(page);
		return paginatorResponse;
	}

	@Override
	public PaginatorResponse<InvestCalendarResponse> queryInvestCalendar(AppRequestHead appRequestHead,InvestCalendarRequest investCalendarRequest) {
		
		String userId = JsonWebTokenHepler.getUserIdByToken(appRequestHead.getToken());//当前用户
		investCalendarRequest.setUserId(userId);
		LOGGER.info("查询交易日历, InvestCalendarRequest={}", JSONObject.toJSONString(investCalendarRequest));
		
		PaginatorResponse<InvestCalendarResponse> paginatorResponse = new PaginatorResponse<InvestCalendarResponse>();
		Page<InvestCalendarResponse> page = new Page<InvestCalendarResponse>(investCalendarRequest.getPageIndex(), investCalendarRequest.getPageSize());
		List<InvestCalendarResponse> productPageListResponses = cimProductInvestRecordMapper.queryInvestCalendarPageList(investCalendarRequest,page);
		
		paginatorResponse.setDatas(productPageListResponses);
		paginatorResponse.setValuesByPage(page);
		return paginatorResponse;
	}

	@Override
	public InvestCalendarDetailResponse queryInvestCalendarDetail(AppRequestHead appRequestHead,InvestCalendarDetailRequest investCalendarDetailRequest) {
		
		InvestCalendarDetailResponse investCalendarDetailResponse = null;
		String userId = JsonWebTokenHepler.getUserIdByToken(appRequestHead.getToken());//当前用户
		investCalendarDetailRequest.setUserId(userId);
		LOGGER.info("查询交易详情, InvestCalendarDetailRequest={}", JSONObject.toJSONString(investCalendarDetailRequest));
		
		investCalendarDetailResponse = cimProductInvestRecordMapper.queryInvestCalendarDetail(investCalendarDetailRequest);
		
		if(investCalendarDetailResponse != null){
			String feeRateCalculateMsg = null;//佣金计算  信息头部
			Map<String, String> feeRateCalculateMap = new HashMap<String, String>();//佣金计算  信息 map
			List<String> feeList = new ArrayList<String>();//佣金信息
			/**
			 * 查询该笔订单获利理财师的佣金明细
			 */
			List<CimFee> cimFeeList= cimFeeServiceNew.selectCimFeeByBillIdProfit(investCalendarDetailResponse.getInvestId(), userId);
			Set<String> feeTypeSet =  new HashSet<String>();//获得佣金类型
			
			/**
			 * 封装   佣金信息  佣金计算  信息 map
			 * 客户  拥有佣金 浮动期产品超过了 预估回款时间(每天计算佣金) 特殊显示
			 * 理财师不会拥有1001
			 */			
			if(CollectionUtils.isNotEmpty(cimFeeList)){
				for (CimFee cimFee : cimFeeList) {
					feeRateCalculateMapJudge(investCalendarDetailResponse,cimFee,feeList,feeRateCalculateMap);
					feeTypeSet.add(cimFee.getFeeType());
				}
			}
			
			//封装佣金计算  信息 头部
			if(investCalendarDetailResponse.getUserType() == 0){//客户
				if(investCalendarDetailResponse.getIsFixedDeadline() == 2){//浮动期限
					if(new Date().before(investCalendarDetailResponse.getEndTime())){//是否超过预期到期时间
						feeRateCalculateMsg = "期限按产品最低期限";
					} else {
						feeRateCalculateMsg = "期限按产品持有期";
					}
				}
			} else {//理财师
				//查询该笔佣金当时理财师对应的职级
				CrmCfpLevelRecord crmCfpLevelRecord = crmCfpLevelRecordService.selectCrmCfpLevelRecordByTime(DateUtils.format(investCalendarDetailResponse.getSaleEndTime()));
				if(crmCfpLevelRecord != null && crmCfpLevelRecord.getCurLevelWeight() >= 30){//职级为经理或者总监时才显示佣金计算  信息 头部
					if(!feeTypeSet.contains("1005") && !feeTypeSet.contains("1006")){
						feeRateCalculateMsg = "不享受直推和团队奖励";
					} else {
						feeRateCalculateMsg = "按"+CfpJobGradeEnum.getCfpJobGradeEnumByKey(crmCfpLevelRecord.getCurLevel()).getMsg()+"职级";
					}
				}
			}
			
			
			//填充数据
			investCalendarDetailResponse.setFeeRateCalculateMsg(feeRateCalculateMsg);
			investCalendarDetailResponse.setFeeRateCalculateMap(feeRateCalculateMap);
			investCalendarDetailResponse.setFeeList(feeList);
		} else {
			LOGGER.info("查询交易详情返回为空");
		}
		
		return investCalendarDetailResponse;
	}

	/**
	 * 佣金明细计算
	 * @param investCalendarDetailResponse	产品投资相关信息
	 * @param cimFee     佣金信息
	 * @param feeList  待返回佣金明细
	 * @param feeRateCalculateMap  待返回佣金计算明细
	 * @param deadLineMinValue  产品持有期
	 */
	private void feeRateCalculateMapJudge(InvestCalendarDetailResponse investCalendarDetailResponse,CimFee cimFee,List<String> feeList,Map<String, String> feeRateCalculateMap) {
		
		//格式化
		String investAmt = investCalendarDetailResponse.getInvestAmt().setScale(2,BigDecimal.ROUND_DOWN).toString();
		String productFeeRate = investCalendarDetailResponse.getProductFeeRate().setScale(2,BigDecimal.ROUND_DOWN).toString();
		String feeRate = cimFee.getFeeRate().setScale(2,BigDecimal.ROUND_DOWN).toString();
		String deadLineMinValue = String.valueOf(investCalendarDetailResponse.getDeadLineMinValue());
		if(investCalendarDetailResponse.getIsFixedDeadline() == 2 && new Date().after(investCalendarDetailResponse.getEndTime())){
			deadLineMinValue = "产品持有期";			
		}
		
		//佣金明细
		feeList.add(cimFee.getFeeAmount().setScale(4,BigDecimal.ROUND_DOWN).toString());
		
		//佣金计算明细
		if(cimFee.getFeeType().equals("1001")){
			feeRateCalculateMap.put("1001",investAmt+" * "+deadLineMinValue+" / 360 * "+productFeeRate+"%" );
		} else if(cimFee.getFeeType().equals("1002")){
			feeRateCalculateMap.put("1002",investAmt+" * "+deadLineMinValue+" / 360 * "+productFeeRate+"% * "+feeRate+"%");
		} else if(cimFee.getFeeType().equals("1005")){
			feeRateCalculateMap.put("1005",investAmt+" * "+deadLineMinValue+" / 360 * "+productFeeRate+"% * "+feeRate+"%");
		} else if(cimFee.getFeeType().equals("1006")){
			feeRateCalculateMap.put("1006",investAmt+" * "+deadLineMinValue+" / 360 * "+productFeeRate+"% * "+feeRate+"%");
		} else if(cimFee.getFeeType().equals("1011")){
			feeRateCalculateMap.put("1011",investAmt+" * "+String.valueOf(investCalendarDetailResponse.getDeadLineMinValue())+" / 360 * "+feeRate+"%");
		} else if(cimFee.getFeeType().equals("1012")){
			feeRateCalculateMap.put("1012",investAmt+" * "+String.valueOf(investCalendarDetailResponse.getDeadLineMinValue())+" / 360 * "+feeRate+"%");
		} else if(cimFee.getFeeType().equals("1021")){//个人加佣券	
			ActPersonAddfeeTicketExtends actPersonAddfeeTicketExtends = actPersonAddfeeTicketSenduseDetailService.queryPersonUseAddfeeTicketByInvestId(investCalendarDetailResponse.getInvestId());
			if(actPersonAddfeeTicketExtends != null){				
				feeRateCalculateMap.put("1021",investAmt+" * "+String.valueOf(actPersonAddfeeTicketExtends.getAddFeeDay())+" / 360 * "+feeRate+"%");
			}
		}
	}

	@Override
	public InvestCalendarStatisticsResponse investCalendarStatistics(InvestCalendarStatisticsRequest investCalendarStatisticsRequest) {
		// TODO Auto-generated method stub
		InvestCalendarStatisticsResponse investCalendarStatisticsResponse = new InvestCalendarStatisticsResponse();
		//网贷
		P2pInvestCalendarStatisticsResponse p2pInvestCalendarStatisticsResponse = cimProductInvestRecordMapper.investCalendarStatisticsTotal(investCalendarStatisticsRequest);
		BeanUtils.copyProperties(p2pInvestCalendarStatisticsResponse, investCalendarStatisticsResponse);
		List<CalendarStatisticsResponse> p2pcalendarStatisticsResponseList = cimProductInvestRecordMapper.investCalendarStatisticsNumber(investCalendarStatisticsRequest);
		investCalendarStatisticsResponse.setP2pCalendarStatisticsResponseList(p2pcalendarStatisticsResponseList);
		
		//保险
		InsuranceInvestCalendarStatisticsResponse insuranceInvestCalendarStatisticsResponse = cimInsuranceNotifyService.investCalendarStatisticsTotal(investCalendarStatisticsRequest);
		BeanUtils.copyProperties(insuranceInvestCalendarStatisticsResponse, investCalendarStatisticsResponse);
		List<CalendarStatisticsResponse> insurancecalendarStatisticsResponseList = cimInsuranceNotifyService.investCalendarStatisticsNumber(investCalendarStatisticsRequest);
		investCalendarStatisticsResponse.setInsuranceCalendarStatisticsResponseList(insurancecalendarStatisticsResponseList);
		
		/**
		 * 合并数据交易笔数 = 网贷交易笔数 + 保险交易笔数
		 */
		List<CalendarStatisticsResponse> calendarStatisticsResponseList = new ArrayList<CalendarStatisticsResponse>();
		Map<Date, CalendarStatisticsResponse> calendarStatisticsResponseDateMap =  new HashMap<Date, CalendarStatisticsResponse>();
		for (CalendarStatisticsResponse p2pCalendarStatisticsResponse : p2pcalendarStatisticsResponseList) {
			if(calendarStatisticsResponseDateMap.containsKey(p2pCalendarStatisticsResponse.getCalendarTime())){
				CalendarStatisticsResponse  calendarStatisticsResponseExist = calendarStatisticsResponseDateMap.get(p2pCalendarStatisticsResponse.getCalendarTime());
				calendarStatisticsResponseExist.setCalendarNumber(calendarStatisticsResponseExist.getCalendarNumber() + p2pCalendarStatisticsResponse.getCalendarNumber());
			} else {
				calendarStatisticsResponseDateMap.put(p2pCalendarStatisticsResponse.getCalendarTime(),p2pCalendarStatisticsResponse);
			}
		}
		for (CalendarStatisticsResponse insurancecalendarStatisticsResponse : insurancecalendarStatisticsResponseList) {
			if(calendarStatisticsResponseDateMap.containsKey(insurancecalendarStatisticsResponse.getCalendarTime())){
				CalendarStatisticsResponse  calendarStatisticsResponseExist = calendarStatisticsResponseDateMap.get(insurancecalendarStatisticsResponse.getCalendarTime());
				calendarStatisticsResponseExist.setCalendarNumber(calendarStatisticsResponseExist.getCalendarNumber() + insurancecalendarStatisticsResponse.getCalendarNumber());
			} else {
				calendarStatisticsResponseDateMap.put(insurancecalendarStatisticsResponse.getCalendarTime(),insurancecalendarStatisticsResponse);
			}
		}
		calendarStatisticsResponseList.addAll(calendarStatisticsResponseDateMap.values());
		investCalendarStatisticsResponse.setCalendarStatisticsResponseList(calendarStatisticsResponseList);
		
		return investCalendarStatisticsResponse;
	}

	@Override
	public RepaymentCalendarStatisticsResponse repamentCalendarStatistics(RePaymentCalendarStatisticsRequest rePaymentCalendarStatisticsRequest) {
		//网贷
		RepaymentCalendarStatisticsResponse repaymentCalendarStatisticsResponse = new RepaymentCalendarStatisticsResponse();
		rePaymentCalendarStatisticsRequest.setRepamentType(1);//已回款
		repaymentCalendarStatisticsResponse.setHavaRepaymentAmtTotal(cimProductInvestRecordMapper.repamentCalendarStatisticsTotal(rePaymentCalendarStatisticsRequest));
		rePaymentCalendarStatisticsRequest.setRepamentType(0);//待回款
		repaymentCalendarStatisticsResponse.setWaitRepaymentAmtTotal(cimProductInvestRecordMapper.repamentCalendarStatisticsTotal(rePaymentCalendarStatisticsRequest));;
		List<CalendarStatisticsResponse> calendarStatisticsResponseList = cimProductInvestRecordMapper.repamentCalendarStatisticsNumber(rePaymentCalendarStatisticsRequest);//日历统计数量
		repaymentCalendarStatisticsResponse.setCalendarStatisticsResponseList(calendarStatisticsResponseList);
		return repaymentCalendarStatisticsResponse;
	}

	@Override
	public PaginatorResponse<InsuranceInvestCalendarResponse> queryInsuranceCalendar(AppRequestHead appRequestHead,InvestCalendarRequest investCalendarRequest) {
		String userId = JsonWebTokenHepler.getUserIdByToken(appRequestHead.getToken());
		investCalendarRequest.setUserId(userId);
		LOGGER.info("查询保险交易日历, queryInsuranceCalendar={}", JSONObject.toJSONString(investCalendarRequest));
		
		PaginatorResponse<InsuranceInvestCalendarResponse> paginatorResponse = new PaginatorResponse<InsuranceInvestCalendarResponse>();
		Page<InsuranceInvestCalendarResponse> page = new Page<InsuranceInvestCalendarResponse>(investCalendarRequest.getPageIndex(), investCalendarRequest.getPageSize());
		List<InsuranceInvestCalendarResponse> productPageListResponses = cimInsuranceNotifyService.queryInsuranceInvestCalendarPageList(investCalendarRequest,page);
		
		paginatorResponse.setDatas(productPageListResponses);
		paginatorResponse.setValuesByPage(page);
		return paginatorResponse;
	}

	@Override
	public InsuranceInvestCalendarDetailResponse queryInsuranceInvestCalendarDetail(AppRequestHead appRequestHead,InvestCalendarDetailRequest investCalendarDetailRequest) {
		// TODO Auto-generated method stub
		InsuranceInvestCalendarDetailResponse insuranceInvestCalendarDetailResponse = null;
		String userId = JsonWebTokenHepler.getUserIdByToken(appRequestHead.getToken());//当前用户
		investCalendarDetailRequest.setUserId(userId);
		LOGGER.info("查询保险交易详情, InvestCalendarDetailRequest={}", JSONObject.toJSONString(investCalendarDetailRequest));
		
		insuranceInvestCalendarDetailResponse = cimInsuranceNotifyService.queryInvestCalendarDetail(investCalendarDetailRequest);
		
		if(insuranceInvestCalendarDetailResponse != null){
			Map<String, String> feeRateCalculateMap = new HashMap<String, String>();//佣金计算  信息 map
			List<String> feeList = new ArrayList<String>();//佣金信息
			
			/**
			 * 查询该笔订单获利理财师的佣金明细
			 */
			List<CimInsuranceFeedetail> cimInsuranceFeedetailList= cimInsuranceFeedetailService.selectCimFeeByBillIdProfit(insuranceInvestCalendarDetailResponse.getInvestId(), userId);
			
			/**
			 * 封装   佣金信息  佣金计算  信息 map
			 */
			if(CollectionUtils.isNotEmpty(cimInsuranceFeedetailList)){
				for (CimInsuranceFeedetail cimInsuranceFeedetail : cimInsuranceFeedetailList) {
					insuranceFeeRateCalculateMapJudge(insuranceInvestCalendarDetailResponse,cimInsuranceFeedetail,feeList,feeRateCalculateMap);
				}
			}
			insuranceInvestCalendarDetailResponse.setFeeRateCalculateMap(feeRateCalculateMap);
			insuranceInvestCalendarDetailResponse.setFeeList(feeList);
		} else {
			LOGGER.info("查询保险交易详情返回为空");
		}
		
		return insuranceInvestCalendarDetailResponse;
	}
	
	/**
	 * 保险佣金明细计算
	 * @param investCalendarDetailResponse	产品投资相关信息
	 * @param cimFee     佣金信息
	 * @param feeList  待返回佣金明细
	 * @param feeRateCalculateMap  待返回佣金计算明细
	 */
	private void insuranceFeeRateCalculateMapJudge(InsuranceInvestCalendarDetailResponse insuranceInvestCalendarDetailResponse,CimInsuranceFeedetail cimInsuranceFeedetail,List<String> feeList,Map<String, String> feeRateCalculateMap) {
		
		//格式化
		String investAmt = insuranceInvestCalendarDetailResponse.getInvestAmt().setScale(2,BigDecimal.ROUND_DOWN).toString();
		String productFeeRate = cimInsuranceFeedetail.getProductFeeRate().setScale(2,BigDecimal.ROUND_DOWN).toString();
		String feeRate = cimInsuranceFeedetail.getFeeRate().setScale(2,BigDecimal.ROUND_DOWN).toString();
		
		if(cimInsuranceFeedetail.getFeeAmount().compareTo(BigDecimal.ZERO) > 0){		
			//佣金明细
			feeList.add(cimInsuranceFeedetail.getFeeAmount().setScale(4,BigDecimal.ROUND_DOWN).toString());
			
			//佣金计算明细
			feeRateCalculateMap.put(cimInsuranceFeedetail.getFeeType(),investAmt+" * " + productFeeRate+"% * "+feeRate+"%");
		}
	}

}
