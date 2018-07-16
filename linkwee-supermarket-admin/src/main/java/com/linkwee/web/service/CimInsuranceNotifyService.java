package com.linkwee.web.service;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.CimInsuranceNotify;
import com.linkwee.web.request.insurance.qixin.InsuranceAuditRequest;
import com.linkwee.web.request.insurance.qixin.InsuranceNotifyAuditRequest;
import com.linkwee.web.service.CimInsuranceNotifyService;
 /**
 * 
 * @描述： CimInsuranceNotifyService服务接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2017年11月30日 11:21:50
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CimInsuranceNotifyService extends GenericService<CimInsuranceNotify,Long>{

	/**
	 * 查询CimInsuranceNotify列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);

	/**
	 * 查询待审核的保险订单列表
	 * @param insuranceNotifyAuditRequest
	 * @return
	 */
	DataTableReturn getInsuranceNotify(InsuranceNotifyAuditRequest insuranceNotifyAuditRequest);

	/**
	 * 保险订单审核
	 * @param insuranceAuditRequest
	 * @return
	 */
	String auditInsuranceNotify(InsuranceAuditRequest insuranceAuditRequest) throws Exception;
	
	/**
	 * 根据机构代码和投保单号查询相关的保单
	 * @param orgNumber
	 * @param insureNum
	 * @return
	 */
	CimInsuranceNotify selectByOrgNumberInsureNum(String orgNumber, String insureNum);

	/**
	 * 根据保险订单通知更新保险审核状态
	 * @param cimInsuranceNotify
	 */
	void updateStatus(CimInsuranceNotify cimInsuranceNotify);
}
