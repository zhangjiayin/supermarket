package com.linkwee.web.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.api.activity.response.NewYearHelpStatusResponse;
import com.linkwee.api.activity.response.YearStaPersonAchiResponse;
import com.linkwee.api.activity.response.YearStaTeamAchiResponse;
import com.linkwee.api.response.activity.YearMaxProfitUserResponse;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.model.ActivityList;
import com.linkwee.web.model.acc.MonthProfixTotalListResp;
import com.linkwee.web.response.InvestStatisticsResponse;

/**
 * 
 * 描述：精彩活动
 * @author yalin
 * @date 2016年7月26日 下午5:26:32 
 * Copyright (c) 深圳市前海领会科技有限公司
 */
public interface ActivityListMapper extends GenericDao<ActivityList,Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<ActivityList> selectBySearchInfo(@Param("actitityName")String actitityName,RowBounds page);
	
	/**
	 * 查询精彩活动
	 * @param req
	 * @return
	 */
	public List<ActivityList> queryActivities(Page<ActivityList> page,Map<String,Object> conditions);

	/**
	 * 活动平台（有封面的平台）
	 * @param page
	 * @param conditions
	 * @return
	 */
	List<ActivityList> queryActivitiesByPlatform(Page<ActivityList> page,Map<String, Object> conditions);

	/**
	 * 某个平台的活动列表(分页)
	 * @param page
	 * @param conditions
	 * @return
	 */
	List<ActivityList> queryActivitiesListByPlatform(Page<ActivityList> page,Map<String, Object> conditions);

	/**
	 * 某个平台的活动列表(不分页)
	 * @param conditions
	 * @return
	 */
	List<ActivityList> queryPlatformActivities(Map<String, Object> conditions);

	/**
	 * 最新开始的平台活动
	 * @return
	 */
	ActivityList queryNewest(Map<String, Object> map);

	/**
	 * 根据活动名称（模糊检索）和活动平台查活动
	 * @param activityName
	 * @param activityPlatform
	 * @return
	 */
	List<ActivityList> queryActivity(@Param("activityName")String activityName,@Param("activityPlatform")String activityPlatform);

	/**
	 * 根据code查对象
	 * @param activityCode
	 * @return
	 */
	ActivityList queryByCode(String activityCode);

	/**
	 * 当前时间在活动期间的活动
	 * @param selectCondition
	 * @return
	 */
	ActivityList selectActiveOne(ActivityList selectCondition);

	/**
	 * 国庆活动--子活动列表
	 * @param conditions
	 * @return
	 */
	List<ActivityList> querySubActivitiesList(Map<String, Object> conditions);

	/**
	 * 2017年度账单个人成绩
	 * @param userId
	 * @return
	 */
	YearStaPersonAchiResponse yearStatiPersonAchievement(@Param("userId")String userId);

	/**
	 * 2017年度账单每月收入列表
	 * @param userId
	 * @return
	 */
	List<MonthProfixTotalListResp> yearStatiMonthProfit(@Param("userId")String userId);

	/**
	 * 团队理财师
	 * @param userId
	 * @return
	 */
	List<String> teamCfp(String userId);

	/**
	 * 2017年度账单团队成绩
	 * @param teamCfpList
	 * @return
	 */
	YearStaTeamAchiResponse yearStatiTeamAchievement(@Param("teamCfpList")List<String> teamCfpList);

	/**
	 * 2017年度账单投资机构分布
	 * @param teamCfpList
	 * @return
	 */
	List<InvestStatisticsResponse> yearPlatformInvestStatistics(@Param("teamCfpList")List<String> teamCfpList);

	/**
	 * 2017年度账单获取佣金最多的客户信息
	 * @param userId
	 * @return
	 */
	YearMaxProfitUserResponse yearMaxProfitUser(@Param("userId")String userId);

	/**
	 * 新年活动奖励保险状况
	 * @param userId
	 * @return
	 */
	int newYearInsureSum(@Param("userId")String userId,@Param("startTime")String startTime,@Param("endTime")String endTime);
	
	/**
	 * 新年活动奖励投资状况
	 * @param userId
	 * @return
	 */
	NewYearHelpStatusResponse newYearPlatformInvest(@Param("userId")String userId,@Param("startTime")String startTime,@Param("endTime")String endTime);

	/**
	 * 我的年化业绩（包括自己和直推理财师出单）
	 * @param userId
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	BigDecimal mySaleAmount(@Param("userId")String userId,@Param("startTime")String startTime,@Param("endTime")String endTime);

	/**
	 * 新年活动最大业绩（自己和直接推荐理财师出单）
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	BigDecimal newYearMaxSaleAmount(@Param("startTime")String startTime,@Param("endTime")String endTime);

	/**
	 * 根据A平台查询A平台相关的活动
	 * @param orgNumber
	 * @return
	 */
	List<ActivityList> queryOrginfoaActivityList(String orgNumber);
	
}
