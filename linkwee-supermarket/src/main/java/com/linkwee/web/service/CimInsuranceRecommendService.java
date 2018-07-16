package com.linkwee.web.service;

import java.util.List;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.CimInsuranceProductExtends;
import com.linkwee.web.model.CimInsuranceRecommend;
import com.linkwee.web.service.CimInsuranceRecommendService;
 /**
 * 
 * @描述： CimInsuranceRecommendService服务接口
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2018年01月07日 11:46:05
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CimInsuranceRecommendService extends GenericService<CimInsuranceRecommend,Long>{

	/**
	 * 查询CimInsuranceRecommend列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);

	/**
	 * 根据保险种类和查询类型查询保险产品
	 * @param category
	 * @param queryType
	 * @return
	 */
	List<CimInsuranceProductExtends> getInsuranceProductByType(String category,String queryType);
}
