package com.linkwee.web.service;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.CimInsuranceRecommendType;
import com.linkwee.web.service.CimInsuranceRecommendTypeService;
 /**
 * 
 * @描述： CimInsuranceRecommendTypeService服务接口
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2018年01月07日 11:46:05
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CimInsuranceRecommendTypeService extends GenericService<CimInsuranceRecommendType,Long>{

	/**
	 * 查询CimInsuranceRecommendType列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);
}
