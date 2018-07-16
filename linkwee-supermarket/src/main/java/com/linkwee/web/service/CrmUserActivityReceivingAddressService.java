package com.linkwee.web.service;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.CrmUserActivityReceivingAddress;
import com.linkwee.web.service.CrmUserActivityReceivingAddressService;
 /**
 * 
 * @描述： CrmUserActivityReceivingAddressService服务接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2018年04月08日 10:20:14
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CrmUserActivityReceivingAddressService extends GenericService<CrmUserActivityReceivingAddress,Long>{

	/**
	 * 查询CrmUserActivityReceivingAddress列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);
}
