package com.linkwee.api.controller.activity;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.core.base.api.ErrorResponse;
import com.linkwee.core.constant.ApiInvokeLogConstant;
import com.linkwee.core.util.DateUtils;
import com.linkwee.core.util.StringUtils;
import com.linkwee.web.model.ActCfpDoubleElevenActivity;
import com.linkwee.web.model.ActivityList;
import com.linkwee.web.model.SysApiInvokeLog;
import com.linkwee.web.service.ActCfpDoubleElevenActivityService;
import com.linkwee.web.service.ActivityListService;
import com.linkwee.web.service.SysApiInvokeLogService;
import com.linkwee.xoss.api.AppRequestHead;
import com.linkwee.xoss.api.BaseController;
import com.linkwee.xoss.helper.JsonWebTokenHepler;
import com.linkwee.xoss.util.AppResponseUtil;
import com.linkwee.xoss.util.AppUtils;
import com.linkwee.xoss.util.RequestLogging;

 /**
 * 
 * @描述： ActCfpDoubleElevenActivityController控制器
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2017年10月23日 11:23:07
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Controller
@RequestMapping(value = "/api/activity/doubleEleven")
@RequestLogging("双十一活动控制器")
public class CfpDoubleElevenActivityController extends BaseController{

	private static final Logger LOGGER = LoggerFactory.getLogger(CfpDoubleElevenActivityController.class);

	@Resource
	private ActCfpDoubleElevenActivityService actCfpDoubleElevenActivityService;
	@Resource
	private ActivityListService activityListService;
	@Resource
	private SysApiInvokeLogService sysApiInvokeLogService;
	
	@RequestLogging("双十一活动任务完成状态")
	@RequestMapping("finishStatus/4.5.0")
	@ResponseBody
	public BaseResponse finishStatus(AppRequestHead head) {
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		ActivityList selectCondition = new ActivityList();
		selectCondition.setActivityCode("double_eleven");
		ActivityList activity = activityListService.selectOne(selectCondition);
		if(activity != null){
			ActCfpDoubleElevenActivity req = new ActCfpDoubleElevenActivity();
			req.setUserId(userId);
			ActCfpDoubleElevenActivity rlt = actCfpDoubleElevenActivityService.selectOne(req);
			if(rlt == null){
				rlt = new ActCfpDoubleElevenActivity();
				boolean fundStatus = actCfpDoubleElevenActivityService.hasSaleFund(userId);
				if(fundStatus){
					rlt.setFundStatus(1);
				}else{
					rlt.setFundStatus(0);
				}
				rlt.setInsuranceStatus(0);
				rlt.setSaleNum(0);
			}else if(rlt.getFundStatus() == 0){
				boolean fundStatus = actCfpDoubleElevenActivityService.hasSaleFund(userId);
				if(fundStatus){
					rlt.setFundStatus(1);
				}
			}
			rlt.setUserId(null);
			rlt.setId(null);
			rlt.setCreateTime(null);
			rlt.setLastUpdateTime(null);
			return AppResponseUtil.getSuccessResponse(rlt);
		}else{
			return AppResponseUtil.getErrorBusi("10086", "活动不存在");
		}	
	}
	
	@RequestMapping(value="/hasNewDoubleEleven/4.5.0")
	@ResponseBody
	@RequestLogging("是否有新的第十一笔订单")
	public BaseResponse hasNewDoubleEleven(AppRequestHead head) {
		LOGGER.info("是否有新的第十一笔订单请求参数AppRequestHead = {}" ,JSON.toJSONString(head));
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		Integer appType = AppUtils.getAppType(head.getOrgNumber()).getKey();
		if(StringUtils.isBlank(userId) || userId.equals("undefined") || appType == null){
			return new ErrorResponse("100002","参数错误");
		}
		SysApiInvokeLog apiInvokeLog = sysApiInvokeLogService.queryApiInvokeLog(ApiInvokeLogConstant.NAME_NEW_DOUBLE_ELEVEN, userId, appType);
		ActCfpDoubleElevenActivity doubleElevenActivity = new ActCfpDoubleElevenActivity();
		doubleElevenActivity.setUserId(userId);
		doubleElevenActivity = actCfpDoubleElevenActivityService.selectOne(doubleElevenActivity);
		sysApiInvokeLogService.updateApiInvokeLog(ApiInvokeLogConstant.NAME_NEW_DOUBLE_ELEVEN, userId,appType);
		Map<String,Object> resp = new HashMap<String,Object>();
		Integer compareResult;
		if(doubleElevenActivity == null && apiInvokeLog == null){
			compareResult = 0;
			resp.put("hasNewDoubleEleven", compareResult);
		}else if(apiInvokeLog == null){
			compareResult = 1;
			resp.put("hasNewDoubleEleven", compareResult);		
		}else if(doubleElevenActivity == null){
			compareResult = 0;
			resp.put("hasNewDoubleEleven", compareResult);		
		}else{
			compareResult =DateUtils.compareDate(apiInvokeLog.getChgTime(), doubleElevenActivity.getElevenOrderTime());
			if(compareResult < 0){
				compareResult = 1;
			}
			else {
				compareResult = 0;
			}	
			resp.put("hasNewDoubleEleven", compareResult);			
		}
		return AppResponseUtil.getSuccessResponse(resp);
	}
	
}
