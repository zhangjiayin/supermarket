package com.linkwee.web.service;


import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.linkwee.api.activity.response.NewYearHelpStatusResponse;
import com.linkwee.api.activity.response.YearStaPersonAchiResponse;
import com.linkwee.api.activity.response.YearStaTeamAchiResponse;
import com.linkwee.api.response.activity.YearMaxProfitUserResponse;
import com.linkwee.core.base.api.PaginatorResponse;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.model.ActivityList;
/**
 * 
 * 描述：精彩活动
 * @author yalin
 * @date 2016年7月26日 下午6:15:07 
 * Copyright (c) 深圳市前海领会科技有限公司
 */
public interface ActivityListService extends GenericService<ActivityList,Long>{

	
	
	/**
	 * 查询所有精彩活动
	 * @param req
	 * @return
	 */
	public PaginatorResponse<ActivityList> queryActivities(Page<ActivityList> page,Map<String,Object> conditions);
	
	/**
	 * 查询ActivityList列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable, String actitityName);

	/**
	 * 活动平台（有封面的平台）
	 * @param page
	 * @param conditions
	 * @return
	 */
	public PaginatorResponse<ActivityList> queryActivitiesByPlatform(Page<ActivityList> page, Map<String, Object> conditions);

	/**
	 * 某个平台的活动列表(分页)
	 * @param page
	 * @param conditions
	 * @return
	 */
	public PaginatorResponse<ActivityList> queryActivitiesListByPlatform(Page<ActivityList> page, Map<String, Object> conditions);

	/**
	 * 某个平台的活动列表(不分页)
	 * @param conditions
	 * @return
	 */
	public List<ActivityList> queryPlatformActivities(Map<String, Object> conditions);

	/**
	 * 最新开始的活动
	 * @return
	 */
	public ActivityList queryNewest(Integer appType);

	/**
	 * 根据活动名称（模糊检索）和活动平台查活动
	 * @param activityName
	 * @param activityPlatform
	 * @return
	 */
	public List<ActivityList> queryActivity(String activityName,String activityPlatform);


	/**
	 * 根据code查活动
	 * @param activityCode
	 * @return
	 */
	public ActivityList queryByCode(String activityCode);

	/**
	 * 当前时间在活动期间的活动
	 * @param selectCondition
	 * @return
	 */
	public ActivityList selectActiveOne(ActivityList selectCondition);

	/**
	 * 中秋节活动--子活动列表
	 * @param conditions
	 * @return
	 */
	public List<ActivityList> querySubActivitiesList(Map<String, Object> conditions);

	/**
	 * 2017年度账单个人成绩
	 * @param userId
	 * @return
	 */
	public YearStaPersonAchiResponse yearStatiPersonAchievement(String userId);

	/**
	 * 团队理财师
	 * @param userId
	 * @return
	 */
	public List<String> teamCfp(String userId);

	/**
	 * 2017年度账单团队成绩
	 * @param teamCfpList
	 * @return
	 */
	public YearStaTeamAchiResponse yearTeamAchievement(List<String> teamCfpList);

	/**
	 * 2017年度账单获取佣金最多的客户信息
	 * @param userId
	 * @return
	 */
	public YearMaxProfitUserResponse yearMaxProfitUser(String userId);

	/**
	 * 新年活动奖励状况
	 * @param userId
	 * @return
	 */
	public NewYearHelpStatusResponse newYearHelpStatus(String userId,String startTime,String endTime);

	/**
	 * 新年活动最大业绩（自己和直接推荐理财师出单）
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public BigDecimal newYearMaxSaleAmount(String startTime,String endTime);

	/**
	 * 根据A平台查询A平台相关的活动
	 * @param orgNumber
	 * @return
	 */
	public List<ActivityList> queryOrginfoaActivityList(String orgNumber);

}
