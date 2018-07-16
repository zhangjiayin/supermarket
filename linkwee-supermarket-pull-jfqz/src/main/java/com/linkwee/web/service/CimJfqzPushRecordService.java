package com.linkwee.web.service;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.CimJfqzPushRecord;
 /**
 * 
 * @描述： CimJfqzPushRecordService服务接口
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2018年03月21日 18:23:57
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CimJfqzPushRecordService extends GenericService<CimJfqzPushRecord,Long>{

	/**
	 * 查询CimJfqzPushRecord列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);
}
