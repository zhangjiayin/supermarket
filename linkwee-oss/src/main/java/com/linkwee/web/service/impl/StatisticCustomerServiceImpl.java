package com.linkwee.web.service.impl;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linkwee.core.base.PaginatorSevReq;
import com.linkwee.core.base.PaginatorSevResp;
import com.linkwee.core.constant.ApiInvokeLogConstant;
import com.linkwee.core.util.DateUtils;
import com.linkwee.core.util.EnumUtils;
import com.linkwee.web.dao.StatisticCustomerDao;
import com.linkwee.web.enums.OrderEnum;
import com.linkwee.web.model.ApiInvokeLog;
import com.linkwee.web.model.SaleUserInfo;
import com.linkwee.web.model.customer.CustomerDetailResp;
import com.linkwee.web.model.customer.CustomerExpireRedeemResp;
import com.linkwee.web.model.customer.CustomerInvestStatisticReq;
import com.linkwee.web.model.customer.CustomerInvestStatisticResp;
import com.linkwee.web.model.customer.CustomerTradelistResp;
import com.linkwee.web.model.customer.LcsCustCountResp;
import com.linkwee.web.model.customer.MycustomersResp;
import com.linkwee.web.model.customer.MycustomersStatisticsResp;
import com.linkwee.web.model.customer.StatisticCustomerReq;
import com.linkwee.web.service.ApiInvokeLogService;
import com.linkwee.web.service.StatisticCustomerService;
import com.linkwee.web.util.PaginatorUtil;
import com.xiaoniu.mybatis.paginator.domain.PageRequest;


/**
 * 
 * @描述： 我的客户服务类
 * 
 * @创建人： Bob
 * 
 * @创建时间：2015年08月08日 17:27:15
 * 
 * Copyright (c) 深圳市小牛科技有限公司-版权所有
 */
@Service("statisticCustomerService")
public class StatisticCustomerServiceImpl implements StatisticCustomerService {

	@Autowired
	private StatisticCustomerDao statisticCustomerDao;
	
	@Autowired
	private ApiInvokeLogService apiInvokeLogService;
	
	/**
	 * 统计理财师客户信息
	 * @param saleUserNumber 理财师用户编码
	 * @param saleCustomerId 理财师客户编码
	 * @param ym 日期
	 * @return
	 */
	public LcsCustCountResp queryLcsCustCountResp(String saleUserNumber, String saleCustomerId, Date now,
			Integer appType) {
		StatisticCustomerReq statisticCustomerReq = new StatisticCustomerReq();
		statisticCustomerReq.setYmd(now);
		statisticCustomerReq.setUserNumber(saleUserNumber);
		statisticCustomerReq.setCustomerId(saleCustomerId);
		LcsCustCountResp rlt = statisticCustomerDao.queryLcsCustCountResp(statisticCustomerReq);
		Integer buytradeCount = queryTradeCountByType(ApiInvokeLogConstant.NAME_CUSTOMER_TRADELIST_SQ,saleUserNumber, saleCustomerId, appType,new Integer[]{2});
		Integer backtradeCount = queryTradeCountByType(ApiInvokeLogConstant.NAME_CUSTOMER_TRADELIST_SH,saleUserNumber, saleCustomerId, appType,new Integer[]{3});
		//
		Date minTime = statisticCustomerDao.queryMinInvestRecordTime(saleUserNumber, saleCustomerId, null);
		try {
			if (null != minTime) {
				minTime = DateUtils.parse(minTime, DateUtils.FORMAT_SHORT);
			} else {
				minTime = DateUtils.parse(new Date(), DateUtils.FORMAT_SHORT);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		rlt.setMinTime(minTime);
		rlt.setBuytradeCount(buytradeCount);
		rlt.setBacktradeCount(backtradeCount);
		Integer custmersNum = queryCustomerNum(saleUserNumber, saleCustomerId, appType);
		rlt.setCustomerCount(custmersNum);
		rlt.setInterlocutionCount(0);// 互动问答默认为0
		return rlt;
	}
	
	/**
	 * 查询未读交易次数
	 * @param saleUserNumber 理财师编码
	 * @param saleCustomerId 理财师用户id
	 * @return
	 */
	public Integer queryTradeCount(String saleUserNumber,String saleCustomerId,Integer appType){
		StatisticCustomerReq statisticCustomerReq = new StatisticCustomerReq();
		ApiInvokeLog apiInvokeLog = apiInvokeLogService.queryApiInvokeLog(ApiInvokeLogConstant.NAME_CUSTOMER_TRADELIST, saleCustomerId,appType);
		if(apiInvokeLog!=null){
			statisticCustomerReq.setTime(apiInvokeLog.getChgTime());
		}
		statisticCustomerReq.setUserNumber(saleUserNumber);
		return statisticCustomerDao.queryTradeCount(statisticCustomerReq);
	}
	
	/**
	 * 根据交易类别查询未读交易次数
	 * @Auther xuzhao
	 * @Date 2016年1月28日 下午5:26:50
	 * @param apiName
	 * @param saleUserNumber
	 * @param saleCustomerId
	 * @param appType
	 * @return
	 */
	public Integer queryTradeCountByType(String apiName,String saleUserNumber,String saleCustomerId,Integer appType,Integer[] types){
		StatisticCustomerReq statisticCustomerReq = new StatisticCustomerReq();
		ApiInvokeLog apiInvokeLog = apiInvokeLogService.queryApiInvokeLog(apiName, saleCustomerId,appType);
		if(apiInvokeLog!=null){
			statisticCustomerReq.setTime(apiInvokeLog.getChgTime());
		}
		apiInvokeLogService.updateApiInvokeLog(apiName, saleCustomerId, appType);
		statisticCustomerReq.setUserNumber(saleUserNumber);
		statisticCustomerReq.setTypes(types);
		return statisticCustomerDao.queryTradeCountByType(statisticCustomerReq);
	}
	
	/**
	 * 查询新增客户数
	 * @Auther xuzhao
	 * @Date 2016年1月21日 下午3:43:24
	 * @param saleUserNumber 理财师编码
	 * @param saleCustomerId 理财师用户id
	 * @param appType
	 * @return
	 */
	public Integer queryCustomerNum(String saleUserNumber,String saleCustomerId,Integer appType){
		StatisticCustomerReq statisticCustomerReq = new StatisticCustomerReq();
		ApiInvokeLog apiInvokeLog = apiInvokeLogService.queryApiInvokeLog(ApiInvokeLogConstant.NAME_CUSTOMER_MYCUSTOMERS_PAGELIST, saleCustomerId,appType);
		if(apiInvokeLog!=null){
			statisticCustomerReq.setTime(apiInvokeLog.getChgTime());
		}
		statisticCustomerReq.setUserNumber(saleUserNumber);
		return statisticCustomerDao.queryCustomerNum(statisticCustomerReq);
	}
	/**
	 * 客户列表
	 * @param request
	 * @return
	 */
	public PaginatorSevResp<MycustomersResp> queryMycustomers(PaginatorSevReq request){
		PageRequest req = PaginatorUtil.toPageRequest(request);
		int totalCount = statisticCustomerDao.queryMycustomersCount(req.getQuery());
		if(totalCount==0){
			return PaginatorUtil.getEmptyResp(req);
		}else{
			req.setContainsTotalCount(false);
			if(request.getSort()==null||request.getOrder()==null){
				req.setSort(QmSortFieldEnum.REGISTER_TIME.getValue());
				req.setOrder(OrderEnum.DESC.getValue());
			}
			if(request.getSort()!=null&&request.getOrder()!=null){
				req.setSort(EnumUtils.getValueByKey(request.getSort(), QmSortFieldEnum.values()));
				req.setOrder(EnumUtils.getValueByKey(request.getOrder(), OrderEnum.values()));
				if(QmSortFieldEnum.NEAR_END_DATE.getKey()==request.getSort().intValue()){
					if(OrderEnum.ASC.getKey()==request.getOrder().intValue()){
						req.setOrderExpr("IFNULL(?,STR_TO_DATE('9999-01-01','%Y-%m-%d')) "+OrderEnum.ASC.getValue()+",customerMobile ");
					}else{
						req.setOrderExpr("IFNULL(?,STR_TO_DATE('1990-01-01','%Y-%m-%d')) "+OrderEnum.ASC.getValue()+",customerMobile ");
					}
				}else{
					req.setOrderExpr("? "+EnumUtils.getValueByKey(request.getOrder(), OrderEnum.values())+",customerMobile ");
				}
			}
			return PaginatorUtil.getPaginatorSevResp(req,statisticCustomerDao.queryMycustomers(req),totalCount);
		}
	}
	

	/**
	 * 交易动态
	 * @param request
	 * @return
	 */
	@Override
	public PaginatorSevResp<CustomerTradelistResp> queryCustomerTradelistResp(
			PaginatorSevReq request) {
		PageRequest req = PaginatorUtil.toPageRequest(request);
		int totalCount = statisticCustomerDao.queryCustomerTradelistCount(req.getQuery());
		if(totalCount==0){
			return PaginatorUtil.getEmptyResp(req);
		}else{
			req.setContainsTotalCount(false);
			if(!(request.getSort()!=null&&request.getOrder()!=null)){
				req.setSort("time");
				req.setOrder(OrderEnum.DESC.getValue());
			}
			return PaginatorUtil.getPaginatorSevResp(req,statisticCustomerDao.queryCustomerTradelistResp(req),totalCount);
		}
	}

	/**
	 * 客户详情
	 * @param customerId
	 * @return
	 */
	@Override
	public CustomerDetailResp queryCustomerDetailResp(String saleUserNumber,String saleCustomerId,String customerId) {
		CustomerDetailResp resp = statisticCustomerDao.queryCustomerDetailResp(saleUserNumber,customerId);
		Date minTime = statisticCustomerDao.queryMinInvestRecordTime(saleUserNumber,saleCustomerId, null);
		if(resp != null){
			resp.setMinTime(minTime);
		}else{
			resp = new CustomerDetailResp();
		}
		return resp;
	}

	 /**
	  * 查询理财师投资统计
	  * @Auther xuzhao
	  * @Date 2016年1月21日 下午5:06:43
	  * @param number
	  * @param customerId
	  * @param time
	  * @param timeType
	  * @return
	  */
	@Override
	public CustomerInvestStatisticResp queryCustomerInvestStatistic(String saleUserNumber, String saleCustomerId,
			String time, int timeType) {
		CustomerInvestStatisticReq req = new CustomerInvestStatisticReq();
		req.setCustomerId(saleCustomerId);
		req.setUserNumber(saleUserNumber);
		req.setTimeType(timeType);
		Date timeDate= DateUtils.parse(time, DateUtils.FORMAT_SHORT);
		int year = Integer.parseInt(DateUtils.getYear(timeDate));
		if(timeType != 5){
			req.setEndTime(getEndTime(timeDate, year, timeType));
			req.setStartTime(timeDate);
		}else{
			req.setEndTime(null);
			req.setStartTime(null);
		}
		//查询投资统计信息
		CustomerInvestStatisticResp rlt = statisticCustomerDao.queryCustomerInvestStatistic(req);
		return rlt;
	}
	
	/**
	 * 查询时间段截止日期
	 * @Auther xuzhao
	 * @Date 2016年1月23日 上午10:45:35
	 * @param timeDate
	 * @param year
	 * @param timeType
	 * @return
	 */
	public Date getEndTime(Date timeDate, int year, int timeType) {
		Date endTime = null;
		switch (timeType) {
		case 1:// 年
			endTime = DateUtils.getLastDayOfMonth(year, 12);
			break;
		case 2:// 季度，设置截止日期
			endTime = DateUtils.getLastDayOfQuarter(timeDate);
			break;
		case 3:// 月
			endTime = DateUtils.getLastDayOfMonth(timeDate);
			break;

		case 4:// 日
			endTime = timeDate;
			break;
		case 5:
			break;
		default:
			break;
		}
		return endTime;
	}

	/**
	 * 查询理财师端  投资统计-分页
	 * @Auther xuzhao
	 * @Date 2016年1月23日 上午10:38:36
	 * @param pageRequest
	 * @return
	 */
	public PaginatorSevResp<CustomerTradelistResp> customerInvestStatisticPageList(
			PaginatorSevReq pageRequest) {
		PageRequest req = PaginatorUtil.toPageRequest(pageRequest);
		int totalCount = statisticCustomerDao.queryCustomerInvestStatisticListCount(req.getQuery());
		if(totalCount==0){
			return PaginatorUtil.getEmptyResp(req);
		}else{
			req.setContainsTotalCount(false);
			List<CustomerTradelistResp> pageList = statisticCustomerDao.queryCustomerInvestStatisticListResp(req);
			return PaginatorUtil.getPaginatorSevResp(req,pageList,totalCount);
		}
	}

	/**
	 *  客户列表-累计
	 * @Auther xuzhao
	 * @Date 2016年1月23日 下午5:08:28
	 * @param number
	 * @return
	 */
	public MycustomersStatisticsResp queryMycustomersStatisticsResp(SaleUserInfo userInfo) {
		MycustomersStatisticsResp bo = new MycustomersStatisticsResp();
		int registerCustomer =statisticCustomerDao.queryRegisterMycustomers(userInfo.getNumber(), userInfo.getCustomerId());
		int investCustomerCount = statisticCustomerDao.queryInvestMycustomers(userInfo.getNumber(), userInfo.getCustomerId());
		bo.setInvestCustomer(investCustomerCount);
		bo.setRegCustomer(registerCustomer);
		return bo;
	}

	/**
	 * 客户详情-分页
	 * @Auther xuzhao
	 * @Date 2016年1月27日 上午11:45:39
	 * @param pageRequest
	 * @return
	 */
	public PaginatorSevResp<CustomerTradelistResp> customerDetailPageList(PaginatorSevReq pageRequest) {
		Map<String, Object> conditionMap = pageRequest.getQueryConditions();
		if(conditionMap.get("type").equals("1")){//交易记录
			pageRequest.getQueryConditions().put("types",new Integer[]{2,3});
			return queryCustomerTradelistResp(pageRequest);
		}else{//到期日程
			PageRequest req = PaginatorUtil.toPageRequest(pageRequest);
			pageRequest.getQueryConditions().put("types",new Integer[]{2});
			int totalCount = statisticCustomerDao.querycustomerDetailPageListCount(req.getQuery());
			if(totalCount==0){
				return PaginatorUtil.getEmptyResp(req);
			}else{
				req.setContainsTotalCount(false);
				if(!(pageRequest.getSort()!=null&&pageRequest.getOrder()!=null)){
					req.setSort("VALID_END_DATE");
					req.setOrder(OrderEnum.ASC.getValue());
				}
				return PaginatorUtil.getPaginatorSevResp(req,statisticCustomerDao.queryquerycustomerDetailPageListResp(req),totalCount);
			}
		}
	}
	

}
