package com.linkwee.web.service;

import java.util.List;

import com.linkwee.api.request.insurance.qixin.QixinInsuranceListRequest;
import com.linkwee.api.request.insurance.qixin.QixinInsuranceSiftRequest;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.model.CimInsuranceProduct;
import com.linkwee.web.model.CimInsuranceProductExtends;
 /**
 * 
 * @描述： CimInsuranceProductService服务接口
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2017年09月12日 14:37:38
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

	/**
	 * 保险列表
	 * @param qixinInsuranceSiftRequest
	 * @param page
	 * @return
	 */
	List<CimInsuranceProductExtends> insuranceList(QixinInsuranceListRequest qixinInsuranceListRequest,Page<CimInsuranceProductExtends> page);

	/**
	 * 精选保险
	 * @param qixinInsuranceSiftRequest
	 * @return
	 */
	CimInsuranceProductExtends selectinsuranceSift(QixinInsuranceSiftRequest qixinInsuranceSiftRequest);

	/**
	 * 甄选保险
	 * @return
	 */
	List<CimInsuranceProductExtends> insuranceSelect();
}
