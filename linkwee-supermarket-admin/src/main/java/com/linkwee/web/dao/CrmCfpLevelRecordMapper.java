package com.linkwee.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.web.model.CfpLevelNode;
import com.linkwee.web.model.CrmCfpLevelRecord;
import com.linkwee.web.request.CfpLevelStatisticsRequest;
import com.linkwee.web.response.CfpLevelDataStatisticsListResp;
import com.linkwee.web.response.CfpYearpurAmountResponse;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2017年03月31日 10:34:09
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CrmCfpLevelRecordMapper extends GenericDao<CrmCfpLevelRecord,Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<CrmCfpLevelRecord> selectBySearchInfo(@Param("dt")DataTable dt,RowBounds page);

	/**
	 * 批量插入理财师职级记录
	 * @param cfpWithoutChildrenAndParentList
	 */
	void batchInsert(@Param("cfpLevelNodeList") List<CfpLevelNode> cfpLevelNodeList);

	/**
	 * 查询已经记录的最后一个月
	 * @return
	 */
	boolean isExistMonth(@Param("month")int month);
	
	/**
	 * 更新理财师职级记录当前 有效-->无效
	 */
	void updateYearpurAmountNow();

	/**
	 * 查询一个月内理财师的年化业绩(年化业绩>0)
	 * @return
	 */
	List<CfpYearpurAmountResponse> querycfpYearpurAmount(@Param("startTime")String startTime,@Param("endTime")String endTime);
	
	/**
	 * 查询一个月内理财师的年化业绩(年化业绩=0)
	 * @return
	 */
	List<CfpYearpurAmountResponse> querycfpYearpurAmount0(@Param("startTime")String startTime,@Param("endTime")String endTime);

	/**
	 * 批量插入理财师定级记录
	 * @param crmCfpLevelRecordList
	 */
	void insertCfpLevelRecordList(List<CrmCfpLevelRecord> crmCfpLevelRecordList);

	/**
	 * 根据理财师userId更新职级记录
	 * @param userId
	 */
	void updateLevelRecordByUserId(CrmCfpLevelRecord crmCfpLevelRecord);

	/**
	 * 数据统计-职级人数
	 * @param cfpLevelDataRequest
	 * @param page
	 * @return
	 */
	List<CfpLevelDataStatisticsListResp> queryStatisticsList(CfpLevelStatisticsRequest cfpLevelStatisticsRequest);
}
