package com.linkwee.web.service;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.ActLaborDayChangeFaceRecord;
import com.linkwee.web.service.ActLaborDayChangeFaceRecordService;
 /**
 * 
 * @描述： ActLaborDayChangeFaceRecordService服务接口
 * 
 * @创建人： Hxb
 * 
 * @创建时间：2018年04月24日 16:51:03
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface ActLaborDayChangeFaceRecordService extends GenericService<ActLaborDayChangeFaceRecord,Long>{

	/**
	 * 查询ActLaborDayChangeFaceRecord列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);
}
