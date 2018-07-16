package com.linkwee.api.controller.activity;

import java.util.ArrayList;
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

import com.linkwee.api.response.act.InvestRankingListResponse;
import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.core.base.api.PaginatorRequest;
import com.linkwee.core.base.api.PaginatorResponse;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.core.util.DateUtils;
import com.linkwee.core.util.StringUtils;
import com.linkwee.web.model.ActivityList;
import com.linkwee.web.model.CrmCfplanner;
import com.linkwee.web.service.ActWheelWinningRecordService;
import com.linkwee.web.service.ActivityListService;
import com.linkwee.web.service.CrmCfplannerService;
import com.linkwee.web.service.CrmUserInfoService;
import com.linkwee.xoss.api.AppRequestHead;
import com.linkwee.xoss.helper.JsonWebTokenHepler;
import com.linkwee.xoss.util.AppResponseUtil;
import com.linkwee.xoss.util.RequestLogging;

@Controller
@RequestMapping("/api/activity/tcOnline")
@RequestLogging("投筹上线活动")
public class TCOnlineActivityController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TCOnlineActivityController.class);
	
	@Resource
    private CrmUserInfoService crmUserInfoService;
	
	@Resource
	private ActWheelWinningRecordService actWheelWinningRecordService;
	
	@Resource
	private ActivityListService activityListService;
	
	@Resource
	private CrmCfplannerService crmCfplannerService;		
	
	@RequestMapping("/rankingList/pageList")
    @ResponseBody
	@RequestLogging("排行榜--投筹上线--榜单")
    public BaseResponse rankingListPageList(AppRequestHead head,PaginatorRequest paginatorRequest){				
		ActivityList selectCondition = new ActivityList();
		selectCondition.setActivityCode("tc_online");
		ActivityList activity = activityListService.selectOne(selectCondition);
		if(activity != null){	
			Page<InvestRankingListResponse> page = new Page<InvestRankingListResponse>(paginatorRequest.getPageIndex(), paginatorRequest.getPageSize());	
			List<String> platformList = new ArrayList<String>(){{add("OPEN_TOUCHOU_WEB");}};
 			PaginatorResponse<InvestRankingListResponse> rankingList = actWheelWinningRecordService.subAndSelfInvestRankingList(DateUtils.format(activity.getStartDate(), DateUtils.FORMAT_LONG),DateUtils.format(activity.getEndDate(), DateUtils.FORMAT_LONG),platformList,page);
			return AppResponseUtil.getSuccessResponse(rankingList);
		}else{
			return AppResponseUtil.getErrorBusi("10086", "活动不存在或已过期");
		}
    }
	
	@RequestMapping("/rankingList/mySale")
    @ResponseBody
	@RequestLogging("排行榜--投筹上线--我的累计业绩")
    public BaseResponse rankingListMyRank(AppRequestHead head,BindingResult result){				
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());		
		if(StringUtils.isBlank(userId) || userId.equals("undefined")){
			return AppResponseUtil.getErrorToken();
		}
		CrmCfplanner cfplanner = crmCfplannerService.queryCfplannerByUserId(userId);
		if(cfplanner == null){
			return AppResponseUtil.getErrorBusi("10087", "活动对象为理财师");
		}
		ActivityList selectCondition = new ActivityList();
		selectCondition.setActivityCode("tc_online");
		ActivityList activity = activityListService.selectOne(selectCondition);
		if(activity != null ){
			List<String> platformList = new ArrayList<String>(){{add("OPEN_TOUCHOU_WEB");}};
			InvestRankingListResponse myRank = actWheelWinningRecordService.subAndSelfInvestMyRank(userId,DateUtils.format(activity.getStartDate(), DateUtils.FORMAT_LONG),DateUtils.format(activity.getEndDate(), DateUtils.FORMAT_LONG),platformList);			  
			Map<String,Object> rlt = new HashMap<String,Object>();
			rlt.put("investAmount", myRank.getInvestAmt());
			return AppResponseUtil.getSuccessResponse(rlt);
		}else{
			return AppResponseUtil.getErrorBusi("10086", "活动不存在或已过期");
		}
    }
	
}
