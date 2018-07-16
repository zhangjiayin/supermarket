package com.linkwee.web.service;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.CimOrgRechargeLimit;
 /**
 * 
 * @描述： CimOrgRechargeLimitService服务接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2017年07月27日 18:31:28
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CimOrgRechargeLimitService extends GenericService<CimOrgRechargeLimit,Long>{

	/**
	 * 查询CimOrgRechargeLimit列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);
}
