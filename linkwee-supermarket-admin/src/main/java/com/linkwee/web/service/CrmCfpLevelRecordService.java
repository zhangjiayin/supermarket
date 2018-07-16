package com.linkwee.web.service;

import java.util.List;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.CrmCfpLevelRecord;
import com.linkwee.web.model.crm.BatchChangeGrade;
import com.linkwee.web.request.CfpLevelStatisticsRequest;
import com.linkwee.web.response.CfpLevelDataStatisticsListResp;
 /**
 * 
 * @描述： CrmCfpLevelRecordService服务接口
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2017年03月31日 10:34:09
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CrmCfpLevelRecordService extends GenericService<CrmCfpLevelRecord,Long>{

	/**
	 * 查询CrmCfpLevelRecord列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);
	
	/**
	 * 计算理财师年化业绩(一个月)
	 */
	void calculateYearpurAmount();
	
	/**
	 * 计算理财师职级 方法一
	 */
	void calculateCfpLevel();
	
	/**
	 * 更新理财师职级记录
	 * @param crmCfpLevelRecord  理财师信息
	 * @param level	    理财师职级
	 */
	void updateLevelRecord(CrmCfpLevelRecord crmCfpLevelRecord);
	
	/**
	 * 计算下级人数并写表
	 */
	void calculateCfpLevelTypeCount();

	/**
	 * 设置理财师的职级
	 * @param userId
	 * @param jobGrade
	 * @return
	 */
	int updateLevelRecord(String userId, String jobGrade,String currentUser);
	/**
	 * 理财师晋级发送消息
	 * @param crmCfpLevelRecord
	 */
	void sendCfpLevelMsg(final CrmCfpLevelRecord crmCfpLevelRecord);

	/**
	 * 数据统计-职级人数
	 * @param cfpLevelDataRequest
	 * @return
	 */
	List<CfpLevelDataStatisticsListResp> queryStatisticsList(CfpLevelStatisticsRequest cfpLevelStatisticsRequest);

	/**
	 * 批量调整职级
	 */
	String batchChangeGrade(List<BatchChangeGrade> gradeList);
}
