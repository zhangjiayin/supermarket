package com.linkwee.web.service;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.CimOrgInfoToOur;
import com.linkwee.web.service.CimOrgInfoToOurService;
 /**
 * 
 * @描述： CimOrgInfoToOurService服务接口
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2018年02月28日 14:21:13
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CimOrgInfoToOurService extends GenericService<CimOrgInfoToOur,Long>{

	/**
	 * 查询CimOrgInfoToOur列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);

	/**
	 * 根据机构编码查询 对接领会机构信息
	 * @param orgNumber
	 * @return
	 */
	CimOrgInfoToOur selectOneByOrgNumber(String orgNumber);
}
