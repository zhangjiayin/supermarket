package com.linkwee.web.service;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.crm.CimLeaderTree;
 /**
 * 
 * @描述： CimLeaderTreeService服务接口
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2017年03月06日 16:38:51
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CimLeaderTreeService extends GenericService<CimLeaderTree,Long>{

	/**
	 * 查询CimLeaderTree列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);
}
