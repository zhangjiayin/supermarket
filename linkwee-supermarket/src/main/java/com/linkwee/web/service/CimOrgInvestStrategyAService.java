package com.linkwee.web.service;

import java.util.List;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.CimOrgInvestStrategyA;
import com.linkwee.web.model.CimOrgInvestStrategyAExtends;
import com.linkwee.web.service.CimOrgInvestStrategyAService;
 /**
 * 
 * @描述： CimOrgInvestStrategyAService服务接口
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2018年05月09日 16:41:24
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CimOrgInvestStrategyAService extends GenericService<CimOrgInvestStrategyA,Long>{

	/**
	 * 查询CimOrgInvestStrategyA列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);

	/**
	 * 根据机构编码查询机构理财收益率列表
	 * @param orgNumber
	 * @return
	 */
	List<CimOrgInvestStrategyAExtends> queryCimOrgInvestStrategyAExtendsList(String orgNumber);
}
