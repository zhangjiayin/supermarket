package com.linkwee.web.service;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.CrmCfpLevelRecordTemp;
 /**
 * 
 * @描述： CrmCfpLevelRecordTempService服务接口
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2017年04月10日 13:51:28
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CrmCfpLevelRecordTempService extends GenericService<CrmCfpLevelRecordTemp,Long>{

	/**
	 * 查询CrmCfpLevelRecordTemp列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);

	/**
	 * 计算理财师年化业绩
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
	void updateLevelRecord(CrmCfpLevelRecordTemp CrmCfpLevelRecordTemp);
	
	/**
	 * 计算下级人数并写表
	 */
	void calculateCfpLevelTempTypeCount();
}
