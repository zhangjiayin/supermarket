package com.linkwee.web.service;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.CrmCfpLevelRecord;
import com.linkwee.web.model.CrmCfpLevelRecordTemp;
import com.linkwee.web.model.crm.CrmCfpLevelMonth;
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
	 * 初始化理财师的月职级
	 * @param cfplannerId
	 */
	CrmCfpLevelRecord initCfpLevel(String cfplannerId,Integer month);
	
	/**
	 * 初始化理财师的天职级
	 * @param cfplannerId
	 */
	CrmCfpLevelRecordTemp initCfpLevelTemp(String cfplannerId,Integer month);

	/**
	 * 根据时间查询理财师对应的职级
	 * @param date
	 * @return
	 */
	CrmCfpLevelRecord selectCrmCfpLevelRecordByTime(String date);

	/**
	 * 查询理财师每月定级
	 * @param crmCfpLevelMonth
	 * @return
	 */
	CrmCfpLevelRecord selectMonthCfpLevel(CrmCfpLevelMonth crmCfpLevelMonth);
}
