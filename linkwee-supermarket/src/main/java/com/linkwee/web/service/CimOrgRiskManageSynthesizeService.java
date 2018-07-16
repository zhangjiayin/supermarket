package com.linkwee.web.service;

import java.util.List;

import com.linkwee.api.request.cim.AllAssessRequest;
import com.linkwee.api.request.cim.OrgMoneyDataRequest;
import com.linkwee.api.response.cim.AllAssessResponse;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.CimOrgRiskManageSynthesize;
import com.linkwee.web.model.cim.OrgMoneyDataDetail;
 /**
 * 
 * @描述： CimOrgRiskManageSynthesizeService服务接口
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2018年06月07日 17:06:03
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CimOrgRiskManageSynthesizeService extends GenericService<CimOrgRiskManageSynthesize,Long>{

	/**
	 * 查询CimOrgRiskManageSynthesize列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);

	/**
	 * 机构编码
	 * @param allAssessRequest
	 * @return
	 */
	AllAssessResponse getAllAssess(AllAssessRequest allAssessRequest);

	/**
	 * 根据机构编码查询机构风控管理综合
	 * @param orgNumber
	 * @return
	 */
	CimOrgRiskManageSynthesize selectOneByOrgNumber(String orgNumber);

	/**
	 * 查询平台数据
	 * @param orgMoneyDataRequest
	 * @return
	 */
	List<OrgMoneyDataDetail> orgdata(OrgMoneyDataRequest orgMoneyDataRequest);
}
