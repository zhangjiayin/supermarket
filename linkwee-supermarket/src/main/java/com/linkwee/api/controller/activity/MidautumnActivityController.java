package com.linkwee.api.controller.activity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.linkwee.api.response.act.InvestRankingListResponse;
import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.core.base.api.PaginatorRequest;
import com.linkwee.core.base.api.PaginatorResponse;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.core.util.DateUtils;
import com.linkwee.core.util.WebUtil;
import com.linkwee.web.enums.PlatformEnum;
import com.linkwee.web.model.ActMidautumnTask;
import com.linkwee.web.model.ActivityList;
import com.linkwee.web.request.ActivityPageListRequest;
import com.linkwee.web.service.ActMidautumnTaskService;
import com.linkwee.web.service.ActivityListService;
import com.linkwee.web.service.CrmOrgAcctRelService;
import com.linkwee.xoss.api.AppRequestHead;
import com.linkwee.xoss.api.BaseController;
import com.linkwee.xoss.helper.JsonWebTokenHepler;
import com.linkwee.xoss.util.AppResponseUtil;
import com.linkwee.xoss.util.AppUtils;
import com.linkwee.xoss.util.RequestLogging;

 /**
 * 
 * @描述： ActMidautumnTaskController控制器
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2017年09月20日 10:12:11
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Controller
@RequestMapping(value = "/api/midautumnactivity")
@RequestLogging("月饼节活动控制器")
public class MidautumnActivityController extends BaseController{

	private static final Logger LOGGER = LoggerFactory.getLogger(MidautumnActivityController.class);

	@Resource
	private ActMidautumnTaskService actMidautumnTaskService;
	
	@Resource
	private CrmOrgAcctRelService orgAcctRelService;
	
	@Resource
	private ActivityListService activityListService;
	
	/**
	 * 月饼节活动任务完成状态
	 * @param head
	 * @return
	 */
	@RequestLogging("月饼节活动任务完成状态")
	@RequestMapping("finishStatus")
	@ResponseBody
	public BaseResponse finishStatus(AppRequestHead head) {
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		ActivityList selectCondition = new ActivityList();
		selectCondition.setActivityCode("midautumn_rank");
		ActivityList activity = activityListService.selectActiveOne(selectCondition);
		if(activity != null){
			ActMidautumnTask req = new ActMidautumnTask();
			req.setUserId(userId);
			ActMidautumnTask rlt = actMidautumnTaskService.selectOne(req);
			if(rlt == null){
				rlt = new ActMidautumnTask();
				boolean regFundStatus = orgAcctRelService.hasRegFund(userId);
				if(regFundStatus){
					rlt.setFundRegStatus(1);
				}else{
					rlt.setFundRegStatus(0);
				}
				rlt.setInvestStatus(0);
				rlt.setInviteCfpStatus(0);
				rlt.setTaskAllStatus(0);
			}else if(rlt.getFundRegStatus() == 0){
				boolean regFundStatus = orgAcctRelService.hasRegFund(userId);
				if(regFundStatus){
					rlt.setFundRegStatus(1);
				}
			}
			rlt.setUserId(null);
			rlt.setId(null);
			rlt.setCreateTime(null);
			rlt.setLastUpdateTime(null);
			return AppResponseUtil.getSuccessResponse(rlt);
		}else{
			return AppResponseUtil.getErrorBusi("10086", "活动未开始");
		}
		
	}
	
	/**
	 * 排行榜--月饼节活动--榜单
	 * @param head
	 * @param paginatorRequest
	 * @return
	 */
	@RequestMapping("/rankingList/pageList")
    @ResponseBody
	@RequestLogging("排行榜--月饼节活动--榜单")
    public BaseResponse rankingListPageList(AppRequestHead head,PaginatorRequest paginatorRequest){				
		ActivityList selectCondition = new ActivityList();
		selectCondition.setActivityCode("midautumn_rank");
		ActivityList activity = activityListService.selectOne(selectCondition);
		if(activity != null){	
			Page<InvestRankingListResponse> page = new Page<InvestRankingListResponse>(paginatorRequest.getPageIndex(), paginatorRequest.getPageSize());	
			PaginatorResponse<InvestRankingListResponse> rankingList = actMidautumnTaskService.investRankingList(DateUtils.format(activity.getStartDate(), DateUtils.FORMAT_LONG),DateUtils.format(activity.getEndDate(), DateUtils.FORMAT_LONG),null,page);
			return AppResponseUtil.getSuccessResponse(rankingList);
		}else{
			return AppResponseUtil.getErrorBusi("10086", "活动不存在");
		}
    }
	
	/**
	 * 子活动列表
	 * @param req
	 * @param head
	 * @return
	 */
	@RequestMapping("/subactivity/list")
    @ResponseBody
	@RequestLogging("子活动--列表")
    public BaseResponse subActivityList(@Valid ActivityPageListRequest req,AppRequestHead head){				
		LOGGER.info("平台活动列表请求参数ActivityPageListRequest = {} , AppRequestHead = {}" ,JSON.toJSONString(req),JSON.toJSONString(head));
		Map<String,Object> conditions = new HashMap<String, Object>(); //筛选条件
		Integer appType = AppUtils.getAppType(head.getOrgNumber()).getKey();		
		conditions.put("appType",appType);	
		conditions.put("activityPlatform", "猎财大师");
		List<ActivityList> datas = activityListService.querySubActivitiesList(conditions);
		Map<String,String> params = new HashMap<String,String>();
		if(StringUtils.isNotBlank(head.getToken())){
			PlatformEnum platform =  AppUtils.getPlatform(head.getOrgNumber());
			if(PlatformEnum.IOS.equals(platform)||PlatformEnum.ANDROID.equals(platform)){
				params.put("token",head.getToken());
			}
		}
		if(!datas.isEmpty()&&datas.size()>0){
			for(ActivityList activity:datas){
				activity.setLinkUrl(WebUtil.creatUrl(activity.getLinkUrl(), params));
			}
		}
		
		return AppResponseUtil.getSuccessResponse(datas);
    }
}
