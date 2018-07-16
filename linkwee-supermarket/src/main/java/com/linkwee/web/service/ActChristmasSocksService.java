package com.linkwee.web.service;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.ActChristmasSocks;
import com.linkwee.web.service.ActChristmasSocksService;
 /**
 * 
 * @描述： ActChristmasSocksService服务接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2017年11月20日 13:52:59
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface ActChristmasSocksService extends GenericService<ActChristmasSocks,Long>{

	/**
	 * 查询ActChristmasSocks列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);
}
