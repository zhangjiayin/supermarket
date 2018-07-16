package com.linkwee.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.api.response.act.InvestRankingListResponse;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.model.ActMidautumnTask;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2017年09月20日 10:12:11
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface ActMidautumnTaskMapper extends GenericDao<ActMidautumnTask,Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<ActMidautumnTask> selectBySearchInfo(@Param("dt")DataTable dt,RowBounds page);

	/**
	 * 月饼节活动--排行榜--榜单
	 * @param startDate
	 * @param endDate
	 * @param platformList
	 * @param page
	 * @return
	 */
	List<InvestRankingListResponse> investRankingList(@Param("startDate")String startDate, @Param("endDate")String endDate, @Param("platformList")List<String> platformList,RowBounds page);

	/**
	 * 是否完成出单任务
	 * @param userId
	 * @param startDate
	 * @param endDate
	 * @param platformList
	 * @return
	 */
	boolean hasFinishInvestStatus(@Param("userId")String userId,@Param("startDate")String startDate, @Param("endDate")String endDate, @Param("platformList")List<String> platformList);
}
