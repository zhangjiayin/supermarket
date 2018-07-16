package com.linkwee.web.service;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.CimProductUpdatePull;
 /**
 * 
 * @描述： CimProductUpdatePullService服务接口
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2017年02月09日 17:21:31
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CimProductUpdatePullService extends GenericService<CimProductUpdatePull,Long>{

	/**
	 * 查询CimProductUpdatePull列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);
}
