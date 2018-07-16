package com.linkwee.web.service;

import java.util.List;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.SystemConfigNew;
import com.linkwee.web.service.SystemConfigNewService;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年06月03日 10:05:19
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface SystemConfigNewService extends GenericService<SystemConfigNew,Long>{

	/**
	 * 查询SystemConfigNew列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);
}
