package com.linkwee.web.service;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.CimOrginfo;
import com.linkwee.web.service.CimOrginfoService;
 /**
 * 
 * @描述： CimOrginfoService服务接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年07月20日 14:45:19
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CimOrginfoService extends GenericService<CimOrginfo,Long>{

	/**
	 * 查询CimOrginfo列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);
}
