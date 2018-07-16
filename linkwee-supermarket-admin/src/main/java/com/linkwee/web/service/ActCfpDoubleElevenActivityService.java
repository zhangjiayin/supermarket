package com.linkwee.web.service;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.ActCfpDoubleElevenActivity;
import com.linkwee.web.service.ActCfpDoubleElevenActivityService;
 /**
 * 
 * @描述： ActCfpDoubleElevenActivityService服务接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2017年10月23日 11:23:07
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface ActCfpDoubleElevenActivityService extends GenericService<ActCfpDoubleElevenActivity,Long>{

	/**
	 * 查询ActCfpDoubleElevenActivity列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);
}
