package com.linkwee.web.service;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.ActAddFeeCoupon;
import com.linkwee.web.request.act.AddFeeCouponInfoRequest;
import com.linkwee.web.service.ActAddFeeCouponService;
 /**
 * 
 * @描述： ActAddFeeCouponService服务接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2017年10月20日 17:11:30
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface ActAddFeeCouponService extends GenericService<ActAddFeeCoupon,Long>{

	/**
	 * 查询ActAddFeeCoupon列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);

	/**
	 * 加拥券列表
	 * @param dataTable
	 * @return
	 */
	DataTableReturn getAddFeeCouponList(DataTable dataTable);

	/**
	 * 新增加拥券
	 * @param addFeeCouponInfo
	 */
	void insertAddFeeCoupon(AddFeeCouponInfoRequest addFeeCouponInfo);

	/**
	 * 更新加拥券
	 * @param addFeeCouponInfo
	 */
	void updateAddFeeCoupon(AddFeeCouponInfoRequest addFeeCouponInfo);
}
