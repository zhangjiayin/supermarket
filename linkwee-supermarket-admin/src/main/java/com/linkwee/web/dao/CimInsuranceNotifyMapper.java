package com.linkwee.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.model.CimInsuranceNotify;
import com.linkwee.web.request.insurance.qixin.InsuranceNotifyAuditRequest;
import com.linkwee.web.response.insurance.qixin.InsuranceNotifyAuditResponse;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2017年11月30日 11:21:50
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CimInsuranceNotifyMapper extends GenericDao<CimInsuranceNotify,Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<CimInsuranceNotify> selectBySearchInfo(@Param("dt")DataTable dt,RowBounds page);

	/**
	 * 查询待审核的保险订单列表
	 * @param insuranceNotifyAuditRequest
	 * @param page
	 * @return
	 */
	List<InsuranceNotifyAuditResponse> getInsuranceNotify(InsuranceNotifyAuditRequest insuranceNotifyAuditRequest,Page<InsuranceNotifyAuditResponse> page);
}
