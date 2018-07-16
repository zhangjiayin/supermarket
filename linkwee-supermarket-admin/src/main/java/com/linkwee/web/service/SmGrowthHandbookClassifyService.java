package com.linkwee.web.service;

import com.linkwee.core.base.ReturnCode;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.SmGrowthHandbookClassify;
import com.linkwee.web.service.SmGrowthHandbookClassifyService;
 /**
 * 
 * @描述： SmGrowthHandbookClassifyService服务接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2017年07月26日 09:37:14
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
	 * 成长手册分类列表
	 * @param request
	 * @param dataTable
	 * @return
	 */
	DataTableReturn findhandbookClassifyList(SmGrowthHandbookClassify request,DataTable dataTable);

	/**
	 * 更新成长手册分类
	 * @param classify
	 * @return
	 */
	ReturnCode updateHandbookClassify(SmGrowthHandbookClassify classify);

	/**
	 * 新增成长手册分类
	 * @param classify
	 * @return
	 */
	ReturnCode saveHandbookClassify(SmGrowthHandbookClassify classify);

	/**
	 * 删除成长手册分类（对应的成长手册失效）
	 * @param parseInt
	 * @return
	 */
	ReturnCode deleteHandbookClassify(int parseInt);
}
