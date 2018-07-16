package com.linkwee.api.controller.act;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.linkwee.api.request.act.SignCalendarRequest;
import com.linkwee.api.response.act.BountyDetailResponse;
import com.linkwee.api.response.act.SignRecordResponse;
import com.linkwee.api.response.act.SignStatisticsResponse;
import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.core.base.api.ErrorResponse;
import com.linkwee.core.base.api.PaginatorRequest;
import com.linkwee.core.base.api.PaginatorResponse;
import com.linkwee.core.util.StringUtils;
import com.linkwee.web.model.ActSignInfo;
import com.linkwee.web.model.ActSignRecord;
import com.linkwee.web.model.ActSignTransferRecord;
import com.linkwee.web.model.CrmUserInfo;
import com.linkwee.web.model.acc.AcAccountRecharge;
import com.linkwee.web.service.AcAccountBindService;
import com.linkwee.web.service.ActSignInfoService;
import com.linkwee.web.service.ActSignRecordService;
import com.linkwee.web.service.ActSignTransferRecordService;
import com.linkwee.web.service.CimProductInvestRecordService;
import com.linkwee.web.service.CrmUserInfoService;
import com.linkwee.xoss.api.AppRequestHead;
import com.linkwee.xoss.api.BaseController;
import com.linkwee.xoss.helper.JsonWebTokenHepler;
import com.linkwee.xoss.util.AppResponseUtil;
import com.linkwee.xoss.util.AppUtils;
import com.linkwee.xoss.util.RequestLogging;
import com.linkwee.xoss.util.ResponseUtil;

/**
 * 
 * 描述：签到
 * @author hxb
 * @date 2016年7月26日 下午6:45:15 
 * Copyright (c) 深圳市前海领会科技有限公司
 */
@Controller
@RequestMapping(value = "/api/sign")
@RequestLogging("签到")
public class SignController extends BaseController {

	private static final Logger LOGGER = LoggerFactory.getLogger(SignController.class);
	
	@Resource
	private ActSignInfoService signInfoService;
	@Resource
	private ActSignRecordService signRecordService;
	@Resource
	private ActSignTransferRecordService signTransferRecordService;
	@Resource
	private CimProductInvestRecordService investRecordService;
	@Resource
	private AcAccountBindService accountbindService;
	@Resource
	private CrmUserInfoService userInfoService;
	
	/**
	 * 签到信息
	 * @param head
	 * @return
	 */
	@RequestMapping(value="/info/4.5.1")
	@ResponseBody
	@RequestLogging("签到信息")
	public BaseResponse info(AppRequestHead head) {
		LOGGER.info("签到信息请求参数AppRequestHead = {}" ,JSON.toJSONString(head));
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		Integer appType = AppUtils.getAppType(head.getOrgNumber()).getKey();
		if(StringUtils.isBlank(userId) || userId.equals("undefined") || appType == null){
			return new ErrorResponse("100002","参数错误");
		}
		Map<String,Object> resp = new HashMap<String,Object>();
		boolean hasSigned = false;
		ActSignRecord signRecord = signRecordService.todaySign(userId,appType);
		if(signRecord != null){
			hasSigned = true;
			
			int times = 0;
			if(signRecord.getTimesAmount() != null){
				times = signRecord.getTimesAmount().divide(signRecord.getSignAmount()).toBigInteger().intValue();
			}
			resp.put("times", times);
			resp.put("signInfo", signRecord);
		}
		int consecutiveDays = signInfoService.consecutiveDays(userId,appType);
		resp.put("hasSigned", hasSigned);
		resp.put("consecutiveDays", consecutiveDays);
		return AppResponseUtil.getSuccessResponse(resp);	
	}
	
	/**
	 * 签到
	 * @param head
	 * @return
	 */
	@RequestMapping(value="/sign/4.5.1")
	@ResponseBody
	@RequestLogging("签到")
	public BaseResponse sign(AppRequestHead head) {
		LOGGER.info("签到请求参数AppRequestHead = {}" ,JSON.toJSONString(head));
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		Integer appType = AppUtils.getAppType(head.getOrgNumber()).getKey();
		if(StringUtils.isBlank(userId) || userId.equals("undefined") || appType == null){
			return new ErrorResponse("100002","参数错误");
		}
		BaseResponse baseResponse = signRecordService.sign(userId,appType);
		return baseResponse;
	}
	
	/**
	 * 分享信息
	 * @param head
	 * @return
	 */
	@RequestMapping(value="/share/info/4.5.1")
	@ResponseBody
	@RequestLogging("分享信息")
	public BaseResponse shareInfo(AppRequestHead head) {
		LOGGER.info("分享信息请求参数AppRequestHead = {}" ,JSON.toJSONString(head));
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		Integer appType = AppUtils.getAppType(head.getOrgNumber()).getKey();
		if(StringUtils.isBlank(userId) || userId.equals("undefined") || appType == null){
			return new ErrorResponse("100002","参数错误");
		}
		ActSignRecord signRecord = signRecordService.todaySign(userId,appType);
		String shareDesc = "0.00";
		if(signRecord != null){
			shareDesc = signRecord.getSignAmount().setScale(2, BigDecimal.ROUND_HALF_UP).toString();
		}
		Map<String,Object> resp = new HashMap<String,Object>();
		resp.put("shareDesc", shareDesc);
		return AppResponseUtil.getSuccessResponse(resp);
	}
	
	/**
	 * 分享
	 * @param head
	 * @return
	 */
	@RequestMapping(value="/share/prize/4.5.1")
	@ResponseBody
	@RequestLogging("分享")
	public BaseResponse share(AppRequestHead head) {
		LOGGER.info("分享请求参数AppRequestHead = {}" ,JSON.toJSONString(head));
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		Integer appType = AppUtils.getAppType(head.getOrgNumber()).getKey();
		if(StringUtils.isBlank(userId) || userId.equals("undefined") || appType == null){
			return new ErrorResponse("100002","参数错误");
		}
		BaseResponse baseResponse = signRecordService.share(userId,appType);
		return baseResponse;
	}
	
	/**
	 * 签到统计
	 * @param head
	 * @return
	 */
	@RequestMapping(value="/statistics/4.5.1")
	@ResponseBody
	@RequestLogging("签到统计")
	public BaseResponse statistics(AppRequestHead head,String userId,String mobile) {
		if(StringUtils.isBlank(userId) || userId.equals("undefined")){
			userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());		
		}
		LOGGER.info("签到统计请求参数AppRequestHead = {}" ,JSON.toJSONString(head));
		LOGGER.info("签到统计请求userid={}",userId);
		LOGGER.info("签到统计请求mobile={}",mobile);
		CrmUserInfo userInfo = new CrmUserInfo();
		if(StringUtils.isBlank(userId) || userId.equals("undefined")){
			userInfo.setMobile(mobile);
		}else{
			userInfo.setUserId(userId);
		}
		userInfo = userInfoService.selectOne(userInfo);
		if(userInfo == null){
			return new ErrorResponse("100002","参数错误");
		}
		userId = userInfo.getUserId();
		Integer appType = AppUtils.getAppType(head.getOrgNumber()).getKey();
		if(StringUtils.isBlank(userId) || userId.equals("undefined") || appType == null){
			return new ErrorResponse("100002","参数错误");
		}
		ActSignInfo signInfo = new ActSignInfo();
		signInfo.setUserId(userId);
		signInfo.setUserType(appType);
		signInfo = signInfoService.selectOne(signInfo);
		SignStatisticsResponse response = new SignStatisticsResponse();
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
			response.setUserName(userName);
		}
		if(signInfo != null){
			ActSignRecord signRecord = signRecordService.queryLatestSign(userId,appType);
			BigDecimal signRecordAmount = investRecordService.querySignRecordAmount460(userId,signInfo.getLastestTransferTime());
			//投资剩余可转金额
			BigDecimal investTransferBouns = signRecordAmount.multiply(new BigDecimal("10")).divide(new BigDecimal(30000),2,BigDecimal.ROUND_DOWN);
			//剩余奖励金
			BigDecimal signTransferBouns = signInfo.getSignAmount().subtract(signInfo.getTransferAmount());
			BigDecimal transferBouns;
			if(investTransferBouns.compareTo(signTransferBouns) >= 0){
				transferBouns = signTransferBouns;
			}else{
				transferBouns = investTransferBouns;
			}
			if(signTransferBouns.compareTo(new BigDecimal(10)) < 0){
				response.setDissatisfyDescription("最低转出金额10元");
			}else if(investTransferBouns.compareTo(new BigDecimal(10)) < 0){
				response.setDissatisfyDescription("需投资期限＞30天,金额＞30000元的网贷产品 ");
			}
			response.setTotalBouns(signInfo.getSignAmount());
			response.setLeftBouns(signTransferBouns);
			response.setTransferBouns(transferBouns);
			response.setTransferedBouns(signInfo.getTransferAmount());
			if(signRecord != null){
				response.setFirstSignTime(signRecord.getSignTime());
			}
		}else{
			response.setTotalBouns(new BigDecimal(0));
			response.setLeftBouns(new BigDecimal(0));
			response.setTransferBouns(new BigDecimal(0));
			response.setTransferedBouns(new BigDecimal(0));
			response.setDissatisfyDescription("最低转出金额10元");
		}		
		return AppResponseUtil.getSuccessResponse(response);
	}
	
	/**
	 * 奖励金转账户
	 * @param head
	 * @return
	 */
	@RequestMapping(value="/bouns/transfer/4.5.1")
	@ResponseBody
	@RequestLogging("奖励金转账户")
	public BaseResponse bounsTransfer(AppRequestHead head) {
		LOGGER.info("奖励金转账户请求参数AppRequestHead = {}" ,JSON.toJSONString(head));
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		Integer appType = AppUtils.getAppType(head.getOrgNumber()).getKey();
		if(StringUtils.isBlank(userId) || userId.equals("undefined") || appType == null){
			return new ErrorResponse("100002","参数错误");
		}
		ActSignInfo signInfo = new ActSignInfo();
		signInfo.setUserId(userId);
		signInfo.setUserType(appType);
		signInfo = signInfoService.selectOne(signInfo);
		if(signInfo != null){
			/*ActSignRecord signRecord = signRecordService.queryLatestSign(userId,appType);
			BigDecimal signRecordAmount = investRecordService.querySignRecordAmount(userId,signRecord.getSignTime());
			BigDecimal transferBouns = new BigDecimal(signRecordAmount.divide(new BigDecimal(10000)).toBigInteger().multiply(new BigInteger("30"))).subtract(signInfo.getTransferAmount());*/
			
			BigDecimal signRecordAmount = investRecordService.querySignRecordAmount460(userId,signInfo.getLastestTransferTime());
			//投资剩余可转金额
			BigDecimal transferBouns = signRecordAmount.multiply(new BigDecimal("10")).divide(new BigDecimal(30000),2,BigDecimal.ROUND_DOWN);
			
			if(transferBouns.compareTo(signInfo.getSignAmount().subtract(signInfo.getTransferAmount())) >= 0){
				transferBouns = signInfo.getSignAmount().subtract(signInfo.getTransferAmount());
			}
			if(transferBouns.compareTo(new BigDecimal(0)) > 0){
				ActSignTransferRecord signTransferRecord = new ActSignTransferRecord();
				signTransferRecord.setTransferId(StringUtils.getUUID());
				signTransferRecord.setUserId(userId);
				signTransferRecord.setUserType(appType);
				signTransferRecord.setTransferAmount(transferBouns);
				signTransferRecord.setTransferTime(new Date());
				signTransferRecord.setTransferType(1);
				signTransferRecordService.insert(signTransferRecord);
				ActSignInfo temp = new ActSignInfo();
				temp.setUserId(userId);
				temp.setUserType(appType);
				temp.setTransferAmount(transferBouns);
				signInfoService.updateSignInfo(temp,2);
				//TODO 转账
				AcAccountRecharge recharge = new AcAccountRecharge();
				recharge.setRedpacketId(signTransferRecord.getTransferId());
				recharge.setTransAmount(transferBouns);
				recharge.setUserId(userId);
				recharge.setUserType(appType);
				recharge.setTransType(17);
				recharge.setRemark("奖励金转账户");
				try {
					accountbindService.accountRecharge(recharge);
				} catch (Exception e) {
					LOGGER.info("奖励金转账户充值失败，exception={}",JSON.toJSONString(e));
				}
			}else{
				return new ErrorResponse("100022","奖励金转账户不能等于0");
			}
			Map<String,Object> resp = new HashMap<String,Object>();
			resp.put("transferBouns", transferBouns.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
			return AppResponseUtil.getSuccessResponse(resp);
		}else{
			return new ErrorResponse("100023","奖励金转账户为空");
		}	
	}
	
	/**
	 * 签到记录
	 * @param head
	 * @return
	 */
	@RequestMapping(value="/records/pageList/4.5.1")
	@ResponseBody
	@RequestLogging("签到记录")
	public BaseResponse recordPageList(AppRequestHead head,PaginatorRequest request) {
		LOGGER.info("签到记录请求参数AppRequestHead = {}" ,JSON.toJSONString(head));
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		Integer appType = AppUtils.getAppType(head.getOrgNumber()).getKey();
		if(StringUtils.isBlank(userId) || userId.equals("undefined") || appType == null){
			return new ErrorResponse("100002","参数错误");
		}
		PaginatorResponse<SignRecordResponse> response = signRecordService.querySignRecords(userId,appType,request);	
		return AppResponseUtil.getSuccessResponse(response);
	}
	
	/**
	 * 签到日历
	 * @param head
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/calendar/4.5.1")
	@ResponseBody
	@RequestLogging("签到日历")
	public BaseResponse calendar(AppRequestHead head,SignCalendarRequest request,BindingResult result) {
		LOGGER.info("签到日历请求参数AppRequestHead = {}" ,JSON.toJSONString(head));
		if (ResponseUtil.existsParamsError(result)) {
			return ResponseUtil.getErrorParams(result);
		}
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		Integer appType = AppUtils.getAppType(head.getOrgNumber()).getKey();
		if(StringUtils.isBlank(userId) || userId.equals("undefined") || appType == null){
			return new ErrorResponse("100002","参数错误");
		}
		List<String> calendar = signRecordService.querySignCalendar(userId,appType,request);	
		return AppResponseUtil.getSuccessResponse(calendar);
	}
	
	/**
	 * 奖励金明细
	 * @param head
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/bounty/detail/4.6.0")
	@ResponseBody
	@RequestLogging("奖励金明细")
	public BaseResponse bountyDetailPageList(AppRequestHead head,PaginatorRequest request) {
		LOGGER.info("奖励金明细请求参数AppRequestHead = {}" ,JSON.toJSONString(head));
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		Integer appType = AppUtils.getAppType(head.getOrgNumber()).getKey();
		if(StringUtils.isBlank(userId) || userId.equals("undefined") || appType == null){
			return new ErrorResponse("100002","参数错误");
		}
		PaginatorResponse<BountyDetailResponse> response = signRecordService.queryBountyDetails(userId,appType,request);	
		return AppResponseUtil.getSuccessResponse(response);
	}
	
}
