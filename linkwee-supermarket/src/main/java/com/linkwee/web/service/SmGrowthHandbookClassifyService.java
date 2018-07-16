package com.linkwee.web.service;

import java.util.List;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.SmGrowthHandbookClassify;
 /**
 * 
 * @描述： SmGrowthHandbookClassifyService服务接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2017年07月25日 14:30:29
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface SmGrowthHandbookClassifyService extends GenericService<SmGrowthHandbookClassify,Long>{

	/**
	 * 查询SmGrowthHandbookClassify列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);

	/**
	 * 成长手册分类
	 * @param condition
	 * @return
	 */
	List<SmGrowthHandbookClassify> classify(SmGrowthHandbookClassify condition);
}
