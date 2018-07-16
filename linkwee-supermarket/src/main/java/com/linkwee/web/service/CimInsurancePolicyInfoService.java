package com.linkwee.web.service;

import java.util.List;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.CimInsuranceNotify;
import com.linkwee.web.model.CimInsurancePolicyInfo;
 /**
 * 
 * @描述： CimInsurancePolicyInfoService服务接口
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2017年09月14日 16:54:47
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CimInsurancePolicyInfoService extends GenericService<CimInsurancePolicyInfo,Long>{

	/**
	 * 查询CimInsurancePolicyInfo列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);

	/**
	 * 批量插入保单信息
	 * @param cimInsurancePolicyInfoList
	 */
	void insertList(List<CimInsurancePolicyInfo> cimInsurancePolicyInfoList);

	/**
	 * 根据投保单号获取保单信息
	 * @param insureNum
	 * @return
	 */
	List<CimInsurancePolicyInfo> selectListByInsureNum(String insureNum);

	/**
	 * 根据投保单号删除对应的保单信息
	 * @param insureNum
	 */
	void deleteByInsureNum(String insureNum);
	
	/**
	 * 处理保险订单通知
	 * @param investRecordReq 投资记录信息
	 */
	void handleInsuranceNotifyProcess(CimInsuranceNotify  cimInsuranceNotify) throws Exception;
}
