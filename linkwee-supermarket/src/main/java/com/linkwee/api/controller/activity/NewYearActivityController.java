package com.linkwee.api.controller.activity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.linkwee.api.activity.response.NewYearHelpStatusResponse;
import com.linkwee.api.activity.response.NewYearRankingListResponse;
import com.linkwee.api.activity.response.NewYearRewardResponse;
import com.linkwee.api.activity.utils.NewYearActivityUtil;
import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.core.constant.ActivityConstant;
import com.linkwee.core.util.DateUtils;
import com.linkwee.core.util.StringUtils;
import com.linkwee.web.model.ActivityList;
import com.linkwee.web.service.ActivityListService;
import com.linkwee.web.service.CrmCfplannerService;
import com.linkwee.xoss.api.AppRequestHead;
import com.linkwee.xoss.api.BaseController;
import com.linkwee.xoss.helper.JsonWebTokenHepler;
import com.linkwee.xoss.util.AppResponseUtil;
import com.linkwee.xoss.util.RequestLogging;

 /**
 * 
 * @描述： NewYearActivityController控制器
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2017年10月23日 11:23:07
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Controller
@RequestMapping(value = "/api/activity/newYear")
@RequestLogging("2017猎财大师春节新年活动控制器")
public class NewYearActivityController extends BaseController{

	private static final Logger LOGGER = LoggerFactory.getLogger(NewYearActivityController.class);
	
	@Resource
	private ActivityListService activityListService;
	@Resource
    private CrmCfplannerService crmCfplannerService;

	
	@RequestLogging("获奖列表")
	@RequestMapping("/reward/list")
	@ResponseBody
	public BaseResponse rewardList() {
		ActivityList act = activityListService.queryByCode(ActivityConstant.NEW_YEAR_HELP_CODE);
		if(act != null && act.getActivityCode() != null && act.getStartDate() != null && act.getEndDate() != null) {
			if(new Date().compareTo(act.getStartDate()) < 0) {
				return AppResponseUtil.getErrorBusi("error", "亲，活动还没开始哦");
			}
		} else {
			return AppResponseUtil.getErrorBusi("error", "亲，活动还没开始哦");
		}
		List<NewYearRewardResponse> resp = crmCfplannerService.queryForgeRewardData();
		return AppResponseUtil.getSuccessResponse(resp);	
	}
	
	@RequestLogging("助力状态")
	@RequestMapping("/help/status")
	@ResponseBody
	public BaseResponse helpStatus(AppRequestHead head,String userId) {
		if(StringUtils.isBlank(userId) || userId.equals("undefined")){
			userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());		
			if(StringUtils.isBlank(userId) || userId.equals("undefined")){
				return AppResponseUtil.getErrorToken();
			}
		}
		LOGGER.info("用户userid={}查看助力状态",userId);
		ActivityList act = activityListService.queryByCode(ActivityConstant.NEW_YEAR_HELP_CODE);
		NewYearHelpStatusResponse resp = new NewYearHelpStatusResponse();
		if(act != null){
			resp = activityListService.newYearHelpStatus(userId,DateUtils.format(act.getStartDate(), DateUtils.FORMAT_LONG),DateUtils.format(act.getEndDate(), DateUtils.FORMAT_LONG));
		}
		return AppResponseUtil.getSuccessResponse(resp);
	}
	
	@RequestLogging("业绩排行榜")
	@RequestMapping("/rankingList")
	@ResponseBody
	public BaseResponse rankingList() {
		ActivityList act = activityListService.queryByCode(ActivityConstant.NEW_YEAR_HELP_CODE);
		if(act != null && act.getActivityCode() != null && act.getStartDate() != null && act.getEndDate() != null) {
			if(new Date().compareTo(act.getStartDate()) < 0) {
				return AppResponseUtil.getErrorBusi("error", "亲，活动还没开始哦");
			}
		} else {
			return AppResponseUtil.getErrorBusi("error", "亲，活动还没开始哦");
		}
		BigDecimal newYearMaxSaleAmount = activityListService.newYearMaxSaleAmount(DateUtils.format(act.getStartDate(), DateUtils.FORMAT_LONG),DateUtils.format(act.getEndDate(), DateUtils.FORMAT_LONG));
		List<NewYearRankingListResponse> resp = NewYearActivityUtil.generateRankingList(newYearMaxSaleAmount,act.getStartDate(),act.getEndDate());
		return AppResponseUtil.getSuccessResponse(resp);
	}
	
}
