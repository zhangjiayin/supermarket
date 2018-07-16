package com.linkwee.web.service;

import java.util.List;
import java.util.Map;

import com.linkwee.core.base.api.PaginatorResponse;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.model.SmGrowthHandbook;
 /**
 * 
 * @描述： SmGrowthHandbookService服务接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2017年07月25日 14:29:27
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface SmGrowthHandbookService extends GenericService<SmGrowthHandbook,Long>{

	/**
	 * 查询SmGrowthHandbook列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);

	/**
	 * 成长手册分类列表
	 * @param page
	 * @param conditions
	 * @return
	 */
	PaginatorResponse<SmGrowthHandbook> classifyList(Page<SmGrowthHandbook> page, Map<String, Object> conditions);

	/**
	 * 个人定制列表
	 * @param conditions
	 * @return
	 */
	List<SmGrowthHandbook> personalCustomizationList(Map<String, Object> conditions);
}
