package com.linkwee.api.controller.activity;

import java.math.BigDecimal;
import java.text.DecimalFormat;
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

import com.linkwee.api.activity.BaseLottery;
import com.linkwee.api.activity.response.BigWheelDrawResponse;
import com.linkwee.api.activity.utils.ValentineWheelDrawUtil;
import com.linkwee.api.response.act.InvestRankingListResponse;
import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.core.base.api.PaginatorRequest;
import com.linkwee.core.base.api.PaginatorResponse;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.core.util.DateUtils;
import com.linkwee.core.util.StringUtils;
import com.linkwee.web.model.ActWheelWinningRecord;
import com.linkwee.web.model.ActivityList;
import com.linkwee.web.model.CrmCfplanner;
import com.linkwee.web.model.CrmUserInfo;
import com.linkwee.web.service.ActWheelWinningRecordService;
import com.linkwee.web.service.ActivityListService;
import com.linkwee.web.service.CrmCfplannerService;
import com.linkwee.web.service.CrmUserInfoService;
import com.linkwee.xoss.api.AppRequestHead;
import com.linkwee.xoss.helper.JsonWebTokenHepler;
import com.linkwee.xoss.util.AppResponseUtil;
import com.linkwee.xoss.util.AppUtils;
import com.linkwee.xoss.util.RequestLogging;

@Controller
@RequestMapping("/api/activity/valentine/wheel")
@RequestLogging("转盘抽奖")
public class ValentineWheelController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ValentineWheelController.class);
	
	@Resource
    private CrmUserInfoService crmUserInfoService;
	
	@Resource
	private ActWheelWinningRecordService actWheelWinningRecordService;
	
	@Resource
	private ActivityListService activityListService;
	
	@Resource
	private CrmCfplannerService crmCfplannerService;
		
	/**
	 * 抽奖次数 -- 周年庆
	 * @param head
	 * @param result
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/prize/times")
    @ResponseBody
	@RequestLogging("转盘抽奖--抽奖次数--周年庆")
    public BaseResponse prizeTimes(AppRequestHead head,BindingResult result){
		
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());		
		if(StringUtils.isBlank(userId) || userId.equals("undefined")){
			return AppResponseUtil.getErrorToken();
		}
		
		CrmCfplanner cfplanner = crmCfplannerService.queryCfplannerByUserId(userId);
		if(cfplanner == null){
			return AppResponseUtil.getErrorBusi("10087", "活动对象为理财师");
		}
		
		ActivityList selectCondition = new ActivityList();
		selectCondition.setActivityCode("valentine_wheel");
		ActivityList activity = activityListService.selectActiveOne(selectCondition);
		
		if(activity != null){
			Integer hasDrawedTimes = actWheelWinningRecordService.queryHasDrawTimes(userId,activity.getId().toString());
			String totalMoney = actWheelWinningRecordService.queryInvestedMoneyExceptSomePlatform(userId,DateUtils.format(activity.getStartDate(), DateUtils.FORMAT_LONG),DateUtils.format(activity.getEndDate(), DateUtils.FORMAT_LONG),null);
			Integer totalTimes = (int) Math.floor(Double.valueOf(totalMoney)/1000) + 1;
			Integer leftTimes = totalTimes - hasDrawedTimes;
			if(leftTimes < 0){
				LOGGER.info("已抽取次数大于能抽取的总次数");
				leftTimes = 0;
			}
			Map<String,Object> rlt = new HashMap<String,Object>();
			rlt.put("leftTimes", leftTimes);
			rlt.put("totalTimes", totalTimes);
			BigDecimal b1 = new BigDecimal(totalMoney);      
			DecimalFormat myformat = new DecimalFormat("0.00");
			totalMoney = myformat.format(b1);
			rlt.put("totalMoney", totalMoney);
			return AppResponseUtil.getSuccessResponse(rlt);
		}else{
			return AppResponseUtil.getErrorBusi("10086", "活动不存在或已过期");
		}
						
    }
	
	/**
	 * 抽一次 -- 周年庆
	 * @param head
	 * @param result
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/prize/one")
    @ResponseBody
	@RequestLogging("转盘抽奖--抽一次--周年庆")
    public BaseResponse prizeOne(AppRequestHead head,BindingResult result){
		
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());		
		if(StringUtils.isBlank(userId) || userId.equals("undefined")){
			return AppResponseUtil.getErrorToken();
		}
		
		CrmCfplanner cfplanner = crmCfplannerService.queryCfplannerByUserId(userId);
		if(cfplanner == null){
			return AppResponseUtil.getErrorBusi("10087", "活动对象为理财师");
		}
		
		Integer userType = AppUtils.isChannelApp(head.getOrgNumber()) ? 1 : 2;
		
		ActivityList selectCondition = new ActivityList();
		selectCondition.setActivityCode("valentine_wheel");
		ActivityList activity = activityListService.selectActiveOne(selectCondition);
		
		if(activity != null){
			Integer hasDrawedTimes = actWheelWinningRecordService.queryHasDrawTimes(userId,activity.getId().toString());
			String totalMoney = actWheelWinningRecordService.queryInvestedMoneyExceptSomePlatform(userId,DateUtils.format(activity.getStartDate(), DateUtils.FORMAT_LONG),DateUtils.format(activity.getEndDate(), DateUtils.FORMAT_LONG),null);
			Integer totalTimes = (int) Math.floor(Double.valueOf(totalMoney)/1000) + 1;
			Integer leftTimes = totalTimes - hasDrawedTimes;
			if(leftTimes <= 0){
				LOGGER.info("剩余次数为0,不能继续抽奖");
				return AppResponseUtil.getErrorBusi("10088", "剩余次数为0,不能继续抽奖");
			}else{
				BaseLottery baseLottery;
				if(hasDrawedTimes == 0){
					baseLottery = ValentineWheelDrawUtil.generateFirstAward();
				}else{
					baseLottery = ValentineWheelDrawUtil.generateAward();
				}
				BigWheelDrawResponse bigWheelDrawResponse = new BigWheelDrawResponse(); 
				bigWheelDrawResponse.setPrizeId(baseLottery.getId());
				bigWheelDrawResponse.setLeftTimes(leftTimes-1);
				CrmUserInfo crmUserInfo = crmUserInfoService.queryUserInfoByUserId(userId);
				try {
					actWheelWinningRecordService.insertDrawRecordWithRemark(baseLottery,1,userId,crmUserInfo.getMobile(),userType,activity.getId().toString(),"周年庆活动");
				} catch (Exception e) {
					LOGGER.warn("记录转盘抽奖记录失败");
					return AppResponseUtil.getErrorBusi("10099", "记录转盘抽奖记录失败");
				}
				return AppResponseUtil.getSuccessResponse(bigWheelDrawResponse);
			}	
		}else{
			return AppResponseUtil.getErrorBusi("10086", "活动不存在或已过期");
		}
						
    }
	
	/**
	 * 所有抽奖记录 -- 周年庆
	 * @param head
	 * @param result
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/prize/record/all")
    @ResponseBody
	@RequestLogging("转盘抽奖--所有抽奖记录--周年庆")
    public BaseResponse prizeRecordAll(AppRequestHead head,BindingResult result){
		ActivityList selectCondition = new ActivityList();
		selectCondition.setActivityCode("valentine_wheel");
		ActivityList activity = activityListService.selectOne(selectCondition);
		ActWheelWinningRecord actWheelWinningRecord = new ActWheelWinningRecord();

		if(activity != null){	
			actWheelWinningRecord.setExtends1(activity.getId().toString());
			List<ActWheelWinningRecord> actWheelWinningRecordList = actWheelWinningRecordService.selectListByCondition(actWheelWinningRecord);
			for(ActWheelWinningRecord actWheelWinningRecordTemp : actWheelWinningRecordList){
				String mobile = actWheelWinningRecordTemp.getMobile().substring(0, 3)+"****"+actWheelWinningRecordTemp.getMobile().substring(7);
				actWheelWinningRecordTemp.setMobile(mobile);
			}
			return AppResponseUtil.getSuccessResponse(actWheelWinningRecordList);	
		}else{
			return AppResponseUtil.getErrorBusi("10086", "活动不存在或已过期");
		}
					
    }
	
	/**
	 * 用户抽奖记录(分页) -- 周年庆
	 * @param head
	 * @param result
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/prize/record/user/pageList")
    @ResponseBody
	@RequestLogging("转盘抽奖--用户抽奖记录--周年庆")
    public BaseResponse prizeRecordUserPageList(AppRequestHead head,PaginatorRequest paginatorRequest){		
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());		
		if(StringUtils.isBlank(userId) || userId.equals("undefined")){
			return AppResponseUtil.getErrorToken();
		}
		CrmCfplanner cfplanner = crmCfplannerService.queryCfplannerByUserId(userId);
		if(cfplanner == null){
			return AppResponseUtil.getErrorBusi("10087", "活动对象为理财师");
		}
		ActivityList selectCondition = new ActivityList();
		selectCondition.setActivityCode("valentine_wheel");
		ActivityList activity = activityListService.selectOne(selectCondition);
		if(activity != null){	
			Page<ActWheelWinningRecord> page = new Page<ActWheelWinningRecord>(paginatorRequest.getPageIndex(), paginatorRequest.getPageSize());		
			ActWheelWinningRecord actWheelWinningRecordTemp = new ActWheelWinningRecord();
			actWheelWinningRecordTemp.setUserId(userId);
			actWheelWinningRecordTemp.setExtends1(activity.getId().toString());
			PaginatorResponse<ActWheelWinningRecord> actWheelWinningRecordList = actWheelWinningRecordService.queryUserPrizeRecord(actWheelWinningRecordTemp,page);			
			return AppResponseUtil.getSuccessResponse(actWheelWinningRecordList);
		}else{
			return AppResponseUtil.getErrorBusi("10086", "活动不存在或已过期");
		}
    }
	
	/**
	 * 用户抽奖记录(不分页) -- 周年庆
	 * @param head
	 * @param result
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/prize/record/user")
    @ResponseBody
	@RequestLogging("转盘抽奖--用户抽奖记录--周年庆")
    public BaseResponse newYearPrizeRecordUser(AppRequestHead head,BindingResult result){				
		ActWheelWinningRecord actWheelWinningRecordTemp = new ActWheelWinningRecord();
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());		
		if(StringUtils.isBlank(userId) || userId.equals("undefined")){
			return AppResponseUtil.getErrorToken();
		}else {
			actWheelWinningRecordTemp.setUserId(userId);
		}
		CrmCfplanner cfplanner = crmCfplannerService.queryCfplannerByUserId(userId);
		if(cfplanner == null){
			return AppResponseUtil.getErrorBusi("10087", "活动对象为理财师");
		}
		ActivityList selectCondition = new ActivityList();
		selectCondition.setActivityCode("valentine_wheel");
		ActivityList activity = activityListService.selectOne(selectCondition);
		if(activity != null ){
			actWheelWinningRecordTemp.setExtends1(activity.getId().toString());
			List<ActWheelWinningRecord> actWheelWinningRecordList = actWheelWinningRecordService.selectListByCondition(actWheelWinningRecordTemp);	
			return AppResponseUtil.getSuccessResponse(actWheelWinningRecordList);	
		}else{
			return AppResponseUtil.getErrorBusi("10086", "活动不存在或已过期");
		}
    }
	
	@RequestMapping("/rankingList/pageList")
    @ResponseBody
	@RequestLogging("排行榜--周年庆--榜单")
    public BaseResponse rankingListPageList(AppRequestHead head,PaginatorRequest paginatorRequest){				
		ActivityList selectCondition = new ActivityList();
		selectCondition.setActivityCode("valentine_wheel");
		ActivityList activity = activityListService.selectOne(selectCondition);
		if(activity != null){	
			Page<InvestRankingListResponse> page = new Page<InvestRankingListResponse>(paginatorRequest.getPageIndex(), paginatorRequest.getPageSize());	
			PaginatorResponse<InvestRankingListResponse> rankingList = actWheelWinningRecordService.investRankingList(DateUtils.format(activity.getStartDate(), DateUtils.FORMAT_LONG),DateUtils.format(activity.getEndDate(), DateUtils.FORMAT_LONG),null,page);
			return AppResponseUtil.getSuccessResponse(rankingList);
		}else{
			return AppResponseUtil.getErrorBusi("10086", "活动不存在或已过期");
		}
    }
	
	@RequestMapping("/rankingList/myRank")
    @ResponseBody
	@RequestLogging("排行榜--周年庆--我的排行")
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
		selectCondition.setActivityCode("valentine_wheel");
		ActivityList activity = activityListService.selectOne(selectCondition);
		if(activity != null ){
			InvestRankingListResponse myRank = actWheelWinningRecordService.rankingListmyRank(userId,DateUtils.format(activity.getStartDate(), DateUtils.FORMAT_LONG),DateUtils.format(activity.getEndDate(), DateUtils.FORMAT_LONG),null);
			if(myRank == null){
				myRank = new InvestRankingListResponse();
				String totalMoney = actWheelWinningRecordService.queryInvestedMoneyExceptSomePlatform(userId,DateUtils.format(activity.getStartDate(), DateUtils.FORMAT_LONG),DateUtils.format(activity.getEndDate(), DateUtils.FORMAT_LONG),null);
				BigDecimal b1 = new BigDecimal(totalMoney);   
				BigDecimal b2 = new BigDecimal("10000");     
				DecimalFormat myformat = new DecimalFormat("0.00");
				String investAmt = myformat.format(b1.divide(b2,2,BigDecimal.ROUND_DOWN))+"万";
				myRank.setInvestAmt(investAmt);
				myRank.setMobile(cfplanner.getMobile());
				myRank.setRownum(0);
				myRank.setHeadImage(cfplanner.getHeadImage());
			}else{
				BigDecimal b1 = new BigDecimal(myRank.getInvestAmt());   
				BigDecimal b2 = new BigDecimal("10000");     
				DecimalFormat myformat = new DecimalFormat("0.00");
				String investAmt = myformat.format(b1.divide(b2,2,BigDecimal.ROUND_DOWN))+"万";
				myRank.setInvestAmt(investAmt);
			}				  
			return AppResponseUtil.getSuccessResponse(myRank);	
		}else{
			return AppResponseUtil.getErrorBusi("10086", "活动不存在或已过期");
		}
    }
	
}
