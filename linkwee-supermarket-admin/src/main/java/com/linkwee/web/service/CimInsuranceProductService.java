package com.linkwee.web.service;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.CimInsuranceProduct;
import com.linkwee.web.service.CimInsuranceProductService;
 /**
 * 
 * @描述： CimInsuranceProductService服务接口
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2017年09月21日 13:51:34
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CimInsuranceProductService extends GenericService<CimInsuranceProduct,Long>{

	/**
	 * 查询CimInsuranceProduct列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);
}
