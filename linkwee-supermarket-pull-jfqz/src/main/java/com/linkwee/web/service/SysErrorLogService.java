package com.linkwee.web.service;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.SysErrorLog;
 /**
 * 
 * @描述： SysErrorLogService服务接口
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2018年03月22日 19:16:19
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有	
 */
public interface SysErrorLogService extends GenericService<SysErrorLog,Long>{

	/**
	 * 查询SysErrorLog列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);
}
