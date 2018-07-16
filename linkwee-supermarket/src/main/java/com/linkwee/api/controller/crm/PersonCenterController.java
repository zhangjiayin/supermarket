package com.linkwee.api.controller.crm;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.linkwee.act.redpacket.service.ActRedpacketService;
import com.linkwee.act.redpacket.service.RedPacketService;
import com.linkwee.api.request.act.RedpacketRequest;
import com.linkwee.api.request.cim.PlatformBounsRequest;
import com.linkwee.api.request.crm.DirectCfpJobGrade;
import com.linkwee.api.request.crm.GoodTransRequest;
import com.linkwee.api.request.crm.IconRequest;
import com.linkwee.api.request.crm.InvestCalendarDetailRequest;
import com.linkwee.api.request.crm.InvestCalendarRequest;
import com.linkwee.api.request.crm.InvestCalendarStatisticsRequest;
import com.linkwee.api.request.crm.MyCustomerInvestRecordRequest;
import com.linkwee.api.request.crm.RePaymentCalendarStatisticsRequest;
import com.linkwee.api.request.crm.RecomProductOrgRequest;
import com.linkwee.api.request.crm.RepamentCalendarRequest;
import com.linkwee.api.request.crm.UserTypeRequest;
import com.linkwee.api.request.funds.ifast.GetInvestorHoldingsRequest;
import com.linkwee.api.response.AccountBookStatisticResponse;
import com.linkwee.api.response.cim.ProductDetailResponse;
import com.linkwee.api.response.crm.CfplannerMemberDetailResponse;
import com.linkwee.api.response.crm.CfplannerMemberResponse;
import com.linkwee.api.response.crm.CfplannerPersonFund;
import com.linkwee.api.response.crm.CustomerCfpmember;
import com.linkwee.api.response.crm.CustomerCfpmemberPageResponse;
import com.linkwee.api.response.crm.CustomerMemberDetailResponse;
import com.linkwee.api.response.crm.CustomerMemberInvestRecordResponse;
import com.linkwee.api.response.crm.GoodTransHaveRead;
import com.linkwee.api.response.crm.GoodTransPageListResponse;
import com.linkwee.api.response.crm.InsuranceInvestCalendarDetailResponse;
import com.linkwee.api.response.crm.InsuranceInvestCalendarResponse;
import com.linkwee.api.response.crm.InvestCalendarDetailResponse;
import com.linkwee.api.response.crm.InvestCalendarResponse;
import com.linkwee.api.response.crm.InvestCalendarStatisticsResponse;
import com.linkwee.api.response.crm.InvestorPersonCenterResponse;
import com.linkwee.api.response.crm.PartnerJobGradeResponse;
import com.linkwee.api.response.crm.PersonCenterResponse;
import com.linkwee.api.response.crm.RepamentCalendarResponse;
import com.linkwee.api.response.crm.RepaymentCalendarStatisticsResponse;
import com.linkwee.api.response.tc.GoodTransResponse;
import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.core.base.api.ErrorResponse;
import com.linkwee.core.base.api.PaginatorRequest;
import com.linkwee.core.base.api.PaginatorResponse;
import com.linkwee.core.constant.ApiInvokeLogConstant;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.core.util.NumberUtils;
import com.linkwee.core.util.StringUtils;
import com.linkwee.tc.fee.service.TCFeeDetailService;
import com.linkwee.web.enums.AppTypeEnum;
import com.linkwee.web.enums.CfpJobGradeEnum;
import com.linkwee.web.enums.PlatformEnum;
import com.linkwee.web.enums.SmsTypeEnum;
import com.linkwee.web.model.CrmCfplanner;
import com.linkwee.web.model.CrmInvestor;
import com.linkwee.web.model.SysApiInvokeLog;
import com.linkwee.web.model.acc.AcAccountBind;
import com.linkwee.web.model.cim.OrgInfo;
import com.linkwee.web.model.crm.CfplannerInvestorPersonResp;
import com.linkwee.web.model.crm.CfplannerPersonCenterResp;
import com.linkwee.web.model.crm.GoodTransResp;
import com.linkwee.web.model.crm.InvestorPersonCenterResp;
import com.linkwee.web.model.crm.OrgSimpleResp;
import com.linkwee.web.model.crm.PersonCenterResp;
import com.linkwee.web.response.CfpLevelWarningResp;
import com.linkwee.web.service.AcAccountBindService;
import com.linkwee.web.service.ActAddFeeCouponService;
import com.linkwee.web.service.ActJobGradeVoucherService;
import com.linkwee.web.service.ActPersonAddfeeTicketService;
import com.linkwee.web.service.CimFundOrderService;
import com.linkwee.web.service.CimOrginfoService;
import com.linkwee.web.service.CimProductInvestRecordService;
import com.linkwee.web.service.CimProductService;
import com.linkwee.web.service.CimProductUnrecordInvestService;
import com.linkwee.web.service.CrmCfpLevelRecordTempService;
import com.linkwee.web.service.CrmCfpNewcomerTaskService;
import com.linkwee.web.service.CrmCfplannerService;
import com.linkwee.web.service.CrmInvestorService;
import com.linkwee.web.service.CrmOrgAcctRelService;
import com.linkwee.web.service.CrmUserAccountBookService;
import com.linkwee.web.service.CrmUserInfoService;
import com.linkwee.web.service.CustomerService;
import com.linkwee.web.service.PartnerService;
import com.linkwee.web.service.PersonCenterService;
import com.linkwee.web.service.SysApiInvokeLogService;
import com.linkwee.web.service.SysConfigService;
import com.linkwee.web.service.SysGrayReleaseService;
import com.linkwee.web.service.SysMsgService;
import com.linkwee.xoss.api.AppRequestHead;
import com.linkwee.xoss.api.BaseController;
import com.linkwee.xoss.funds.sdk.ifast.model.HoldingsStatistic;
import com.linkwee.xoss.helper.DateUtils;
import com.linkwee.xoss.helper.JsonWebTokenHepler;
import com.linkwee.xoss.helper.PushMessageHelper;
import com.linkwee.xoss.util.AppResponseUtil;
import com.linkwee.xoss.util.AppUtils;
import com.linkwee.xoss.util.OpenResponseUtil;
import com.linkwee.xoss.util.RequestLogging;
import com.linkwee.xoss.util.WebUtil;

/**
 * 个人中心
 * @author Mignet
 * @since 2014年5月28日 下午3:54:00
 **/
@Controller
@RequestMapping(value = "/api/personcenter")
public class PersonCenterController extends BaseController{
	
	private String errorCode = "-1";
	
    @Resource
    private CrmCfplannerService crmCfplannerService;
    @Resource
    private CrmInvestorService crmInvestorService;
    @Resource
    private CrmUserInfoService crmUserInfoService;
    @Resource
    private CimProductInvestRecordService investRecordService;
    @Resource
    private PartnerService partnerService;
    @Resource
	private SysApiInvokeLogService sysApiInvokeLogService;
    @Resource
	private SysMsgService msgService;
    @Resource 
    private TCFeeDetailService feeDetailService ;
    @Resource
	private CimOrginfoService cimOrginfoService;
    @Resource
    private RedPacketService redPacketService;
    @Resource
	private AcAccountBindService accountbindService; 
    @Resource
	private CimProductUnrecordInvestService cimProductUnrecordInvestService;
    @Resource
	private CrmCfpNewcomerTaskService crmCfpNewcomerTaskService;
    @Resource
	private ActRedpacketService actRedpacketService;
    @Resource
    private PersonCenterService personCenterService;
    @Resource
    private CustomerService customerService;
    @Resource
	private SysConfigService sysConfigService;
    @Resource
	private CrmUserAccountBookService crmUserAccountBookService;
    @Resource
	private CrmCfpLevelRecordTempService crmCfpLevelRecordTempService;
    @Resource
	private CimFundOrderService cimFundOrderService;
	@Resource
	private ActAddFeeCouponService addFeeCouponService;
	@Resource
	private ActJobGradeVoucherService actJobGradeVoucherService;
	@Resource
	private CrmOrgAcctRelService crmOrgAcctRelService;
	@Resource
	private SysGrayReleaseService sysGrayReleaseService; //灰度服务
	@Resource
	private CimOrginfoService cimOrgInfoService; 
	@Autowired
	private PushMessageHelper pushMessageHelper;
	@Resource
	private CimProductService cimProductService;
	@Resource
	private ActPersonAddfeeTicketService actPersonAddfeeTicketService;
    
    /**
	 * 个人中心-首页
	 * @param head
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("homepage")
	@ResponseBody
	public BaseResponse homepage(AppRequestHead head) throws Exception {
		Integer appType = AppUtils.getAppType(head.getOrgNumber()).getKey();
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		LOGGER.debug("个人中心首页信息: userId = " + userId + "; appType = " + appType);
		Map<String,Object> conditions = Maps.newHashMap();//未读消息查询条件
		conditions.put("userId", userId);
        conditions.put("appType",appType);
        //限制读取注册后发布的公告信息
        //regTime 注册的投资人和理财师只能看到自己注册之后的系统公告
        if(StringUtils.isNotBlank(userId) && !userId.equals("undefined")){
        	CrmInvestor crmInvestor = crmInvestorService.queryInvestorByUserId(userId);
        	if(crmInvestor!= null && crmInvestor.getCreateTime() != null ){
        		conditions.put("regTime", DateUtils.format(crmInvestor.getCreateTime(), DateUtils.FORMAT_LONG));
        	}
        }
		if(AppUtils.isChannelApp(head.getOrgNumber())){
			CrmCfplanner cfplanner = crmCfplannerService.queryCfplannerByUserId(userId);
			SysApiInvokeLog apiInvokeLog = sysApiInvokeLogService.queryApiInvokeLog(ApiInvokeLogConstant.NAME_PERSONCENTER_PARTNER,userId,appType);
			Date date = null;
			if(apiInvokeLog!=null){
				date = apiInvokeLog.getChgTime();
			}
			PersonCenterResp rlt = new PersonCenterResp();
			rlt.setUserName(cfplanner.getUserName());
			rlt.setMobile(cfplanner.getMobile());
			rlt.setIsBindBankCard(accountbindService.isbindBankcard(userId));//是否已绑定银行卡
			rlt.setHeadImage(cfplanner.getHeadImage());
			rlt.setLevelExperience(cfplanner.getLevelExperience());
			
			//V4.0新加
			rlt.setOnInvestNotReadMsg(investRecordService.queryNotReadRecord(userId,0));//在投未读投资记录数目
			rlt.setOverdueInvestNotReadMsg(investRecordService.queryNotReadRecord(userId,1));//已过期未读投资记录数目
			
			RedpacketRequest redpacketRequest = new RedpacketRequest();
			redpacketRequest.setUserId(userId);
			redpacketRequest.setType(2);//派发
			rlt.setSendNum(redPacketService.queryRedPacketCount4(redpacketRequest));//派发红包数量
			redpacketRequest.setType(1);//投资
			rlt.setInvestNum(redPacketService.queryRedPacketCount4(redpacketRequest));//投资红包数量
			
			rlt.setAccountBalance(accountbindService.queryAccountBalance(userId));//账户余额
			if(cfplanner.getJobGrade() != null) {
				//rlt.setCfgLevelName(CfpLevelEnum.valueOf(cfplanner.getCfpLevel()).getMsg());
				rlt.setCfgLevelName(CfpJobGradeEnum.valueOf(cfplanner.getJobGrade()).getMsg());
			}
			PersonCenterResp p = feeDetailService.queryCfplannerMonthProfitTotal(userId);
			rlt.setFeeProfit(new BigDecimal(p.getFeeProfit()).setScale(2, BigDecimal.ROUND_DOWN).doubleValue());//本月佣金收益
			rlt.setRecommendProfit(new BigDecimal(p.getRecommendProfit()).setScale(2, BigDecimal.ROUND_DOWN).doubleValue());//本月推荐收益
//			rlt.setMonthProfit(new BigDecimal( p.getMonthProfit()).setScale(2, BigDecimal.ROUND_DOWN).doubleValue());//本月总收益
//			BigDecimal mothProfit = feeDetailService.queryMothProfit(userId);
//			Double activityReward = accountbindService.queryCfpActivityRewardNew(userId, DateUtils.format(new Date(),DateUtils.FORMAT_MONTH));
			
//			rlt.setMonthProfit(mothProfit.add(new BigDecimal(activityReward)).doubleValue());//本月总收益
			rlt.setMonthProfit(NumberUtils.getFormat(investRecordService.queryMonthIncome(DateUtils.format(new Date(),DateUtils.FORMAT_MONTH),userId), "0.00"));//本月总收益
			rlt.setTodayProfit(p.getTodayProfit());//今日收益
			rlt.setHistoryProfit(p.getHistoryProfit());//累计收益
			rlt.setHongbaoCount(redPacketService.queryCfplannerRedPacketCount(userId));//可用红包数量
			//未读消息数量
			int bulletinMsgCount = msgService.queryUnreadBulletinCount(conditions);
			int personMsgCount = msgService.queryUnreadLcsCount(userId, AppTypeEnum.CHANNEL.getKey());
			rlt.setMsgCount(bulletinMsgCount + personMsgCount);
			rlt.setBulletinMsgCount(bulletinMsgCount);
			rlt.setPersonMsgCount(personMsgCount);
			rlt.setNewPartnerCount(partnerService.queryNewPartnerCount(userId, date));//新团队成员数量
			rlt.setWithdrawAmount(accountbindService.queryWithdrawingAmount(userId));//提现中金额
			rlt.setUnRecordInvestCount(cimProductUnrecordInvestService.getCfplannerUnrecordInvestCount(userId));//保单记录数
			rlt.setUnFinishNewcomerTaskCount(crmCfpNewcomerTaskService.queryUnFinishNewcomerTaskCount(userId));//未完成新手任务数量
			return AppResponseUtil.getSuccessResponse(rlt,PersonCenterResponse.class);
		} else {
			CrmInvestor crmInvestor = crmInvestorService.queryInvestorByUserId(userId);
			InvestorPersonCenterResp rlt = new InvestorPersonCenterResp();
			rlt.setUserName(crmInvestor.getUserName());
			rlt.setMobile(crmInvestor.getMobile());
			rlt.setHeadImage(crmInvestor.getHeadImage());
			rlt.setIsBindBankCard(accountbindService.isbindBankcard(userId));//是否已绑定银行卡
			
			rlt.setAccountBalance(accountbindService.queryAccountBalance(userId));//账户余额
			rlt.setHongbaoCount(redPacketService.queryInvestorRedPacketCount(userId));//可用红包数量
			rlt.setInvestAmount(investRecordService.queryCustomerInvestTotalAmount(userId));//投资总额
			//未读消息数量
			if(appType != null && appType.intValue() == AppTypeEnum.INVESTOR.getKey()){
		        conditions.put("platform", AppUtils.getPlatform(head.getOrgNumber()).getKey());
		    }
			PlatformEnum platform =  WebUtil.getPlatform(head.getOrgNumber());
			if(PlatformEnum.WEB.equals(platform)){
				rlt.setMsgCount(msgService.queryUnreadLcsCount(userId, AppTypeEnum.INVESTOR.getKey()));
			} else {
				rlt.setMsgCount(msgService.queryUnreadBulletinCount(conditions) + msgService.queryUnreadLcsCount(userId, AppTypeEnum.INVESTOR.getKey()));
			}
			rlt.setTotalProfit(investRecordService.queryCustomerInvestTotalProfit(userId));//总收益
			rlt.setWithdrawAmount(accountbindService.queryWithdrawingAmount(userId));//提现中金额
			rlt.setOrgAccountCount(cimOrginfoService.queryOrgAccountCount(userId));//绑定机构帐号数
			rlt.setUnBindOrgAccountCount(cimOrginfoService.queryOrgCount() - rlt.getOrgAccountCount());//未绑定机构数
			return AppResponseUtil.getSuccessResponse(rlt,InvestorPersonCenterResponse.class);
		}
		
		
	}
	
	/**
	 * 上传头像
	 * 
	 * @param head
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/icon")
	@ResponseBody
	public BaseResponse uploadIcon(@Valid IconRequest iconRequest,AppRequestHead appRequestHead) throws Exception {
		
		String userId = JsonWebTokenHepler.getUserIdByToken(appRequestHead.getToken());
		String headImage = iconRequest.getImage();
		if(AppUtils.isChannelApp(appRequestHead.getOrgNumber())){
			CrmCfplanner crmCfplanner = new CrmCfplanner();
			crmCfplanner.setUserId(userId);
			crmCfplanner.setHeadImage(headImage);
			crmCfplannerService.updateByUserId(crmCfplanner);
		}
		else if(AppUtils.isInvestorApp(appRequestHead.getOrgNumber())){
			CrmInvestor crmInvestor = new CrmInvestor();
			crmInvestor.setUserId(userId);
			crmInvestor.setHeadImage(headImage);
			crmInvestorService.updateByUserId(crmInvestor);
		}else{
			return AppResponseUtil.getErrorBusi(new BaseResponse("uploadIconFail","上传头像更新数据库既不是理财师也不是投资者端"));
		}
		
		//理财师首次上传头像送红包
//		if(cfp != null && cfp.getHeadImage() == null ) {
//			CrmUserInfo userInfo = crmUserInfoService.queryUserInfoByUserId(userId);
//			try {
//				actRedpacketService.lcsTaskRedPacekt(CfpNewcomerTaskEnum.CFPLANNER_INEWCOMERWELFARE_UPLOAD_HEADIMAGE, userInfo);
//			} catch (Exception e) {
//				LOGGER.warn("newcomer welfare exception userInfo={}" ,userInfo, e.getMessage());
//			}
//		}
		
		return AppResponseUtil.getSuccessResponse();
	}
	
	/**
	 * 在投金额
	 */
	@RequestMapping("myCurrInvestAmount")
	@ResponseBody
	public BaseResponse myCurrInvestAmount(AppRequestHead head) throws Exception {
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		Double amt = investRecordService.queryCurrInvestAmount(userId);
		Map<String, String> rlt = new HashMap<String, String>();
		rlt.put("amount", WebUtil.getDefaultFormat(amt));
		return AppResponseUtil.getSuccessResponse(rlt);
	}
	
	/**
	 * 未完成新手任务
	 * @param head
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("unFinishNewerTask/2.2.0")
	@ResponseBody
	public BaseResponse unFinishNewerTask(AppRequestHead head) throws Exception {

		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		
		Map<String, Integer> rlt = new HashMap<String, Integer>();
		if(AppUtils.isChannelApp(head.getOrgNumber())){
			rlt.put("unFinishNewcomerTaskCount", crmCfpNewcomerTaskService.queryUnFinishNewcomerTaskCount(userId));		
			return AppResponseUtil.getSuccessResponse(rlt);
		}
		else {
			return new ErrorResponse("100002","参数错误");
		}	
	}
	
	/**
	 * 批量给老用户数据设置头像
	 */
	@RequestMapping("bacthSetHeadImage")
	@ResponseBody
	public BaseResponse bacthSetHeadImage(AppRequestHead head) throws Exception {
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		CrmInvestor user = crmInvestorService.queryInvestorByUserId(userId);
		if(user == null || !"18576651144".equals(user.getMobile())) {
			return AppResponseUtil.getErrorBusi("error","no permission");
		}
		List<AcAccountBind> ulist = null;
		try {
			//查出已实名认证未设置头像的理财师
			ulist = accountbindService.queryCfpOfNotSetImage();
			//设置随机默认头像
			for(AcAccountBind bo : ulist) {
				int sex = this.getSexByIdcard(bo.getIdCard());
				String headImage = getRadomHeadImage(sex);
				CrmCfplanner cfpUpdate = new CrmCfplanner();
				cfpUpdate.setUserId(bo.getUserId());
				cfpUpdate.setHeadImage(headImage);
				crmCfplannerService.updateByUserId(cfpUpdate);
			}
		} catch (Exception e) {
			LOGGER.info("批量给老用户数据设置头像: {}", e);
			return AppResponseUtil.getErrorBusi("error", e.getMessage());
		}
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("count", ulist.size());
		return AppResponseUtil.getSuccessResponse(map);
	}
	
	/**
	 * 根据性别获取随机头像-猎财大师
	 * @param flag
	 * @return
	 */
	private String getRadomHeadImage(int flag) {
		int number = new Random().nextInt(3) + 1;
		String headImage = "";
		if(flag == 1) {
			switch (number) {
			case 1 : headImage = "cb23a307a15cb134c8f6dacbca9928b7";
			break;
			case 2 : headImage = "5d58e7cf5721549e31166b8f4c79d5df";
			break;
			case 3 : headImage = "014c4d2316f71d6d2f02d6afa76750c8";
			break;
			default : headImage = "014c4d2316f71d6d2f02d6afa76750c8";
			break;
			}
		} else {
			switch (number) {
			case 1 : headImage = "2af903bfde4e1b7b671ef9668b960fb5";
			break;
			case 2 : headImage = "85540f6509d8f46b1e6fa9098626db68";
			break;
			case 3 : headImage = "742f95e30e825dc623e06940ffe7f72e";
			break;
			default : headImage = "742f95e30e825dc623e06940ffe7f72e";
			break;
			}
		}
		return headImage;
	}

	/**
	 * 根据身份证获取性别
	 * @param idCard
	 * @return 0女，1男
	 */
	private int getSexByIdcard(String idCard) {
		if(idCard == null || "".equals(idCard) || idCard.length() < 18) {
			return 1;
		}
		String sex = idCard.substring(16, 17);
		if(Integer.parseInt(sex)%2==0){
			return 0;
		}else{
			return 1;
		}
	}
	
	/**
	 * V4.1.1往期喜报(personcenter/queryOldGoodTrans)
	 * @param req
	 * @param result
	 * @param head
	 * @return
	 */
	@RequestMapping("/queryOldGoodTrans")
	@ResponseBody
	public BaseResponse queryOldGoodTransList(@Valid PaginatorRequest req,BindingResult result,AppRequestHead head) throws Exception {
		if(StringUtils.isBlank(head.getToken())){
			return  new BaseResponse(errorCode,"token不能为空");
		}
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		if (AppResponseUtil.existsParamsError(result)) {
			return AppResponseUtil.getErrorParams(result);
		}
		Map<String,Object> conditions = new HashMap<String, Object>();
		conditions.put("userId",userId);
		conditions.put("sort",req.getSort()!=null?req.getSort():2);
		conditions.put("month",DateUtils.format(DateUtils.subDay(new Date(),180),DateUtils.FORMAT_MONTH));
		Page<GoodTransResp> page  = new Page<GoodTransResp>(req.getPageIndex(),req.getPageSize());
		PaginatorResponse<GoodTransResp> datas =  null;
		try {
			datas = investRecordService.queryOldGoodTransList(page,conditions);
		} catch (Exception e) {
			return  new BaseResponse(errorCode,"查询往期喜报失败");
		}
		return AppResponseUtil.getSuccessResponse(datas,GoodTransPageListResponse.class);
	}
	
	
	/**
	 * V4.1.1出单喜报(personcenter.goodTrans)
	 * @param head
	 */
	@RequestMapping("/goodTrans")
	@ResponseBody
	public BaseResponse queryGoodTrans(@Valid GoodTransRequest req,BindingResult result,AppRequestHead head){
		if (AppResponseUtil.existsParamsError(result)) {
			return AppResponseUtil.getErrorParams(result);
		}
		
		if(StringUtils.isBlank(head.getToken())){
			return  new BaseResponse(errorCode,"token不能为空");
		}
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		GoodTransResp obj= null;
		if(req.getBillId()!=null&&req.getBillId().length()>0){
			obj = investRecordService.getGoodTransByInvestId(req.getBillId());
		}else{
			obj = investRecordService.getGoodTrans(userId);
			sysApiInvokeLogService.updateApiInvokeLog(ApiInvokeLogConstant.GOOD_TRANS_READED, userId,AppTypeEnum.CHANNEL.getKey());
		}
		return AppResponseUtil.getSuccessResponse(obj,GoodTransResponse.class);
		
	}
	
	/**
	 * V4.1.1是否有未读喜报(personcenter.haveGoodTransNoRead)
	 * @param head
	 */
	@RequestMapping("/haveGoodTransNoRead")
	@ResponseBody
	public BaseResponse haveGoodTransNoRead(AppRequestHead head){
		if(StringUtils.isBlank(head.getToken())){
			return  new BaseResponse(errorCode,"token不能为空");
		}
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		GoodTransResp obj = investRecordService.getGoodTrans(userId);
		SysApiInvokeLog apiInvokeLog = sysApiInvokeLogService.queryApiInvokeLog(ApiInvokeLogConstant.GOOD_TRANS_READED, userId, AppTypeEnum.CHANNEL.getKey());
		String lastReaddate = apiInvokeLog!=null ?  DateUtils.format(apiInvokeLog.getChgTime()): DateUtils.format(DateUtils.parse("1990-01-01 00:00:00"));
		GoodTransHaveRead read = new GoodTransHaveRead();;
		//有新的未读消息
		if(obj!=null&&DateUtils.compareDate(DateUtils.parse(obj.getInvestTime(),DateUtils.FORMAT_LONG),DateUtils.parse(lastReaddate,DateUtils.FORMAT_LONG))==1){
			read.setHaveRead("1");
		}
		return AppResponseUtil.getSuccessResponse(read);
	}
	
	/**
	 * 回款日历
	 */
	@RequestMapping("/repamentCalendar")
	@ResponseBody
	public BaseResponse repamentCalendar(AppRequestHead appRequestHead,RepamentCalendarRequest repamentCalendarRequest) throws Exception {
		PaginatorResponse<RepamentCalendarResponse> rlt = personCenterService.queryRepamentCalendarPageList(appRequestHead,repamentCalendarRequest);
		return AppResponseUtil.getSuccessResponse(rlt);
	}
	
	/**
	 * 回款日历统计
	 */
	@RequestMapping("/repamentCalendarStatistics")
	@ResponseBody
	public BaseResponse repamentCalendarStatistics(AppRequestHead appRequestHead,RePaymentCalendarStatisticsRequest rePaymentCalendarStatisticsRequest) throws Exception {
		rePaymentCalendarStatisticsRequest.setUserId(JsonWebTokenHepler.getUserIdByToken(appRequestHead.getToken()));
		LOGGER.info("查询回款日历统计, RePaymentCalendarStatisticsRequest={}", JSONObject.toJSONString(rePaymentCalendarStatisticsRequest));
		RepaymentCalendarStatisticsResponse repaymentCalendarStatisticsResponse = personCenterService.repamentCalendarStatistics(rePaymentCalendarStatisticsRequest);
		return AppResponseUtil.getSuccessResponse(repaymentCalendarStatisticsResponse);
	}
	
	
	/**
	 * 交易日历
	 */
	@RequestMapping("/investCalendar")
	@ResponseBody
	public BaseResponse investCalendar(AppRequestHead appRequestHead,InvestCalendarRequest investCalendarRequest) throws Exception {
		PaginatorResponse<InvestCalendarResponse> rlt = personCenterService.queryInvestCalendar(appRequestHead,investCalendarRequest);
		sysApiInvokeLogService.updateApiInvokeLog(ApiInvokeLogConstant.NEW_TRANS_RECORD_STATUS, JsonWebTokenHepler.getUserIdByToken(appRequestHead.getToken()),AppTypeEnum.CHANNEL.getKey());	
		return AppResponseUtil.getSuccessResponse(rlt);
	}
	
	/**
	 * 交易日历统计
	 */
	@RequestMapping("/investCalendarStatistics")
	@ResponseBody
	public BaseResponse investCalendarStatistics(AppRequestHead appRequestHead,InvestCalendarStatisticsRequest investCalendarStatisticsRequest) throws Exception {
		investCalendarStatisticsRequest.setUserId(JsonWebTokenHepler.getUserIdByToken(appRequestHead.getToken()));
		LOGGER.info("查询交易日历统计, investCalendarStatisticsRequest={}", JSONObject.toJSONString(investCalendarStatisticsRequest));
		InvestCalendarStatisticsResponse investCalendarStatisticsResponse = personCenterService.investCalendarStatistics(investCalendarStatisticsRequest);
		return AppResponseUtil.getSuccessResponse(investCalendarStatisticsResponse);
	}
	
	/**
	 * 交易详情
	 */
	@RequestMapping("/investCalendarDetail")
	@ResponseBody
	public BaseResponse investCalendarDetail(AppRequestHead appRequestHead,InvestCalendarDetailRequest investCalendarDetailRequest) throws Exception {
		InvestCalendarDetailResponse rlt = personCenterService.queryInvestCalendarDetail(appRequestHead,investCalendarDetailRequest);
		return AppResponseUtil.getSuccessResponse(rlt);
	}
	
    /**
	 * 4.5.0我的-整页-显示职级等
	 * @param head
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("personInfo")
	@ResponseBody
	public BaseResponse personInfo(AppRequestHead head) throws Exception {
		Integer appType = AppUtils.getAppType(head.getOrgNumber()).getKey();
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		LOGGER.debug("个人中心首页信息: userId = " + userId + "; appType = " + appType);
		Map<String,Object> conditions = Maps.newHashMap();//未读消息查询条件
		conditions.put("userId", userId);
        conditions.put("appType",appType);
        
        CrmCfplanner cfp = crmCfplannerService.queryCfplannerByUserId(userId);
        
        CfplannerPersonCenterResp rlt = new CfplannerPersonCenterResp();
        rlt.setUserName(cfp.getUserName());
        rlt.setHeadImage(cfp.getHeadImage());
        rlt.setMobile(cfp.getMobile()!=null?cfp.getMobile().substring(0, 3)+"****"+cfp.getMobile().substring(cfp.getMobile().length()-4, cfp.getMobile().length()):null);
        rlt.setGrade(CfpJobGradeEnum.getCfpJobGradeEnumByKey(cfp.getJobGrade()).getMsg());
        //记账本
        AccountBookStatisticResponse resultResp = crmUserAccountBookService.statistics(userId);
        rlt.setAccountBook(NumberUtils.getFormat(Double.parseDouble(resultResp == null?"0":resultResp.getInvestTotal()), "0.00")+"元总待收");
        //未读消息数量
        Integer personCount = msgService.queryUnreadLcsCount(userId, appType);
        if(null != personCount && personCount >99){
        	personCount = 99;
        }
        rlt.setMsgCount(personCount==null?"0":personCount.toString());
        
        //获取职级信息
        CfpLevelWarningResp cfpLevel = crmCfpLevelRecordTempService.cfpLevelWarning(userId);
        String reMsg = cfpLevel.getCfpLevelTitleNew().substring(4, 6)+"差";
        if(cfpLevel.getYearpurAmountMaxNew()-cfpLevel.getYearpurAmountActualNew() > 0){
        	reMsg += NumberUtils.getFormat((cfpLevel.getYearpurAmountMaxNew()-cfpLevel.getYearpurAmountActualNew()), "0.00")+"元";
        	
        }
        if(cfpLevel.getLowerLevelCfpMaxNew()-cfpLevel.getLowerLevelCfpActualNew() > 0){
        	reMsg += " "+(cfpLevel.getLowerLevelCfpMaxNew()-cfpLevel.getLowerLevelCfpActualNew())+"人";
        }
        if(cfpLevel.getCfpLevelTitleNew()!=null&&(cfpLevel.getCfpLevelTitleNew().contains("已完成总监所需")||cfpLevel.getCfpLevelContent().contains("已完成总监所需"))){
        	reMsg = "已完成总监所需的职级要求";
        }
        rlt.setGradePrivi(reMsg);
//		int feeCount = addFeeCouponService.queryAddFeeCouponCount();//平台加佣券
		int feeCount = actPersonAddfeeTicketService.queryPersonAddfeeTicket(userId, 1).size();//个人加佣券
		int vouCount = actJobGradeVoucherService.queryCanUserJobGradeVoucher(userId);
        int count = redPacketService.queryRedPacketCount5(userId);
        rlt.setCoupon(count+"个红包,"+(feeCount+vouCount)+"个券");
        rlt.setInsurance("查看我的保单");
        String thisMonth = new SimpleDateFormat("yyyy-MM").format(new Date());
        rlt.setMonthIncome(NumberUtils.getFormat(investRecordService.queryMonthIncome(thisMonth,userId), "0.00"));
        
//        rlt.setNewPaymentRecordStatus(true);//回款记录
        
        Date investTime = investRecordService.newTranRecordDate(userId);
		SysApiInvokeLog apiInvokeLog = sysApiInvokeLogService.queryApiInvokeLog(ApiInvokeLogConstant.NEW_TRANS_RECORD_STATUS, userId, AppTypeEnum.CHANNEL.getKey());
		String lastReaddate = apiInvokeLog!=null ?  DateUtils.format(apiInvokeLog.getChgTime()): DateUtils.format(DateUtils.parse("1990-01-01 00:00:00"));
		//有新的未读消息
		if(investTime!=null&&DateUtils.compareDate(DateUtils.parse(investTime,DateUtils.FORMAT_LONG),DateUtils.parse(lastReaddate,DateUtils.FORMAT_LONG))==1){
			rlt.setNewTranRecordStatus(true);//新的交易记录
		}
		
        rlt.setTotalIncome(NumberUtils.getFormat(investRecordService.queryAllTotalIncome(userId), "0.00"));//累计收益（统计所有）
      
    	Boolean isGrayUser = sysGrayReleaseService.ifHaveGrayPermission(userId, "0,2"); //判断是否灰度用户
    	Map<String, String> statisticsMap = cimOrgInfoService.investStatistics(userId,isGrayUser);
    	String investingAmt = statisticsMap.get("investAmt");
//        rlt.setNetLoan(NumberUtils.getFormat(investRecordService.queryCurrInvestAmount2(userId), "0.00"));
    	rlt.setNetLoan(investingAmt);//网贷 当前在投总额
    	
        Date paymentDate = investRecordService.selectPaymentDate(userId);
        rlt.setPaymentDate(paymentDate!=null?"最近:"+DateUtils.format(paymentDate,DateUtils.FORMAT_SHORT):"--");//回款日历 最近一笔
        
        
        AcAccountBind bind = accountbindService.selectAccountByUserId(userId);
        rlt.setTotalAmount(bind!=null?NumberUtils.getFormat(Double.parseDouble(bind.getTotalAmount()), "0.00"):"0.00");//余额
        
        Date ranRecordDate = investRecordService.selectTranRecordDate(userId);//近一笔交易时间
        rlt.setTranRecordDate(ranRecordDate!=null?"最近:"+DateUtils.format(ranRecordDate,DateUtils.FORMAT_SHORT):"--");//交易记录 最近一笔投资时间
        
        int custMembercount = crmCfplannerService.queryCustomerMember(userId);
        rlt.setCustomerMember("共"+custMembercount+"名");
        
        List<CrmCfplanner> cfpList = crmCfplannerService.queryLowerLevelOne(userId);
        rlt.setTeamMember("直推理财师"+cfpList.size()+"名");
		return AppResponseUtil.getSuccessResponse(rlt);
	}
	
	/**
	 * 4.5.0我的-客户成员/理财师团队成员
	 * @param head
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("customerCfpmember")
	@ResponseBody
	public BaseResponse customerCfpmember(@Valid UserTypeRequest req,BindingResult result,AppRequestHead head) throws Exception {
		if (AppResponseUtil.existsParamsError(result)) {
			return AppResponseUtil.getErrorParams(result);
		}
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		CfplannerInvestorPersonResp rlt = new CfplannerInvestorPersonResp();
		if("1".equals(req.getType())){
			rlt = crmCfplannerService.queryCfplannerMemberNum(userId);
		}else{
			rlt =  customerService.queryCustomerMemberNum(userId);
		}
		return AppResponseUtil.getSuccessResponse(rlt);
	}
	
	/**
	 * 4.5.0我的-客户成员/理财师团队成员分页
	 * @param req
	 * @param result
	 * @param head
	 * @return
	 */
	@RequestMapping("/customerCfpmemberPage")
	@ResponseBody
	public BaseResponse customerCfpmemberPage(@Valid UserTypeRequest req,BindingResult result,AppRequestHead head) throws Exception {
		if (AppResponseUtil.existsParamsError(result)) {
			return AppResponseUtil.getErrorParams(result);
		}
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		if (AppResponseUtil.existsParamsError(result)) {
			return AppResponseUtil.getErrorParams(result);
		}
		req.setUserId(userId);
		Page<CustomerCfpmember> page  = new Page<CustomerCfpmember>(req.getPageIndex(),req.getPageSize());
		PaginatorResponse<CustomerCfpmember> datas =  null;
		try {
			if("1".equals(req.getType())){
				datas = crmCfplannerService.queryCfpmemberPage(page,req);
			}else{
				datas = customerService.queryCustomerMemberPage(page,req);
			}
		} catch (Exception e) {
			return  new BaseResponse(errorCode,"查询失败");
		}
		return AppResponseUtil.getSuccessResponse(datas,CustomerCfpmemberPageResponse.class);
	}
	
	/**
	 * 4.5.0我的-整页-基金
	 * @param head
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("personInfoFund")
	@ResponseBody
	public BaseResponse personInfoFund(AppRequestHead head,GetInvestorHoldingsRequest getInvestorHoldingsRequest) throws Exception {
		CfplannerPersonFund rlt = new CfplannerPersonFund();
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		if(!"undefined".equals(userId)){
			if(crmOrgAcctRelService.ifOrgAccountExist(userId, getInvestorHoldingsRequest.getOrgCode())){		
		        HoldingsStatistic holdingsStatistic = cimFundOrderService.getOnTransactionAmountByUserId(userId);
		        LOGGER.info("4.5.0我的-基金信息: userId = {} holdingsStatistic={}" , userId,JSONObject.toJSONString(holdingsStatistic));
		        rlt.setFundAmount(NumberUtils.getFormatHaveUp(holdingsStatistic==null?new BigDecimal(0):new BigDecimal(holdingsStatistic.getInvestmentAmount()))+"元在投");
			} else {
				rlt.setFundAmount("0.00元在投");
			}
		} else {
			rlt.setFundAmount("0.00元在投");
		}
		return AppResponseUtil.getSuccessResponse(rlt);
	}
	
	
	/**
	 * 4.5.0我的-客户成员详情
	 * @param head
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("customerDetail")
	@ResponseBody
	public BaseResponse customerDetail(@Valid DirectCfpJobGrade req,BindingResult result,AppRequestHead head) throws Exception {
		if (AppResponseUtil.existsParamsError(result)) {
			return AppResponseUtil.getErrorParams(result);
		}
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
        CustomerMemberDetailResponse rlt = new CustomerMemberDetailResponse();
        CrmInvestor crmIn = crmInvestorService.queryInvestorByUserId(req.getUserId());
        rlt.setFirstInvestTime(crmIn.getFirstInvestTime()!=null?DateUtils.format(crmIn.getFirstInvestTime(),DateUtils.FORMAT_SHORT):"--");
        rlt.setCurrInvestAmt(NumberUtils.getFormat(investRecordService.queryCurrInvestAmount(req.getUserId()), "0.00"));//在投金额
        rlt.setFollow(crmIn.getIsImportant()==1?true:false);
        rlt.setHeadImage(crmIn.getHeadImage()==null?"7187525842a640ca36e48a5ce366894d":crmIn.getHeadImage());
        rlt.setRegistTime(DateUtils.format(crmIn.getCreateTime(),DateUtils.FORMAT_SHORT));
        rlt.setMobile(crmIn.getMobile());
        String thisMonth = new SimpleDateFormat("yyyy-MM").format(new Date());

        CustomerMemberDetailResponse resp = customerService.queryCustomerDetail(req.getUserId(),userId,thisMonth);
        rlt.setLoginTime(resp.getLoginTime());//最近一次登录时间
        rlt.setThisMonthInvestAmt(NumberUtils.getFormat(Double.parseDouble(resp.getThisMonthInvestAmt()), "0.00"));//本月投资金额
        rlt.setThisMonthProfit(NumberUtils.getFormat(Double.parseDouble(resp.getThisMonthProfit()), "0.00"));//本月贡献收入
        rlt.setTotalProfit(NumberUtils.getFormat(Double.parseDouble(resp.getTotalProfit()), "0.00"));//累计贡献收入
        rlt.setTotalInvestAmt(NumberUtils.getFormat(investRecordService.queryCustomerInvestTotalAmount(req.getUserId()), "0.00"));//累计投资金额
        rlt.setUserName(crmIn.getUserName()==null?"未认证":crmIn.getUserName());
        List<OrgSimpleResp> orgList = customerService.querycustomerCfpRegisteredOrgList(req.getUserId());
		if(orgList != null) {
			for(OrgSimpleResp bo : orgList) {
				if(bo != null && bo.getOrgLogo() != null && !"".equals(bo.getOrgLogo())){
					bo.setOrgLogo(sysConfigService.getImageUrl(bo.getOrgLogo()));
				}
			}
			rlt.setRegisteredOrgList(orgList);
		}
		return AppResponseUtil.getSuccessResponse(rlt);
	}
	
	/**
	 * 4.5.0我的-客户成员详情-投资记录
	 * @param req
	 * @param result
	 * @param head
	 * @return
	 */
	@RequestMapping("/customerInvestRecord")
	@ResponseBody
	public BaseResponse customerInvestRecordPage(@Valid MyCustomerInvestRecordRequest req,BindingResult result,AppRequestHead head) throws Exception {
		if (AppResponseUtil.existsParamsError(result)) {
			return AppResponseUtil.getErrorParams(result);
		}
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		if (AppResponseUtil.existsParamsError(result)) {
			return AppResponseUtil.getErrorParams(result);
		}
		Map<String,Object> conditions = new HashMap<String, Object>();
		conditions.put("userId",userId);
		Page<CustomerMemberInvestRecordResponse> page  = new Page<CustomerMemberInvestRecordResponse>(req.getPageIndex(),req.getPageSize());
		PaginatorResponse<CustomerMemberInvestRecordResponse> datas =  null;
		try {
			
			if("2".equals(req.getType())){
				conditions.put("userId",req.getUserId());
				datas = customerService.customerInvestRecordPage(page,conditions);
			}else{
				conditions.put("cfpUserId",req.getUserId());//直接下级理财师userID
				datas = crmCfplannerService.cfpInvestRecordPage(page,conditions);
			}
		} catch (Exception e) {
			return  new BaseResponse(errorCode,"查询失败");
		}
		return AppResponseUtil.getSuccessResponse(datas,CustomerMemberInvestRecordResponse.class);
	}
	
	/**
	 * 4.5.0我的-理财师团队成员详情
	 * @param head
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("cfplannerDetail")
	@ResponseBody
	public BaseResponse cfplannerDetail(@Valid DirectCfpJobGrade req,BindingResult result,AppRequestHead head) throws Exception {
		if (AppResponseUtil.existsParamsError(result)) {
			return AppResponseUtil.getErrorParams(result);
		}
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
        
        CrmCfplanner cfp = crmCfplannerService.queryCfplannerByUserId(req.getUserId());
        CrmInvestor crmIn = crmInvestorService.queryInvestorByUserId(req.getUserId());
        CfplannerMemberDetailResponse rlt = new CfplannerMemberDetailResponse();
        rlt.setFirstInvestTime(crmIn.getFirstInvestTime()!=null?DateUtils.format(crmIn.getFirstInvestTime(),DateUtils.FORMAT_SHORT):"--");
        rlt.setCurrInvestAmt(NumberUtils.getFormat(investRecordService.queryCurrInvestAmount(req.getUserId()), "0.00"));
        rlt.setFollow(cfp.getIsImportant()==1?true:false);
        rlt.setHeadImage(cfp.getHeadImage()==null?"7187525842a640ca36e48a5ce366894d":cfp.getHeadImage());
        rlt.setMobile(crmIn.getMobile());
        rlt.setRegistTime(DateUtils.format(cfp.getCfpRegTime(),DateUtils.FORMAT_SHORT));
        rlt.setUserName(crmIn.getUserName()==null?"未认证":crmIn.getUserName());
        List<CrmCfplanner> cfpList = crmCfplannerService.queryLowerLevelOne(req.getUserId());
        rlt.setDirectRecomCfp(cfpList.size()+"");
        rlt.setGrade(CfpJobGradeEnum.getCfpJobGradeEnumByKey(cfp.getJobGrade()).getMsg());
        
        String thisMonth = new SimpleDateFormat("yyyy-MM").format(new Date());
        CfplannerMemberDetailResponse resp = crmCfplannerService.queryCfplannerDetail(req.getUserId(),userId,thisMonth);
        rlt.setThisMonthIssueAmt(NumberUtils.getFormat(Double.parseDouble(resp.getThisMonthIssueAmt()), "0.00"));
        rlt.setThisMonthProfit(NumberUtils.getFormat(Double.parseDouble(resp.getThisMonthProfit()), "0.00"));
        rlt.setTotalIssueAmt(NumberUtils.getFormat(Double.parseDouble(resp.getTotalIssueAmt()), "0.00"));
        rlt.setTotalProfit(NumberUtils.getFormat(Double.parseDouble(resp.getTotalProfit()), "0.00"));
        rlt.setSecondLevelCfp(resp.getSecondLevelCfp());
        rlt.setLoginTime(resp.getLoginTime());
        List<OrgSimpleResp> orgList = customerService.querycustomerCfpRegisteredOrgList(req.getUserId());
		if(orgList != null) {
			for(OrgSimpleResp bo : orgList) {
				if(bo != null && bo.getOrgLogo() != null && !"".equals(bo.getOrgLogo())){
					bo.setOrgLogo(sysConfigService.getImageUrl(bo.getOrgLogo()));
				}
			}
			rlt.setRegisteredOrgList(orgList);
		}
		return AppResponseUtil.getSuccessResponse(rlt);
	}
	
	/**
	 * 4.5.0我的-理财师成员
	 * @param req
	 * @param result
	 * @param head
	 * @return
	 */
	@RequestMapping("/cfplannerMemberPage")
	@ResponseBody
	public BaseResponse cfplannerMemberPage(@Valid PaginatorRequest req,BindingResult result,AppRequestHead head) throws Exception {
		if (AppResponseUtil.existsParamsError(result)) {
			return AppResponseUtil.getErrorParams(result);
		}
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		if (AppResponseUtil.existsParamsError(result)) {
			return AppResponseUtil.getErrorParams(result);
		}
		Map<String,Object> conditions = new HashMap<String, Object>();
		conditions.put("userId",userId);
		Page<CfplannerMemberResponse> page  = new Page<CfplannerMemberResponse>(req.getPageIndex(),req.getPageSize());
		PaginatorResponse<CfplannerMemberResponse> datas =  null;
		try {
			datas = crmCfplannerService.cfplannerMemberPage(page,conditions);
		} catch (Exception e) {
			return  new BaseResponse(errorCode,"查询失败");
		}
		return AppResponseUtil.getSuccessResponse(datas,CfplannerMemberResponse.class);
	}
	
	/**
	 * 4.5.0我的-下级理财师职级特权
	 * @param head
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("directCfpJobGrade")
	@ResponseBody
	public BaseResponse directCfpJobGrade(@Valid DirectCfpJobGrade req,BindingResult result,AppRequestHead head) throws Exception {
		if (AppResponseUtil.existsParamsError(result)) {
			return AppResponseUtil.getErrorParams(result);
		}
		PartnerJobGradeResponse re = partnerService.jobGrade(req.getUserId());
		return AppResponseUtil.getSuccessResponse(re);
	}
	
	/**
	 * 4.5.1我的-推荐
	 * @param head
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("recomProductOrg")
	@ResponseBody
	public BaseResponse recomProductOrg(@Valid RecomProductOrgRequest req,BindingResult result,AppRequestHead head) throws Exception {
		if (AppResponseUtil.existsParamsError(result)) {
			return AppResponseUtil.getErrorParams(result);
		}
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		CrmCfplanner cfplanner = crmCfplannerService.queryCfplannerByUserId(userId);
		String userName = cfplanner.getUserName()==null?cfplanner.getMobile():cfplanner.getUserName();
		String content = "";
		OrgInfo org = new OrgInfo();
		ProductDetailResponse product = new ProductDetailResponse();
		if("1".equals(req.getIdType())){//1=产品ID 2 =机构ID
			product = cimProductService.queryProductDetail(req.getProductOrgId());
			String rate = product.getIsFlow()==1?product.getFlowMinRate()+"%":product.getFlowMinRate()+"%~"+product.getFlowMaxRate()+"%";
			content = "理财师"+userName+"给你推荐了一个产品【"+product.getProductName()+"】,预期收益:"+rate+",产品期限:"+product.getDeadLineMinSelfDefined()+",你可以多了解下哦~";
		}else{
			org = cimOrginfoService.findOrgInfo(req.getProductOrgId());
			content = "理财师"+userName+"给你推荐了一个平台:"+org.getOrgName()+"("+org.getOrgAdvantage()+"),你可以多了解下哦~";
		}
		if(StringUtils.isNotBlank(req.getUserId())){
			String[] userIds = req.getUserId().split(",");
			for(String senduserId : userIds){
				pushMessageHelper.pushMessage("1".equals(req.getType())?AppTypeEnum.CHANNEL:AppTypeEnum.INVESTOR,SmsTypeEnum.RECOM_PRODUCT_ORG,senduserId,"推荐通知",content,null,true);
			}
		}
		return AppResponseUtil.getSuccessResponse();
	}
	
	/**
	 * 保险交易日历
	 */
	@RequestMapping("/insuranceCalendar")
	@ResponseBody
	public BaseResponse insuranceCalendar(AppRequestHead appRequestHead,InvestCalendarRequest investCalendarRequest) throws Exception {
		PaginatorResponse<InsuranceInvestCalendarResponse> rlt = personCenterService.queryInsuranceCalendar(appRequestHead,investCalendarRequest);
		sysApiInvokeLogService.updateApiInvokeLog(ApiInvokeLogConstant.NEW_INSURANCE_RECORD_STATUS, JsonWebTokenHepler.getUserIdByToken(appRequestHead.getToken()),AppTypeEnum.CHANNEL.getKey());	
		return AppResponseUtil.getSuccessResponse(rlt);
	}
	
	/**
	 * 保险交易详情
	 */
	@RequestMapping("/insuranceInvestCalendarDetail")
	@ResponseBody
	public BaseResponse insuranceInvestCalendarDetail(AppRequestHead appRequestHead,InvestCalendarDetailRequest investCalendarDetailRequest) throws Exception {
		InsuranceInvestCalendarDetailResponse rlt = personCenterService.queryInsuranceInvestCalendarDetail(appRequestHead,investCalendarDetailRequest);
		return AppResponseUtil.getSuccessResponse(rlt);
	}
	
	/**
	 * 投资记录平台奖励
	 * @param appRequestHead
	 * @param platformBounsRequest
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/investRecord/platformBouns")
	@ResponseBody
	@RequestLogging("投资记录平台奖励")
	public BaseResponse platformBouns(@Valid PlatformBounsRequest platformBounsRequest,BindingResult validResult,AppRequestHead appRequestHead) throws Exception {
		LOGGER.info("投资记录平台奖励传参，platformBounsRequest={}",JSON.toJSONString(platformBounsRequest));
		if (OpenResponseUtil.existsParamsError(validResult)) {
			return OpenResponseUtil.getErrorParams(validResult);
		}
		String userId = JsonWebTokenHepler.getUserIdByToken(appRequestHead.getToken());		
		if(StringUtils.isBlank(userId) || userId.equals("undefined")){
			return AppResponseUtil.getErrorToken();
		}
		platformBounsRequest.setUserId(userId);
		investRecordService.updatePlatformBouns(platformBounsRequest);
		return AppResponseUtil.getSuccessResponse();
	}
}
