package com.linkwee.web.service;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.SmGrowthHandbookCfplevelRelation;
import com.linkwee.web.service.SmGrowthHandbookCfplevelRelationService;
 /**
 * 
 * @描述： SmGrowthHandbookCfplevelRelationService服务接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2017年07月26日 09:38:45
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface SmGrowthHandbookCfplevelRelationService extends GenericService<SmGrowthHandbookCfplevelRelation,Long>{

	/**
	 * 查询SmGrowthHandbookCfplevelRelation列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);
}
