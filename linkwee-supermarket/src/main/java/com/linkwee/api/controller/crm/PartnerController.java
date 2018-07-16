package com.linkwee.api.controller.crm;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.linkwee.api.request.crm.PartnerDetailRequest;
import com.linkwee.api.request.crm.PartnerMonthSaleRequest;
import com.linkwee.api.request.crm.PartnerPageListRequest;
import com.linkwee.api.response.crm.JobGradeVoucherPopup;
import com.linkwee.api.response.crm.JobGradeVoucherResponse;
import com.linkwee.api.response.crm.MonthSaleListNewResponse;
import com.linkwee.api.response.crm.MonthSaleListResponse;
import com.linkwee.api.response.crm.MonthSaleStatisticsResponse;
import com.linkwee.api.response.crm.PartnerDetailResponse;
import com.linkwee.api.response.crm.PartnerJobGradeResponse;
import com.linkwee.api.response.crm.PartnerListResponse;
import com.linkwee.api.response.crm.PartnerSaleRecordNewResponse;
import com.linkwee.api.response.crm.PartnerSaleRecordResponse;
import com.linkwee.api.response.crm.PartnerStatisticsResponse;
import com.linkwee.core.base.PaginatorSevResp;
import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.core.base.api.PaginatorRequest;
import com.linkwee.core.base.api.PaginatorResponse;
import com.linkwee.core.constant.ApiInvokeLogConstant;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.core.util.DateUtils;
import com.linkwee.core.util.StringUtils;
import com.linkwee.web.enums.AppTypeEnum;
import com.linkwee.web.model.SysApiInvokeLog;
import com.linkwee.web.model.crm.JobGradeVoucherPopupResponse;
import com.linkwee.web.model.crm.MonthSaleListResp;
import com.linkwee.web.model.crm.MonthSaleStatisticsResp;
import com.linkwee.web.model.crm.PartnerDetailResp;
import com.linkwee.web.model.crm.PartnerListResp;
import com.linkwee.web.model.crm.PartnerSaleRecordNewResp;
import com.linkwee.web.model.crm.PartnerSaleRecordResp;
import com.linkwee.web.model.crm.PartnerStatisticsResp;
import com.linkwee.web.service.CimLeaderFeeService;
import com.linkwee.web.service.CrmCfpLevelRecordTempService;
import com.linkwee.web.service.CrmCfpPromotionConditionService;
import com.linkwee.web.service.CrmCfplannerService;
import com.linkwee.web.service.CrmInvestorService;
import com.linkwee.web.service.CrmUserInfoService;
import com.linkwee.web.service.PartnerService;
import com.linkwee.web.service.SysApiInvokeLogService;
import com.linkwee.xoss.api.AppRequestHead;
import com.linkwee.xoss.api.BaseController;
import com.linkwee.xoss.helper.JsonWebTokenHepler;
import com.linkwee.xoss.util.AppResponseUtil;
import com.linkwee.xoss.util.AppUtils;

/**
 * 团队
 * @author Mignet
 * @since 2014年5月28日 下午3:54:00
 **/
@Controller
@RequestMapping(value = "/api/personcenter/partner")
public class PartnerController extends BaseController{
	
    @Resource
    private CrmCfplannerService crmCfplannerService;

    @Resource
    private CrmInvestorService crmInvestorService;
    
    @Resource
    private CrmUserInfoService crmUserInfoService;
    
    @Resource
    private PartnerService partnerService;
    
    @Resource
	private SysApiInvokeLogService sysApiInvokeLogService;
    
    @Resource
    private CimLeaderFeeService cimLeaderFeeService;
    
    @Resource
    private CrmCfpLevelRecordTempService crmCfpLevelRecordTempService;
    
    @Resource
    private CrmCfpPromotionConditionService crmCfpPromotionConditionService;
    
    
	/**
	 * 团队信息统计
	 * @param head
	 * @return
	 */
	@RequestMapping("")
	@ResponseBody
	public BaseResponse partner(AppRequestHead head) throws Exception {
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		PartnerStatisticsResp rlt = new PartnerStatisticsResp();
		rlt.setMyRcCount(partnerService.queryMyRcCount(userId));
		rlt.setChildrenRcCount(partnerService.queryChildrenRcCount(userId));
		rlt.setGrandChildrenRcCount(partnerService.queryGrandChildrenRcCount(userId));
		return AppResponseUtil.getSuccessResponse(rlt,PartnerStatisticsResponse.class);
	}
	
	/**
	 * 团队列表
	 * @param head
	 * @return
	 */
	@RequestMapping("pageList")
	@ResponseBody
	public BaseResponse partnerPageList(PartnerPageListRequest req,AppRequestHead head) throws Exception {
		LOGGER.info("团队列表, pageRequest={}", JSONObject.toJSONString(req));
		Integer appType = AppUtils.getAppType(head.getOrgNumber()).getKey();
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		Map<String ,Object> query = new HashMap<String ,Object>();
		query.put("userId", userId);
		if(StringUtils.isNotBlank(req.getName())){
			query.put("userName",req.getName());
		}
		SysApiInvokeLog apiInvokeLog = sysApiInvokeLogService.queryApiInvokeLog(ApiInvokeLogConstant.NAME_PERSONCENTER_PARTNER, userId, appType);
		Date date = null;
		if(apiInvokeLog!=null){
			date = apiInvokeLog.getChgTime();
		}else{
			date = DateUtils.parse("1990-01-01",DateUtils.FORMAT_SHORT);
		}
		query.put("date",date);
		if(req.getSort() != null || req.getOrder() != null){
			query.put("sort",req.getSort());
			query.put("order", req.getOrder());
		}
		Page<PartnerListResp> page = new Page<PartnerListResp>(req.getPageIndex(), req.getPageSize());
		PaginatorSevResp<PartnerListResp> rlt = partnerService.queryPartnerList(query,page);
		sysApiInvokeLogService.updateApiInvokeLog(ApiInvokeLogConstant.NAME_PERSONCENTER_PARTNER, userId,appType);
		return AppResponseUtil.getSuccessResponse(rlt, PartnerListResponse.class);		
	}
	
	/**
	 * 团队成员详情
	 * @param req
	 * @param result
	 * @param head
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("detail")
	@ResponseBody
	public BaseResponse partnerDetail(@Valid PartnerDetailRequest req, BindingResult result, AppRequestHead head) throws Exception {
		if (AppResponseUtil.existsParamsError(result)) {
			return AppResponseUtil.getErrorParams(result);
		}
		PartnerDetailResp rlt = partnerService.queryDetailResp(req.getUserId());
		return AppResponseUtil.getSuccessResponse(rlt,PartnerDetailResponse.class);
	}
	
	
	/**
	 * 团队成员销售记录
	 */
	@RequestMapping("salesRecordList")
	@ResponseBody
	public BaseResponse partnerSalesRecordPageList(@Valid PartnerDetailRequest req,
			BindingResult result, AppRequestHead head) throws Exception {
		if (AppResponseUtil.existsParamsError(result)) {
			return AppResponseUtil.getErrorParams(result);
		}
		Map<String ,Object> query = new HashMap<String ,Object>();
		query.put("userId", req.getUserId());
		query.put("parentId", JsonWebTokenHepler.getUserIdByToken(head.getToken()));
		Page<PartnerSaleRecordResp> page = new Page<PartnerSaleRecordResp>(req.getPageIndex(), req.getPageSize());
		PaginatorSevResp<PartnerSaleRecordResp> rlt = partnerService.queryPartnerSaleRecord(query, page);
		return AppResponseUtil.getSuccessResponse(rlt,PartnerSaleRecordResponse.class);
	}
	
	
	/**
	 * 团队成员销售记录3.0 (奖励明细)
	 */
	@RequestMapping("salesRecordList/3.0")
	@ResponseBody
	public BaseResponse partnerSalesRecordPageListNew(@Valid PartnerDetailRequest req,
			BindingResult result, AppRequestHead head) throws Exception {
		if (AppResponseUtil.existsParamsError(result)) {
			return AppResponseUtil.getErrorParams(result);
		}
		Map<String ,Object> query = new HashMap<String ,Object>();
		query.put("userId", req.getUserId());
		query.put("parentId", JsonWebTokenHepler.getUserIdByToken(head.getToken()));
		Page<PartnerSaleRecordNewResp> page = new Page<PartnerSaleRecordNewResp>(req.getPageIndex(), req.getPageSize());
		PaginatorSevResp<PartnerSaleRecordNewResp> rlt = partnerService.queryPartnerSaleRecordNew(query, page);
		return AppResponseUtil.getSuccessResponse(rlt,PartnerSaleRecordNewResponse.class);
	}
	
	/**
	 * 团队本月销售统计
	 * @param req
	 * @param result
	 * @param head
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("monthSaleStatistics")
	@ResponseBody
	public BaseResponse partnerMonthSaleStatistics(@Valid PartnerMonthSaleRequest req,
			BindingResult result, AppRequestHead head) throws Exception {
		if (AppResponseUtil.existsParamsError(result)) {
			return AppResponseUtil.getErrorParams(result);
		}
		Map<String ,Object> query = new HashMap<String ,Object>();
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		query.put("userId", userId);
		if(req.getDate() != null && req.getDate() != "" && req.getDateType() != null && req.getDateType() != "") {
			/*if(req.getDateType() != null && req.getDateType().equals("3")) {
				SimpleDateFormat format =   new SimpleDateFormat( "yyyy-MM-dd" );
				Date date = format.parse(req.getDate());
				query.put("date", DateUtils.format(date, "yyyy-MM"));
			} else {
				query.put("date", req.getDate());
			}*/
			SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd" );
			Date date = format.parse(req.getDate());
			String ds = format.format( date); 
			req.setDate(ds);
			
			if(req.getDateType().equals("3") && req.getDate().length() > 7) {
				String dateStr = req.getDate().trim().substring(0, 7);
				query.put("date", dateStr);
			} else {
				query.put("date", req.getDate());
			}
			query.put("dateType", req.getDateType());
		} else {
			query.put("date", DateUtils.format(new Date(), "yyyy-MM"));
			query.put("dateType", "3");
		}
		
		MonthSaleStatisticsResp rlt = partnerService.queryPartnerMonthSaleStatistics(query);
		rlt.setDirectAllowance(0.0);
		rlt.setTeamAllowance(0.0);
		/*Double leaderProfit = cimLeaderFeeService.queryMonthProfit(userId);
		rlt.setLeaderProfit(NumberUtils.getFormat(leaderProfit, "0.00"));*/
		return AppResponseUtil.getSuccessResponse(rlt, MonthSaleStatisticsResponse.class);
		
	}
	
	/**
	 * 团队本月销售列表
	 * @param req
	 * @param result
	 * @param head
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("monthSaleList")
	@ResponseBody
	public BaseResponse partnerMonthSaleList(@Valid PartnerMonthSaleRequest req,
			BindingResult result, AppRequestHead head) throws Exception {
		if (AppResponseUtil.existsParamsError(result)) {
			return AppResponseUtil.getErrorParams(result);
		}
		Map<String ,Object> query = new HashMap<String ,Object>();
		query.put("userId", JsonWebTokenHepler.getUserIdByToken(head.getToken()));
		if(req.getDate() != null && req.getDate() != "" && req.getDateType() != null && req.getDateType() != "") {
			SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd" );
			Date date = format.parse(req.getDate());
			String ds = format.format( date); 
			req.setDate(ds);
			
			if(req.getDateType().equals("3") && req.getDate().length() > 7) {
				String dateStr = req.getDate().trim().substring(0, 7);
				query.put("date", dateStr);
			} else {
				query.put("date", req.getDate());
			}
			query.put("dateType", req.getDateType());
		} else {
			query.put("date", DateUtils.format(new Date(), "yyyy-MM"));
			query.put("dateType", "3");
		}
		Page<MonthSaleListResp> page = new Page<MonthSaleListResp>(req.getPageIndex(), req.getPageSize());
		PaginatorSevResp<MonthSaleListResp> rlt = partnerService.queryPartnerMonthSaleList(query, page);
		return AppResponseUtil.getSuccessResponse(rlt, MonthSaleListResponse.class);
	}
	
	/**
	 * 团队本月销售统计3.0
	 * @param req
	 * @param result
	 * @param head
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("monthSaleStatistics/3.0")
	@ResponseBody
	public BaseResponse partnerMonthSaleStatisticsNew(@Valid PartnerMonthSaleRequest req,
			BindingResult result, AppRequestHead head) throws Exception {
		if (AppResponseUtil.existsParamsError(result)) {
			return AppResponseUtil.getErrorParams(result);
		}
		Map<String ,Object> query = new HashMap<String ,Object>();
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		query.put("userId", userId);
		if(req.getDate() != null && req.getDate() != "" && req.getDateType() != null && req.getDateType() != "") {
			SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd" );
			Date date = format.parse(req.getDate());
			String ds = format.format( date); 
			req.setDate(ds);
			
			if(req.getDateType().equals("3") && req.getDate().length() > 7) {
				String dateStr = req.getDate().trim().substring(0, 7);
				query.put("date", dateStr);
			} else {
				query.put("date", req.getDate());
			}
			query.put("dateType", req.getDateType());
		} else {
			query.put("date", DateUtils.format(new Date(), "yyyy-MM"));
			query.put("dateType", "3");
		}
		
		MonthSaleStatisticsResp rlt = partnerService.queryPartnerMonthSaleStatisticsNew(query);
		rlt.setSalesCount(partnerService.queryTeamSalesCount(query));
		rlt.setTotalAmount(partnerService.queryTeamSalesTotalAmount(query));
		return AppResponseUtil.getSuccessResponse(rlt, MonthSaleStatisticsResponse.class);
		
	}
	
	/**
	 * 团队本月销售列表3.0
	 * @param req
	 * @param result
	 * @param head
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("monthSaleList/3.0")
	@ResponseBody
	public BaseResponse partnerMonthSaleListNew(@Valid PartnerMonthSaleRequest req,
			BindingResult result, AppRequestHead head) throws Exception {
		if (AppResponseUtil.existsParamsError(result)) {
			return AppResponseUtil.getErrorParams(result);
		}
		Map<String ,Object> query = new HashMap<String ,Object>();
		query.put("userId", JsonWebTokenHepler.getUserIdByToken(head.getToken()));
		if(req.getDate() != null && req.getDate() != "" && req.getDateType() != null && req.getDateType() != "") {
			SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd" );
			Date date = format.parse(req.getDate());
			String ds = format.format( date); 
			req.setDate(ds);
			
			if(req.getDateType().equals("3") && req.getDate().length() > 7) {
				String dateStr = req.getDate().trim().substring(0, 7);
				query.put("date", dateStr);
			} else {
				query.put("date", req.getDate());
			}
			query.put("dateType", req.getDateType());
		} else {
			query.put("date", DateUtils.format(new Date(), "yyyy-MM"));
			query.put("dateType", "3");
		}
		Page<MonthSaleListResp> page = new Page<MonthSaleListResp>(req.getPageIndex(), req.getPageSize());
		PaginatorSevResp<MonthSaleListResp> rlt = partnerService.queryPartnerMonthSaleListNew(query, page);
		return AppResponseUtil.getSuccessResponse(rlt, MonthSaleListNewResponse.class);
	}
	
	
	/**
	 * 职级特权
	 */
	@RequestMapping("jobGrade")
	@ResponseBody
	public BaseResponse jobGrade(AppRequestHead head) throws Exception {	
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		PartnerJobGradeResponse re = partnerService.jobGrade(userId);
		return AppResponseUtil.getSuccessResponse(re);
	}
	
	/**
	 * 4.5.0职级体验券列表
	 * @param req
	 * @param result
	 * @param head
	 * @return
	 */
	@RequestMapping("/jobGradeVoucherPage")
	@ResponseBody
	public BaseResponse jobGradeVoucherPage(@Valid PaginatorRequest req,BindingResult result,AppRequestHead head) throws Exception {
		if (AppResponseUtil.existsParamsError(result)) {
			return AppResponseUtil.getErrorParams(result);
		}
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		if (AppResponseUtil.existsParamsError(result)) {
			return AppResponseUtil.getErrorParams(result);
		}
		Map<String,Object> conditions = new HashMap<String, Object>();
		conditions.put("userId",userId);
		Page<JobGradeVoucherResponse> page  = new Page<JobGradeVoucherResponse>(req.getPageIndex(),req.getPageSize());
		PaginatorResponse<JobGradeVoucherResponse> datas =  null;
		try {
			datas = partnerService.jobGradeVoucherPage(page,conditions);
		} catch (Exception e) {
			return  new BaseResponse("-1","查询失败");
		}
			
		return AppResponseUtil.getSuccessResponse(datas,JobGradeVoucherResponse.class);
	}
	
	/**
	 * 4.5.0职级体验券弹出框
	 * @param head
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("jobGradeVoucherPopup")
	@ResponseBody
	public BaseResponse jobGradeVoucherPopup(AppRequestHead head) throws Exception {
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());

		JobGradeVoucherPopup rlt = new JobGradeVoucherPopup();
		JobGradeVoucherPopupResponse oucherPopup = partnerService.queryNewJobGradeVoucherPopupDate(userId);
		SysApiInvokeLog apiInvokeLog = sysApiInvokeLogService.queryApiInvokeLog(ApiInvokeLogConstant.NEW_VOUCHER_NO_READ_STATUS, userId, AppTypeEnum.CHANNEL.getKey());
		String lastReaddate = apiInvokeLog!=null ?  DateUtils.format(apiInvokeLog.getChgTime()): DateUtils.format(DateUtils.parse("1990-01-01 00:00:00"));
		//有新的未读消息
		if(oucherPopup!=null&&oucherPopup.getCreateTime()!=null&&DateUtils.compareDate(DateUtils.parse(oucherPopup.getCreateTime(),DateUtils.FORMAT_LONG),DateUtils.parse(lastReaddate,DateUtils.FORMAT_LONG))==1){
			rlt.setHaveNewJobGrade(true);
			rlt.setJobGrade(oucherPopup.getJobGrade());
		}
		//更新职级体验券读取情况
		sysApiInvokeLogService.updateApiInvokeLog(ApiInvokeLogConstant.NEW_VOUCHER_NO_READ_STATUS, userId,AppTypeEnum.CHANNEL.getKey());
		return AppResponseUtil.getSuccessResponse(rlt);
	}
	
	/**
	 * 团队leader奖励-累计奖励
	 */
	/*@RequestMapping("leaderProfit")
	@ResponseBody
	public BaseResponse queryleaderProfit(AppRequestHead head) throws Exception {
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		
		LeaderProfitStatisticsResp rlt = new LeaderProfitStatisticsResp();
		List<CrmCfplanner> directcfp = crmCfplannerService.queryLowerLevelOne(userId);
		boolean boo = cimLeaderFeeService.isFiveAddOneCondition(userId);
		if(boo){
			rlt.setHaveLeader("0");
			rlt.setMonthProfit(cimLeaderFeeService.queryMonthProfit(userId));//本月奖励
			rlt.setContrProfit(cimLeaderFeeService.queryContrProfit(userId));//间接理财师贡献奖励
			rlt.setTotalProfit(cimLeaderFeeService.queryTotalProfit(userId));//累计leader奖励
			rlt.setIndiNumbers(cimLeaderFeeService.queryIndirectCfpNumbers(userId));//间接理财师人数
		}else{
			rlt.setHaveLeader("1");
		}
		rlt.setDirectNumbers(directcfp.size()+"");//直接理财师人数
		return AppResponseUtil.getSuccessResponse(rlt, LeaderProfitStatisticsResponse.class);
		
	}*/
	
	/**
	 * 团队leader奖励-直属理财师团队
	 */
	/*@RequestMapping("directCfpPageList")
	@ResponseBody
	public BaseResponse directCfpPageList(@Valid PaginatorRequest req,BindingResult result,AppRequestHead head) throws Exception {
		if (AppResponseUtil.existsParamsError(result)) {
			return AppResponseUtil.getErrorParams(result);
		}
		Map<String ,Object> query = new HashMap<String ,Object>();
		query.put("userId", JsonWebTokenHepler.getUserIdByToken(head.getToken()));
		Page<CrmCfplanner> page = new Page<CrmCfplanner>(req.getPageIndex(), req.getPageSize());
		
		PaginatorSevResp<CrmCfplanner> rlt = partnerService.queryCfpList(query, page);
		return AppResponseUtil.getSuccessResponse(rlt,DirectCfpPageListResponse.class);
	}*/
	
	
	/**
	 * 团队leader奖励-成员贡献明细
	 */
	/*@RequestMapping("contribuPageList")
	@ResponseBody
	public BaseResponse contribuPageList(@Valid PaginatorRequest req,BindingResult result,AppRequestHead head) throws Exception {
		if (AppResponseUtil.existsParamsError(result)) {
			return AppResponseUtil.getErrorParams(result);
		}
		Map<String ,Object> query = new HashMap<String ,Object>();
		query.put("userId", JsonWebTokenHepler.getUserIdByToken(head.getToken()));
		Page<CimLeaderFee> page = new Page<CimLeaderFee>(req.getPageIndex(), req.getPageSize());
		
		PaginatorSevResp<CimLeaderFee> rlt = cimLeaderFeeService.querycontribuPageList(query, page);
		return AppResponseUtil.getSuccessResponse(rlt,ContribuPageListResponse.class);
	}*/
	
	/**
	 * 判断理财师leader奖励满足状态
	 */
	/*@RequestMapping("leaderProfitStatus")
	@ResponseBody
	public BaseResponse leaderProfitStatus(AppRequestHead head) throws Exception {
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		
		LeaderProfitStatusResponse rlt = new LeaderProfitStatusResponse();
		
		boolean boo = cimLeaderFeeService.isFiveAddOneCondition(userId);
		if(boo){
			rlt.setCfpStatus("0");
			if(cimLeaderFeeService.haveUnderCfpIndependent(userId)){//下级有独立核算
				rlt.setCfpStatus("1");
			}
		}else{
			rlt.setCfpStatus("2");
		}
		return AppResponseUtil.getSuccessResponse(rlt);
		
	}*/
}
