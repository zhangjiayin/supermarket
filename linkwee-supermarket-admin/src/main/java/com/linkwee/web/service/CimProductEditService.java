package com.linkwee.web.service;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.CimProductEdit;
import com.linkwee.web.request.CimProductEditDataTableRequest;
 /**
 * 
 * @描述： CimProductEditService服务接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年11月28日 13:47:00
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CimProductEditService extends GenericService<CimProductEdit,Long>{

	/**
	 * 查询CimProductEdit列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);

	/**
	 * 根据条件查询CimProductEdit列表,
	 * @param cimProductEdit
	 * @param dataTable
	 * @return
	 */
	DataTableReturn findProductEditList(CimProductEditDataTableRequest cimProductEditDataTableRequest);
}
