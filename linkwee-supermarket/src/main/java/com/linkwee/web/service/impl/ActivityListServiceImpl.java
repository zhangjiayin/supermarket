package com.linkwee.web.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.linkwee.api.activity.response.NewYearHelpStatusResponse;
import com.linkwee.api.activity.response.YearStaPersonAchiResponse;
import com.linkwee.api.activity.response.YearStaTeamAchiResponse;
import com.linkwee.api.response.activity.YearMaxProfitUserResponse;
import com.linkwee.core.base.api.PaginatorResponse;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.core.util.NumberUtils;
import com.linkwee.web.dao.ActivityListMapper;
import com.linkwee.web.model.ActivityList;
import com.linkwee.web.model.acc.MonthProfixTotalListResp;
import com.linkwee.web.response.InvestStatisticsResponse;
import com.linkwee.web.service.ActivityListService;


/**
 * 
 * 描述：精彩活动
 * @author yalin
 * @date 2016年7月26日 下午6:18:22 
 * Copyright (c) 深圳市前海领会科技有限公司
 */
@Service("activityListService")
public class ActivityListServiceImpl extends GenericServiceImpl<ActivityList, Long> implements ActivityListService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ActivityListServiceImpl.class);
	
	@Resource
	private ActivityListMapper activityListMapper;
	
	@Override
    public GenericDao<ActivityList, Long> getDao() {
        return activityListMapper;
    }
	
	 @Override
		public DataTableReturn selectByDatatables(DataTable dt, String actitityName) {
			DataTableReturn tableReturn = new DataTableReturn();
			tableReturn.setDraw(dt.getDraw()+1);
			LOGGER.debug(" -- ActivityList -- 排序和模糊查询 ");
			Page<ActivityList> page = new Page<ActivityList>(dt.getStart()/dt.getLength()+1,dt.getLength());
			List<ActivityList> list = this.activityListMapper.selectBySearchInfo(actitityName,page);
			tableReturn.setData(list);
			tableReturn.setRecordsFiltered(page.getTotalCount());
			tableReturn.setRecordsTotal(page.getTotalCount());
			return tableReturn;
		}

	
	@Override
	public PaginatorResponse<ActivityList> queryActivities(Page<ActivityList> page,Map<String,Object> conditions) {
		PaginatorResponse<ActivityList> activityListResponse = new PaginatorResponse<ActivityList>();
		List<ActivityList> activityList = activityListMapper.queryActivities(page,conditions);
		activityListResponse.setDatas(activityList);
		activityListResponse.setValuesByPage(page);
		return activityListResponse;
	}

	@Override
	public PaginatorResponse<ActivityList> queryActivitiesByPlatform(
			Page<ActivityList> page, Map<String, Object> conditions) {
		PaginatorResponse<ActivityList> activityListResponse = new PaginatorResponse<ActivityList>();
		List<ActivityList> activityList = activityListMapper.queryActivitiesByPlatform(page,conditions);
		activityListResponse.setDatas(activityList);
		activityListResponse.setValuesByPage(page);
		return activityListResponse;
	}

	@Override
	public PaginatorResponse<ActivityList> queryActivitiesListByPlatform(
			Page<ActivityList> page, Map<String, Object> conditions) {
		PaginatorResponse<ActivityList> activityListResponse = new PaginatorResponse<ActivityList>();
		List<ActivityList> activityList = activityListMapper.queryActivitiesListByPlatform(page,conditions);
		activityListResponse.setDatas(activityList);
		activityListResponse.setValuesByPage(page);
		return activityListResponse;
	}

	@Override
	public List<ActivityList> queryPlatformActivities(
			Map<String, Object> conditions) {
		List<ActivityList> activityList = activityListMapper.queryPlatformActivities(conditions);
		return activityList;
	}

	@Override
	public ActivityList queryNewest(Integer appType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("appType", appType);
		return activityListMapper.queryNewest(map);
	}

	@Override
	public List<ActivityList> queryActivity(String activityName,String activityPlatform) {
		return activityListMapper.queryActivity(activityName,activityPlatform);
	}

	@Override
	public ActivityList queryByCode(String activityCode) {
		return activityListMapper.queryByCode(activityCode);
	}

	@Override
	public ActivityList selectActiveOne(ActivityList selectCondition) {
		return activityListMapper.selectActiveOne(selectCondition);
	}

	@Override
	public List<ActivityList> querySubActivitiesList(Map<String, Object> conditions) {
		List<ActivityList> activityList = activityListMapper.querySubActivitiesList(conditions);
		return activityList;
	}

	@Override
	public YearStaPersonAchiResponse yearStatiPersonAchievement(String userId) {
		YearStaPersonAchiResponse response = activityListMapper.yearStatiPersonAchievement(userId);
		List<MonthProfixTotalListResp> monthProfitList = activityListMapper.yearStatiMonthProfit(userId);
		BigDecimal totalProfit = new BigDecimal("0.00");
		if(monthProfitList != null && monthProfitList.size() > 0){
			MonthProfixTotalListResp maxProfitMonthResp = monthProfitList.get(0);
			for(MonthProfixTotalListResp monthResponse : monthProfitList){
				if(monthResponse.getTotalAmount() > maxProfitMonthResp.getTotalAmount()){
					maxProfitMonthResp = monthResponse;
				}
				totalProfit = totalProfit.add(new BigDecimal(monthResponse.getTotalAmount()));
			}
			response.setMaxFeeMonth(maxProfitMonthResp.getMonth());
			response.setMaxFeeMonthAmount(new BigDecimal(maxProfitMonthResp.getTotalAmount().toString()));
		}	
		response.setMonthProfitList(monthProfitList);
		response.setTotalProfit(totalProfit);
		return response;
	}

	@Override
	public List<String> teamCfp(String userId) {
		return activityListMapper.teamCfp(userId);
	}

	@Override
	public YearStaTeamAchiResponse yearTeamAchievement(List<String> teamCfpList) {
		YearStaTeamAchiResponse response = activityListMapper.yearStatiTeamAchievement(teamCfpList);
		BigDecimal investingAmt = response.getInvestAmount();
		List<InvestStatisticsResponse> platformInvestStatistics = activityListMapper.yearPlatformInvestStatistics(teamCfpList);    	
    	List<InvestStatisticsResponse> investingStatisticList = new ArrayList<InvestStatisticsResponse>();
    	
    	for(InvestStatisticsResponse temp : platformInvestStatistics){
    		if(new BigDecimal(temp.getInvestAmt()).compareTo(new BigDecimal(0)) != 0){
    			investingStatisticList.add(temp);  			
    		}
    	}
    	BigDecimal leftPercent = new BigDecimal(1);
    	
    	for(int i = 0 ; i < investingStatisticList.size(); i++){ 		
    		InvestStatisticsResponse temp = investingStatisticList.get(i);
    		if(i == investingStatisticList.size()-1){
    			temp.setTotalPercent(NumberUtils.getFormat(leftPercent.multiply(new BigDecimal(100)),"0.00"));
    			break;
    		}
    		BigDecimal rate = new BigDecimal(temp.getInvestAmt()).divide(investingAmt, 4, RoundingMode.HALF_UP);
    		String totalPercent = NumberUtils.getFormat(rate.multiply(new BigDecimal(100)),"0.00");
    		temp.setTotalPercent(totalPercent);
    		leftPercent = leftPercent.subtract(rate); 
    	}
    	response.setInvestingStatisticList(investingStatisticList);
		return response;
	}

	@Override
	public YearMaxProfitUserResponse yearMaxProfitUser(String userId) {
		return activityListMapper.yearMaxProfitUser(userId);
	}

	@Override
	public NewYearHelpStatusResponse newYearHelpStatus(String userId,String startTime,String endTime) {
		NewYearHelpStatusResponse response = activityListMapper.newYearPlatformInvest(userId,startTime,endTime);
		int insureSum = activityListMapper.newYearInsureSum(userId,startTime,endTime);
		BigDecimal mySaleAmount = activityListMapper.mySaleAmount(userId,startTime,endTime);
		response.setInsureSum(insureSum);
		response.setMySaleAmount(mySaleAmount);
		return response;
	}

	@Override
	public BigDecimal newYearMaxSaleAmount(String startTime, String endTime) {
		return activityListMapper.newYearMaxSaleAmount(startTime,endTime);
	}

	@Override
	public List<ActivityList> queryOrginfoaActivityList(String orgNumber) {
		// TODO Auto-generated method stub
		return activityListMapper.queryOrginfoaActivityList(orgNumber);
	}
}
