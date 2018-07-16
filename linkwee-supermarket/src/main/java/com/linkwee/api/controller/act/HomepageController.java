package com.linkwee.api.controller.act;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import com.linkwee.api.response.CradListResponse;
import com.linkwee.api.response.HomePageInvestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.linkwee.act.redpacket.model.ActRedpacketDetail;
import com.linkwee.act.redpacket.service.RedPacketService;
import com.linkwee.api.request.act.AdvPageListRequest;
import com.linkwee.api.request.act.AdvertisementPageListRequest;
import com.linkwee.api.response.tc.CfpFeeInfoResponse;
import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.core.base.api.ErrorResponse;
import com.linkwee.core.constant.ApiInvokeLogConstant;
import com.linkwee.core.util.DateUtils;
import com.linkwee.core.util.NumberUtils;
import com.linkwee.core.util.StringUtils;
import com.linkwee.web.enums.PlatformEnum;
import com.linkwee.web.model.ActAddFeeCoupon;
import com.linkwee.web.model.ActAddFeeCouponUseDetail;
import com.linkwee.web.model.CrmCfplanner;
import com.linkwee.web.model.CrmUserInfo;
import com.linkwee.web.model.SysApiInvokeLog;
import com.linkwee.web.model.SysHomepageCommission;
import com.linkwee.web.model.news.SmAdvertisement;
import com.linkwee.web.response.BannersResponse;
import com.linkwee.web.service.ActAddFeeCouponService;
import com.linkwee.web.service.ActAddFeeCouponUseDetailService;
import com.linkwee.web.service.AdvertisementService;
import com.linkwee.web.service.CimFeeServiceNew;
import com.linkwee.web.service.CimProductInvestRecordService;
import com.linkwee.web.service.CrmCfpNewcomerWelfareTaskService;
import com.linkwee.web.service.CrmCfplannerService;
import com.linkwee.web.service.CrmUserInfoService;
import com.linkwee.web.service.HomepageService;
import com.linkwee.web.service.SysApiInvokeLogService;
import com.linkwee.web.service.SysConfigService;
import com.linkwee.web.service.SysHomepageCommissionService;
import com.linkwee.xoss.api.AppRequestHead;
import com.linkwee.xoss.helper.JsonWebTokenHepler;
import com.linkwee.xoss.util.AppResponseUtil;
import com.linkwee.xoss.util.AppUtils;
import com.linkwee.xoss.util.RequestLogging;
import com.linkwee.xoss.util.WebUtil;



/**
 * 
 * @描述： 实体控制器
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年07月11日 16:27:03
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 *
 */
@Controller
@RequestMapping(value = "/api/homepage")
@RequestLogging("首页")
public class HomepageController  {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(HomepageController.class);
	
	@Resource
	private AdvertisementService advertisementService;
	
	@Resource
    private CrmCfplannerService crmCfplannerService;
	
	@Resource
	private SysApiInvokeLogService sysApiInvokeLogService;
	
	@Resource
	private SysHomepageCommissionService sysHomepageCommissionService;
	
	@Resource
    private CimProductInvestRecordService investRecordService;
	
	@Resource
	private SysConfigService sysConfigService;
	
	@Resource
	private CrmCfpNewcomerWelfareTaskService crmCfpNewcomerWelfareTaskService;
	
	@Resource
	private RedPacketService redPacketService;
	
	@Resource
	private ActAddFeeCouponService addFeeCouponService;
	
	@Resource
	private ActAddFeeCouponUseDetailService addFeeCouponUseDetailService;
	
	@Resource
	private CimFeeServiceNew feeService;
	
	@Resource
	private CrmUserInfoService userInfoService;
	
	@Resource
	private HomepageService homepageService;
	
	/**
	 * 首页广告条
	 * @param head
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/banners")
	@ResponseBody
	@RequestLogging("首页banner")
	public BaseResponse banners(AppRequestHead head) {
		LOGGER.info("首页head:"+head);
		Integer appType = AppUtils.getAppType(head.getOrgNumber()).getKey();
		List<SmAdvertisement> advertisementList = advertisementService.queryBanner(appType);
		List<BannersResponse> rlt = new ArrayList<BannersResponse>();
		Map<String,String> params = new HashMap<String,String>();
		if(StringUtils.isNotBlank(head.getToken())){
			PlatformEnum platform =  AppUtils.getPlatform(head.getOrgNumber());
			if(PlatformEnum.IOS.equals(platform)||PlatformEnum.ANDROID.equals(platform)){
				params.put("token",head.getToken());
			}
		}
		if(null != advertisementList){
			for(SmAdvertisement obj:advertisementList){
				obj.setLinkUrl(WebUtil.creatUrl(obj.getLinkUrl(), params));
				rlt.add(new BannersResponse(obj));
			}
		}
		Map<String,Object> ret = new HashMap<String,Object>();
		ret.put("datas",rlt);
		LOGGER.info("bannerreturn:"+AppResponseUtil.getSuccessResponse(ret));
		//更新理财师访问时间
		if(AppUtils.isChannelApp(head.getOrgNumber())){
			CrmCfplanner crmCfplanner = new CrmCfplanner();
			crmCfplanner.setUserId(JsonWebTokenHepler.getUserIdByToken(head.getToken()));
			crmCfplanner.setRectVisitTime(new Date());
			crmCfplannerService.updateByUserId(crmCfplanner);
		}
		return AppResponseUtil.getSuccessResponse(ret);
	}
	/**
	 * 开屏广告
	 * @param req
	 * @param head
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/opening")
	@ResponseBody
	@RequestLogging("开屏广告")
	public BaseResponse partnerPageList(@Valid AdvertisementPageListRequest req,BindingResult result,AppRequestHead head) throws Exception {
		if (AppResponseUtil.existsParamsError(result)) {
			return AppResponseUtil.getErrorParams(result);
		}
		Integer appType = AppUtils.getAppType(head.getOrgNumber()).getKey();
		SmAdvertisement adv = new SmAdvertisement();
		adv.setAppType(appType);
		adv.setPageIndex("app_opening");
		adv.setStatus(0);
		
		List<SmAdvertisement> rlt = advertisementService.findAdvertisementDtl(adv);
		return AppResponseUtil.getSuccessResponse(rlt);
	}
	
	/**
	 * 广告
	 * @param req
	 * @param head
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/advs")
	@ResponseBody
	@RequestLogging("广告")
	public BaseResponse advPageList(@Valid AdvPageListRequest req,BindingResult result,AppRequestHead head) throws Exception {
		if (AppResponseUtil.existsParamsError(result)) {
			return AppResponseUtil.getErrorParams(result);
		}
		Integer appType = AppUtils.getAppType(head.getOrgNumber()).getKey();
		SmAdvertisement adv = new SmAdvertisement();
		adv.setAppType(appType);
		adv.setPageIndex(req.getAdvPlacement());
		adv.setStatus(0);	
		List<SmAdvertisement> rlt = advertisementService.findAdvertisementDtl(adv);
		
		return AppResponseUtil.getSuccessResponse(rlt);
	}
	
	/**
	 * 产品弹出窗
	 * @param head
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/product/opening/2.1.0")
	@ResponseBody
	@RequestLogging("产品弹出窗")
	public BaseResponse productOpeningAdv(AppRequestHead head) throws Exception {
		
		Integer appType = AppUtils.getAppType(head.getOrgNumber()).getKey();
		SmAdvertisement adv = new SmAdvertisement();
		adv.setAppType(appType);
		adv.setPageIndex("product_opening");
		adv.setStatus(0);	
		List<SmAdvertisement> rlt = advertisementService.findAdvertisementDtl(adv);
		SmAdvertisement result = null;
		if(rlt != null && rlt.size() >= 1){
			result = rlt.get(0);
		}
		
		return AppResponseUtil.getSuccessResponse(result);
	}
	
	/**
	 * 首页理财师发放佣金累计和出单
	 * @param head
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/cfp/sysInfo/4.0.0")
	@ResponseBody
	@RequestLogging("首页理财师发放佣金累计和出单")
	public BaseResponse sysCfpHomepageInfo(AppRequestHead head) throws Exception{
		return AppResponseUtil.getSuccessResponse(homepageService.querySysCfpHomepageInfo());
	}
	
	/**
	 * 猎财大师业绩
	 * @param head
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/lcs/achievement/4.3.0")
	@ResponseBody
	@RequestLogging("猎财大师业绩")
	public BaseResponse lcsAchievement(AppRequestHead head) throws Exception{
		SysHomepageCommission sysHomepageCommission = sysHomepageCommissionService.selectNewest();
		Map<String,Object> resp = new HashMap<String,Object>();
		resp.put("commissionAmount", sysHomepageCommission.getAmount().replace(",", ""));
		String fromTime = sysConfigService.getValuesByKey("addNewCfpNumFromTime", 1);
		int addTime = crmCfplannerService.queryNewCfpNumber(fromTime);
		resp.put("activeUserNumber", 8215+addTime);
		resp.put("safeOperationTime", DateUtils.countDays("2015-09-14 00:00:00"));
		resp.put("reInvestRate", "70%");
		return AppResponseUtil.getSuccessResponse(resp);
	}
	
	/**
	 * 是否有新的红包
	 * @param head
	 * @return
	 */
	@RequestMapping(value="/hasNewRedPacket/4.5.0")
	@ResponseBody
	@RequestLogging("是否有新的红包")
	public BaseResponse hasNewRedPacket(AppRequestHead head) {
		LOGGER.info("是否有新的红包请求参数AppRequestHead = {}" ,JSON.toJSONString(head));
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		Integer appType = AppUtils.getAppType(head.getOrgNumber()).getKey();
		if(StringUtils.isBlank(userId) || userId.equals("undefined") || appType == null){
			return new ErrorResponse("100002","参数错误");
		}
		SysApiInvokeLog apiInvokeLog = sysApiInvokeLogService.queryApiInvokeLog(ApiInvokeLogConstant.NAME_NEW_REAPACKET, userId, appType);
		ActRedpacketDetail redpacket = redPacketService.queryNewestRedPacket(userId);
		sysApiInvokeLogService.updateApiInvokeLog(ApiInvokeLogConstant.NAME_NEW_REAPACKET, userId,appType);
		Map<String,Object> resp = new HashMap<String,Object>();
		Integer compareResult;
		if(redpacket == null && apiInvokeLog == null){
			compareResult = 0;
			resp.put("hasNewRedPacket", compareResult);		
			return AppResponseUtil.getSuccessResponse(resp);
		}else if(apiInvokeLog == null){
			compareResult = 1;
			resp.put("hasNewRedPacket", compareResult);		
			return AppResponseUtil.getSuccessResponse(resp);
		}else if(redpacket == null){
			compareResult = 0;
			resp.put("hasNewRedPacket", compareResult);		
			return AppResponseUtil.getSuccessResponse(resp);
		}else{
			compareResult =DateUtils.compareDate(apiInvokeLog.getChgTime(), redpacket.getUpdateTime());
			if(compareResult < 0){
				compareResult = 1;
			}
			else {
				compareResult = 0;
			}	
			resp.put("hasNewRedPacket", compareResult);	
			return AppResponseUtil.getSuccessResponse(resp);
		}	
	}
	
	/**
	 * 是否有新的加拥券
	 * @param head
	 * @return
	 */
	@RequestMapping(value="/hasNewAddFeeCoupon/4.5.0")
	@ResponseBody
	@RequestLogging("是否有新的加拥券")
	public BaseResponse hasNewAddFeeCoupon(AppRequestHead head) {
		LOGGER.info("是否有新的加拥券请求参数AppRequestHead = {}" ,JSON.toJSONString(head));
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		Integer appType = AppUtils.getAppType(head.getOrgNumber()).getKey();
		if(StringUtils.isBlank(userId) || userId.equals("undefined") || appType == null){
			return new ErrorResponse("100002","参数错误");
		}
		SysApiInvokeLog apiInvokeLog = sysApiInvokeLogService.queryApiInvokeLog(ApiInvokeLogConstant.NAME_NEW_ADDFEECOUPON, userId, appType);
		ActAddFeeCoupon addFeeCoupon = addFeeCouponService.queryNewestAddFeeCoupon();
		sysApiInvokeLogService.updateApiInvokeLog(ApiInvokeLogConstant.NAME_NEW_ADDFEECOUPON, userId,appType);
		Map<String,Object> resp = new HashMap<String,Object>();
		Integer compareResult;
		if(addFeeCoupon == null && apiInvokeLog == null){
			compareResult = 0;
			resp.put("hasNewAddFeeCoupon", compareResult);		
		}else if(apiInvokeLog == null){
			compareResult = 1;
			resp.put("hasNewAddFeeCoupon", compareResult);		
		}else if(addFeeCoupon == null){
			compareResult = 0;
			resp.put("hasNewAddFeeCoupon", compareResult);		
		}else{
			compareResult =DateUtils.compareDate(apiInvokeLog.getChgTime(), addFeeCoupon.getCreateTime());
			if(compareResult < 0){
				compareResult = 1;
			}
			else {
				compareResult = 0;
			}	
			resp.put("hasNewAddFeeCoupon", compareResult);			
		}
		if(compareResult == 1){
			resp.put("addFeeCoupon", addFeeCoupon);
		}
		return AppResponseUtil.getSuccessResponse(resp);
	}
	
	/**
	 * 加拥券是否有新的加拥
	 * @param head
	 * @return
	 */
	@RequestMapping(value="/hasNewAddFee/4.5.0")
	@ResponseBody
	@RequestLogging("加拥券是否有新的加拥")
	public BaseResponse hasNewAddFee(AppRequestHead head) {
		LOGGER.info("加拥券是否有新的加拥请求参数AppRequestHead = {}" ,JSON.toJSONString(head));
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		Integer appType = AppUtils.getAppType(head.getOrgNumber()).getKey();
		if(StringUtils.isBlank(userId) || userId.equals("undefined") || appType == null){
			return new ErrorResponse("100002","参数错误");
		}
		SysApiInvokeLog apiInvokeLog = sysApiInvokeLogService.queryApiInvokeLog(ApiInvokeLogConstant.NAME_NEW_ADDFEE, userId, appType);
		ActAddFeeCouponUseDetail addFeeCouponUseDetail = addFeeCouponUseDetailService.queryNewestAddFee(userId);
		sysApiInvokeLogService.updateApiInvokeLog(ApiInvokeLogConstant.NAME_NEW_ADDFEE, userId,appType);
		Map<String,Object> resp = new HashMap<String,Object>();
		Integer compareResult;
		if(addFeeCouponUseDetail == null && apiInvokeLog == null){
			compareResult = 0;
			resp.put("hasNewAddFee", compareResult);
		}else if(apiInvokeLog == null){
			compareResult = 1;
			resp.put("hasNewAddFee", compareResult);		
		}else if(addFeeCouponUseDetail == null){
			compareResult = 0;
			resp.put("hasNewAddFee", compareResult);		
		}else{
			compareResult =DateUtils.compareDate(apiInvokeLog.getChgTime(), addFeeCouponUseDetail.getCreateTime());
			if(compareResult < 0){
				compareResult = 1;
			}
			else {
				compareResult = 0;
			}	
			resp.put("hasNewAddFee", compareResult);			
		}
		if(compareResult == 1){
			resp.put("addFeeCouponUseDetail", addFeeCouponUseDetail);
		}
		return AppResponseUtil.getSuccessResponse(resp);
	}
	
	@RequestMapping(value="/cfp/feeInfo/4.5.4")
	@ResponseBody
	@RequestLogging("邀请理财师发放佣金信息")
	public BaseResponse sysCfpFeeInfo(AppRequestHead head){
		SysHomepageCommission sysHomepageCommission = sysHomepageCommissionService.selectNewest();
		int lcsNumber = crmCfplannerService.lcsNumber();
		BigDecimal commissionAmount = new BigDecimal(sysHomepageCommission.getAmount().replace(",", "")).divide(new BigDecimal(lcsNumber),2, BigDecimal.ROUND_DOWN);
		List<CfpFeeInfoResponse> feeList = feeService.virtualFeeList();
		Map<String,Object> resp = new HashMap<String,Object>();
		resp.put("lcsNumber", lcsNumber);
		resp.put("commissionAmount", commissionAmount.setScale(2, BigDecimal.ROUND_DOWN).toString());
		resp.put("feeList", feeList);
		return AppResponseUtil.getSuccessResponse(resp);
	}
	
	@RequestMapping(value="/cfp/inviteRegInfo/4.5.4")
	@ResponseBody
	@RequestLogging("邀请理财师注册页面信息")
	public BaseResponse inviteRegInfo(AppRequestHead head,String userId,String mobile){
		if(StringUtils.isBlank(userId) || userId.equals("undefined")){
			userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());		
		}
		LOGGER.info("邀请理财师注册页面信息请求参数AppRequestHead = {}" ,JSON.toJSONString(head));
		LOGGER.info("邀请理财师注册页面信息请求userid={}",userId);
		LOGGER.info("邀请理财师注册页面信息请求mobile={}",mobile);
		CrmUserInfo userInfo = new CrmUserInfo();
		if(StringUtils.isBlank(userId) || userId.equals("undefined")){
			userInfo.setMobile(mobile);
		}else{
			userInfo.setUserId(userId);
		}	
		userInfo = userInfoService.selectOne(userInfo);
		List<String> mobileList = crmCfplannerService.queryNewRegTop();
		for(String mobileTemp : mobileList){
			mobileTemp = mobileTemp.substring(0, 3) + "****" + mobileTemp.substring(7);
		}
		Map<String,Object> resp = new HashMap<String,Object>();
		if(userInfo != null){
			String userName = "";
			if(userInfo.getUserName() != null){
				if(userInfo.getUserName().length() == 1){
					userName = userInfo.getUserName();
	            }
	            if(userInfo.getUserName().length() == 2){
	            	userName = userInfo.getUserName().replaceFirst(userInfo.getUserName().substring(1),"*");
	            }
	            if (userInfo.getUserName().length() > 2) {
	            	userName = userInfo.getUserName().replaceFirst(userInfo.getUserName().substring(1,userInfo.getUserName().length()-1) ,"*");
	            }
			}else{
				userName = userInfo.getMobile().substring(0, 3)+"****"+userInfo.getMobile().substring(7);				
			}
			resp.put("regTime", DateUtils.countDays(userInfo.getCreateTime()));
			resp.put("userName", userName);
			resp.put("totalIncome", NumberUtils.getFormat(investRecordService.queryAllTotalIncome(userInfo.getUserId()), "0.00"));
			resp.put("mobile", userInfo.getMobile());
		}		
		resp.put("newRegList", mobileList);
		return AppResponseUtil.getSuccessResponse(resp);
	}

	@RequestMapping(value="/cfp/card/list/4.6.7")
	@ResponseBody
	@RequestLogging("理财师首页卡片列表")
	public BaseResponse cfpCardList(AppRequestHead head){
		List<CradListResponse> responseList = homepageService.cfpCardList(head);
		return AppResponseUtil.getSuccessResponse(responseList);
	}

	@RequestMapping(value="/cfp/invest/statistic/4.6.7")
	@ResponseBody
	@RequestLogging("理财师首页投资统计")
	public BaseResponse cfpInvestStatistic(AppRequestHead head){
		Map<String,Object> resultMap = homepageService.cfpInvestStatistic(head);
		return AppResponseUtil.getSuccessResponse(resultMap);
	}

	@RequestMapping(value="/cfp/invest/list/4.6.7")
	@ResponseBody
	@RequestLogging("理财师首页投资列表")
	public BaseResponse cfpInvestList(){
		List<HomePageInvestResponse> responseList = homepageService.homepageInvestList();
		return AppResponseUtil.getSuccessResponse(responseList);
	}
	
}
