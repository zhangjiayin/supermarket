package com.linkwee.web.service;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.ActFinancialCalculationInfo;
import com.linkwee.web.service.ActFinancialCalculationInfoService;
 /**
 * 
 * @描述： ActFinancialCalculationInfoService服务接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2017年12月25日 10:17:03
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface ActFinancialCalculationInfoService extends GenericService<ActFinancialCalculationInfo,Long>{

	/**
	 * 查询ActFinancialCalculationInfo列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);

}
