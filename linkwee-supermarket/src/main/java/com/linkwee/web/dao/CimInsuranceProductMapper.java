package com.linkwee.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.api.request.insurance.qixin.QixinInsuranceListRequest;
import com.linkwee.api.request.insurance.qixin.QixinInsuranceSiftRequest;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.model.CimInsuranceProduct;
import com.linkwee.web.model.CimInsuranceProductExtends;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2017年09月12日 14:37:38
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CimInsuranceProductMapper extends GenericDao<CimInsuranceProduct,Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<CimInsuranceProduct> selectBySearchInfo(@Param("dt")DataTable dt,RowBounds page);

	/**
	 * 查询保险列表
	 * @param qixinInsuranceListRequest
	 * @param page
	 * @return
	 */
	List<CimInsuranceProductExtends> insuranceList(QixinInsuranceListRequest qixinInsuranceListRequest,Page<CimInsuranceProductExtends> page);

	/**
	 * 查询精选保险
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
