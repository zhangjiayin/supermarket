package com.linkwee.web.service;

import java.util.Date;

import com.alibaba.fastjson.JSONObject;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.CimInsuranceInfo;
import com.linkwee.web.model.CimInsuranceNotify;
import com.linkwee.web.model.CimInsuranceProduct;
 /**
 * 
 * @描述： CimInsuranceInfoService服务接口
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2017年09月12日 14:37:38
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CimInsuranceInfoService extends GenericService<CimInsuranceInfo,Long>{

	/**
	 * 查询CimInsuranceInfo列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);

	/**
	 * 根据机构编码查询保险机构信息
	 * @param orgCode
	 * @return
	 */
	CimInsuranceInfo selectByOrgNumber(String orgCode);

	/**
	 * 处理齐欣通知消息
	 * @param jsonData
	 * @return
	 */
	Object notify(String jsonData);
	
	/**
	 * 初始化保险通知表
	 * @param cimInsuranceNotify
	 * @param orgNumber
	 * @param notifyType
	 * @param innerDataJSONObject
	 * @param insureNum
	 * @param creatTime
	 * @param updateTime
	 * @param cimInsuranceProduct
	 * @return
	 */
	CimInsuranceNotify initCimInsuranceNotify(CimInsuranceNotify cimInsuranceNotify,String orgNumber,Integer notifyType,JSONObject innerDataJSONObject,String insureNum,Date creatTime,Date updateTime,CimInsuranceProduct cimInsuranceProduct);
}
