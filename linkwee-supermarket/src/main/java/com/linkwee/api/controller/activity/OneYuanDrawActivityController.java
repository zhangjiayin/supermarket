package com.linkwee.api.controller.activity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.linkwee.api.activity.BaseLottery;
import com.linkwee.api.activity.request.ReceivingAddressRequest;
import com.linkwee.api.activity.response.BigWheelDrawResponse;
import com.linkwee.api.activity.response.OneYuanDrawRecordResponse;
import com.linkwee.api.activity.response.UserWinningRecordResponse;
import com.linkwee.api.activity.utils.OneYuanDrawUtil;
import com.linkwee.api.response.activity.FortunePrizeResponse;
import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.core.base.api.PaginatorRequest;
import com.linkwee.core.base.api.PaginatorResponse;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.core.util.StringUtils;
import com.linkwee.web.model.ActSignInfo;
import com.linkwee.web.model.ActWheelWinningRecord;
import com.linkwee.web.model.ActivityList;
import com.linkwee.web.model.CrmUserActivityReceivingAddress;
import com.linkwee.web.model.CrmUserInfo;
import com.linkwee.web.service.AcAccountBindService;
import com.linkwee.web.service.ActOneYuanDrawRecordService;
import com.linkwee.web.service.ActSignInfoService;
import com.linkwee.web.service.ActWheelWinningRecordService;
import com.linkwee.web.service.ActivityListService;
import com.linkwee.web.service.CrmUserActivityReceivingAddressService;
import com.linkwee.web.service.CrmUserInfoService;
import com.linkwee.xoss.api.AppRequestHead;
import com.linkwee.xoss.helper.JsonWebTokenHepler;
import com.linkwee.xoss.util.AppResponseUtil;
import com.linkwee.xoss.util.AppUtils;
import com.linkwee.xoss.util.RequestLogging;
import com.mchange.v2.codegen.bean.BeangenUtils;

@Controller
@RequestMapping("/api/activity/oneyuandraw")
public class OneYuanDrawActivityController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(OneYuanDrawActivityController.class);
	
	@Resource
    private CrmUserInfoService crmUserInfoService;	
	@Resource
	private ActWheelWinningRecordService actWheelWinningRecordService;	
	@Resource
	private ActivityListService activityListService;
	@Resource
	private AcAccountBindService accountbindService; 
	@Resource
	private ActSignInfoService signInfoService;
	@Resource
	private ActOneYuanDrawRecordService oneYuanDrawRecordService;
	@Resource
	private CrmUserActivityReceivingAddressService activityAddressService;
	
	/**
	 * 抽奖次数
	 * @param head
	 * @param result
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/prize/times")
    @ResponseBody
	@RequestLogging("一元抽奖--抽奖次数")
    public BaseResponse prizeTimes(AppRequestHead head,BindingResult result){
		
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());		
		if(StringUtils.isBlank(userId) || userId.equals("undefined")){
			return AppResponseUtil.getErrorToken();
		}
		
		ActivityList selectCondition = new ActivityList();
		selectCondition.setActivityCode("one_yuan_draw");
		ActivityList activity = activityListService.selectOne(selectCondition);

		if(activity != null ){
			Double liecaiBalance = accountbindService.queryAccountBalance(userId);
			ActSignInfo signInfo = signInfoService.queryInfoByUserId(userId);
			BigDecimal signLeftBouns = new BigDecimal("0");
			if(signInfo != null){
				signLeftBouns = signInfo.getSignAmount().subtract(signInfo.getTransferAmount());
			}	
			
			String signTransferBouns = signLeftBouns.setScale(2, BigDecimal.ROUND_DOWN).toString();
			Integer liecaiTimes = (int) Math.floor(liecaiBalance);
			Integer signTimes = signLeftBouns.intValue();
			Integer leftTimes = liecaiTimes + signTimes;
			if(leftTimes < 0){
				LOGGER.info("已抽取次数大于能抽取的总次数");
				leftTimes = 0;
			}
			int resultInteger = oneYuanDrawRecordService.queryMaxId();
			int roundTime = (resultInteger / 1800) * 2 + 1;
			int leftCount = resultInteger % 1800;
			if(leftCount > 600){
				roundTime += 1;
				leftCount -= 600;
			}
			Map<String,Object> rlt = new HashMap<String,Object>();
			rlt.put("leftTimes", leftTimes);
			rlt.put("signTransferBouns", signTransferBouns);
			rlt.put("liecaiBalance", liecaiBalance);
			rlt.put("roundTime", roundTime);
			rlt.put("count", leftCount);
			return AppResponseUtil.getSuccessResponse(rlt);
		}else{
			return AppResponseUtil.getErrorBusi("10086", "活动不存在或已过期");
		}
						
    }
	
	/**
	 * 抽一次
	 * @param head
	 * @param result
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/prize/one")
    @ResponseBody
	@RequestLogging("一元抽奖--抽一次")
    public BaseResponse prizeOne(AppRequestHead head,BindingResult result){
		
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());		
		if(StringUtils.isBlank(userId) || userId.equals("undefined")){
			return AppResponseUtil.getErrorToken();
		}
		Integer userType = AppUtils.isChannelApp(head.getOrgNumber()) ? 1 : 2;	
		
		ActivityList selectCondition = new ActivityList();
		selectCondition.setActivityCode("one_yuan_draw");
		ActivityList activity = activityListService.selectOne(selectCondition);

		if(activity != null ){
			Double liecaiBalance = accountbindService.queryAccountBalance(userId);
			ActSignInfo signInfo = signInfoService.queryInfoByUserId(userId);
			BigDecimal signLeftBouns = new BigDecimal("0");
			if(signInfo != null){
				signLeftBouns = signInfo.getSignAmount().subtract(signInfo.getTransferAmount());
			}
			Integer liecaiTimes = (int) Math.floor(liecaiBalance);
			Integer signTimes = signLeftBouns.intValue();
			Integer leftTimes = liecaiTimes + signTimes;
			if(leftTimes <= 0){
				LOGGER.info("剩余次数为0,不能继续抽奖");
				return AppResponseUtil.getErrorBusi("10088", "剩余次数为0,不能继续抽奖");
			}
			BaseLottery baseLottery = OneYuanDrawUtil.generateAward();
			BigWheelDrawResponse bigWheelDrawResponse = new BigWheelDrawResponse(); 
			bigWheelDrawResponse.setPrizeId(baseLottery.getId());
			
			CrmUserInfo crmUserInfo = crmUserInfoService.queryUserInfoByUserId(userId);
			try {
				String bizId = StringUtils.getUUID();
				FortunePrizeResponse response = oneYuanDrawRecordService.insertOneYuanDrawRecord(baseLottery,1,userId,crmUserInfo.getMobile(),userType,activity.getId().toString(),"一元抽奖活动",signTimes,liecaiTimes,bizId,1);
				bigWheelDrawResponse.setFortunePrizeResponse(response);
				//抽奖的同时发生转账等操作，可能引起数据不准确
				liecaiBalance = accountbindService.queryAccountBalance(userId);
				signInfo = signInfoService.queryInfoByUserId(userId);
				if(signInfo != null){
					signLeftBouns = signInfo.getSignAmount().subtract(signInfo.getTransferAmount());
				}
				liecaiTimes = (int) Math.floor(liecaiBalance);
				signTimes = signLeftBouns.intValue();
				leftTimes = liecaiTimes + signTimes;
				bigWheelDrawResponse.setLeftTimes(leftTimes);
			} catch (Exception e) {
				LOGGER.warn("记录一元抽奖记录失败");
				return AppResponseUtil.getErrorBusi("10099", "记录一元抽奖记录失败");
			}
			return AppResponseUtil.getSuccessResponse(bigWheelDrawResponse);
		}else{
			return AppResponseUtil.getErrorBusi("10086", "活动不存在或已过期");
		}
						
    }
	
	/**
	 * 抽十次
	 * @param head
	 * @param result
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/prize/ten")
    @ResponseBody
	@RequestLogging("一元抽奖--抽十次")
    public BaseResponse prizeTen(AppRequestHead head,BindingResult result){
		
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());		
		if(StringUtils.isBlank(userId) || userId.equals("undefined")){
			return AppResponseUtil.getErrorToken();
		}
		Integer userType = AppUtils.isChannelApp(head.getOrgNumber()) ? 1 : 2;	
		
		ActivityList selectCondition = new ActivityList();
		selectCondition.setActivityCode("one_yuan_draw");
		ActivityList activity = activityListService.selectOne(selectCondition);

		if(activity != null ){
			Double liecaiBalance = accountbindService.queryAccountBalance(userId);
			ActSignInfo signInfo = signInfoService.queryInfoByUserId(userId);
			BigDecimal signLeftBouns = new BigDecimal("0");
			if(signInfo != null){
				signLeftBouns = signInfo.getSignAmount().subtract(signInfo.getTransferAmount());
			}
			Integer liecaiTimes = (int) Math.floor(liecaiBalance);
			Integer signTimes = signLeftBouns.intValue();
			Integer leftTimes = liecaiTimes + signTimes;
			if(leftTimes < 10){
				LOGGER.info("剩余次数小于10,不能采用该方式抽奖");
				return AppResponseUtil.getErrorBusi("10089", "剩余次数小于10,不能采用该方式抽奖");
			}else {
				
				List<BaseLottery> baseLotteryList = OneYuanDrawUtil.generateAwards(10);
				BigWheelDrawResponse bigWheelDrawResponse = new BigWheelDrawResponse(); 
				List<Integer> prizeIdList = new ArrayList<Integer>();
				for(BaseLottery baseLottery : baseLotteryList){
					prizeIdList.add(baseLottery.getId());
				}
				bigWheelDrawResponse.setPrizeIdList(prizeIdList);
				CrmUserInfo crmUserInfo = crmUserInfoService.queryUserInfoByUserId(userId);
				try {
					FortunePrizeResponse response = oneYuanDrawRecordService.batchInsertOneYuanDrawRecord(baseLotteryList,1,userId,crmUserInfo.getMobile(),userType,activity.getId().toString(),"一元抽奖活动",signTimes,liecaiTimes);
					bigWheelDrawResponse.setFortunePrizeResponse(response);
					//抽奖的同时发生转账等操作，可能引起数据不准确
					liecaiBalance = accountbindService.queryAccountBalance(userId);
					signInfo = signInfoService.queryInfoByUserId(userId);
					if(signInfo != null){
						signLeftBouns = signInfo.getSignAmount().subtract(signInfo.getTransferAmount());
					}
					liecaiTimes = (int) Math.floor(liecaiBalance);
					signTimes = signLeftBouns.intValue();
					leftTimes = liecaiTimes + signTimes;
					bigWheelDrawResponse.setLeftTimes(leftTimes);
				} catch (Exception e) {
					LOGGER.warn("记录一元抽奖记录失败--十连抽");
					return AppResponseUtil.getErrorBusi("10098", "记录一元抽奖记录失败--十连抽");
				}
				return AppResponseUtil.getSuccessResponse(bigWheelDrawResponse);
			}	
		}else{
			return AppResponseUtil.getErrorBusi("10086", "活动不存在或已过期");
		}
						
    }
	
	/**
	 * 所有抽奖记录
	 * @param head
	 * @param result
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/prize/record/all")
    @ResponseBody
	@RequestLogging("一元抽奖--所有抽奖记录")
    public BaseResponse prizeRecordAll(AppRequestHead head,BindingResult result){	
		ActivityList selectCondition = new ActivityList();
		selectCondition.setActivityCode("one_yuan_draw");
		ActivityList activity = activityListService.selectOne(selectCondition);

		if(activity != null ){
			List<OneYuanDrawRecordResponse> recordList = oneYuanDrawRecordService.queryOneYuanDrawRecord(activity.getId().toString());
			for(OneYuanDrawRecordResponse responseTemp : recordList){
				String mobile = responseTemp.getMobile().substring(0, 3)+"****"+responseTemp.getMobile().substring(7);
				responseTemp.setMobile(mobile);
			}
			return AppResponseUtil.getSuccessResponse(recordList);
		}else{
			return AppResponseUtil.getErrorBusi("10086", "活动不存在或已过期");
		}
						
    }
	
	/**
	 * 用户抽奖记录(分页)
	 * @param head
	 * @param result
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/prize/record/user/pageList")
    @ResponseBody
	@RequestLogging("一元抽奖--用户抽奖记录")
    public BaseResponse prizeRecordUserPageList(AppRequestHead head,PaginatorRequest paginatorRequest,Integer isfortunePrize){
		
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());		
		if(StringUtils.isBlank(userId) || userId.equals("undefined")){
			return AppResponseUtil.getErrorToken();
		}
		
		ActivityList selectCondition = new ActivityList();
		selectCondition.setActivityCode("one_yuan_draw");
		ActivityList activity = activityListService.selectOne(selectCondition);

		if(activity != null ){	
			Page<UserWinningRecordResponse> page = new Page<UserWinningRecordResponse>(paginatorRequest.getPageIndex(), paginatorRequest.getPageSize());		
			ActWheelWinningRecord actWheelWinningRecord = new ActWheelWinningRecord();
			actWheelWinningRecord.setUserId(userId);
			actWheelWinningRecord.setExtends1(activity.getId().toString());
			PaginatorResponse<UserWinningRecordResponse> actWheelWinningRecordList = oneYuanDrawRecordService.queryUserPrizeRecord(actWheelWinningRecord,isfortunePrize,page);			
			return AppResponseUtil.getSuccessResponse(actWheelWinningRecordList);
		}else{
			return AppResponseUtil.getErrorBusi("10086", "活动不存在或已过期");
		}
    }
	
	@RequestMapping("/prize/record/fortune")
    @ResponseBody
	@RequestLogging("一元抽奖-幸运奖品中奖记录")
    public BaseResponse fortunePrizeRecordPageList(AppRequestHead head,PaginatorRequest paginatorRequest){
		
		ActivityList selectCondition = new ActivityList();
		selectCondition.setActivityCode("one_yuan_draw");
		ActivityList activity = activityListService.selectOne(selectCondition);

		if(activity != null ){	
			Page<FortunePrizeResponse> page = new Page<FortunePrizeResponse>(paginatorRequest.getPageIndex(), paginatorRequest.getPageSize());		
			ActWheelWinningRecord actWheelWinningRecord = new ActWheelWinningRecord();
			actWheelWinningRecord.setExtends1(activity.getId().toString());
			PaginatorResponse<FortunePrizeResponse> actWheelWinningRecordList = oneYuanDrawRecordService.queryFortunePrizeRecord(actWheelWinningRecord,page);			
			return AppResponseUtil.getSuccessResponse(actWheelWinningRecordList);
		}else{
			return AppResponseUtil.getErrorBusi("10086", "活动不存在或已过期");
		}
    }
	
	@RequestMapping("/receiving/address")
    @ResponseBody
	@RequestLogging("一元抽奖-用户收件地址")
    public BaseResponse receivingAddress(AppRequestHead head){		
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());		
		if(StringUtils.isBlank(userId) || userId.equals("undefined")){
			return AppResponseUtil.getErrorToken();
		}
		CrmUserActivityReceivingAddress request = new CrmUserActivityReceivingAddress();
		request.setUserId(userId);
		List<CrmUserActivityReceivingAddress> resultList = activityAddressService.selectListByCondition(request);
		return AppResponseUtil.getSuccessResponse(resultList);
    }
	
	@RequestMapping("/receiving/address/update")
    @ResponseBody
	@RequestLogging("一元抽奖-修改用户收件地址")
    public BaseResponse receivingAddressUpdate(AppRequestHead head,@Valid ReceivingAddressRequest addressRequest,BindingResult result){	
		if(AppResponseUtil.existsParamsError(result)) {
	    	return AppResponseUtil.getErrorParams(result);
        }
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());		
		if(StringUtils.isBlank(userId) || userId.equals("undefined")){
			return AppResponseUtil.getErrorToken();
		}
		CrmUserActivityReceivingAddress request = new CrmUserActivityReceivingAddress();
		request.setUserId(userId);
		request.setType(addressRequest.getType());
		
		if(addressRequest.getType() == 1 || addressRequest.getType() == 2){
			request = activityAddressService.selectOne(request);
			if(request != null){
				BeanUtils.copyProperties(addressRequest, request);
				if(addressRequest.getType() == 1){
					request.setTypeName("邮寄地址");
				}else {
					request.setTypeName("爱奇艺账户");
				}
				activityAddressService.update(request);
			}else{
				request = new CrmUserActivityReceivingAddress();
				request.setUserId(userId);
				request.setType(addressRequest.getType());
				BeanUtils.copyProperties(addressRequest, request);
				if(addressRequest.getType() == 1){
					request.setTypeName("邮寄地址");
				}else {
					request.setTypeName("爱奇艺账户");
				}
				activityAddressService.insert(request);
			}
		}else{
			return AppResponseUtil.getErrorBusi("10091", "用户收件地址-收件类型不存在");
		}
		return AppResponseUtil.getSuccessResponse();
    }
	
}
