package com.linkwee.web.service;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.CimOrgRiskManageSynthesize;
import com.linkwee.web.model.cim.OrgRiskManage;
 /**
 * 
 * @描述： CimOrgRiskManageSynthesizeService服务接口
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2018年06月07日 14:14:21
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
	 * 根据机构编码查询机构风险信息
	 * @param orgNumber
	 * @return
	 */
	OrgRiskManage queryRiskManageEdit(String orgNumber);

	/**
	 * 根据机构编码更新风控信息
	 * @param cimOrgRiskManageSynthesize
	 */
	void updateByOrgNumber(CimOrgRiskManageSynthesize cimOrgRiskManageSynthesize);

	/**
	 * 根据机构编码查询机构风控管理综合
	 * @param orgNumber
	 * @return
	 */
	CimOrgRiskManageSynthesize selectOneByOrgNumber(String orgNumber);
}
