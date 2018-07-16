package com.linkwee.web.service;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.ActivityProfit;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年06月24日 14:18:12
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface ActivityProfitService extends GenericService<ActivityProfit,Long>{

	/**
	 * 查询ActivityProfit列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);
}
