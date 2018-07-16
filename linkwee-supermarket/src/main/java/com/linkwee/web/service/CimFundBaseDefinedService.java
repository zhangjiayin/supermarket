package com.linkwee.web.service;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.CimFundBaseDefined;
 /**
 * 
 * @描述： CimFundBaseDefinedService服务接口
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2017年08月16日 10:00:12
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CimFundBaseDefinedService extends GenericService<CimFundBaseDefined,Long>{

	/**
	 * 查询CimFundBaseDefined列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);
}
