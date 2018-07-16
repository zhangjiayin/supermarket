package com.linkwee.web.service;

import com.linkwee.core.base.ReturnCode;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.SmGrowthHandbook;
import com.linkwee.web.model.SmGrowthHandbookClassify;
import com.linkwee.web.request.HandbookRequest;
import com.linkwee.web.service.SmGrowthHandbookService;
 /**
 * 
 * @描述： SmGrowthHandbookService服务接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2017年07月26日 09:37:59
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
	 * 成长手册列表
	 * @param handbookRequest
	 * @param dataTable
	 * @return
	 */
	DataTableReturn findHandbookList(HandbookRequest handbookRequest,DataTable dataTable);

	/**
	 * 更新成长手册
	 * @param convertHandbook
	 * @return
	 */
	ReturnCode updateHandbook(HandbookRequest convertHandbook);

	/**
	 * 保存成长手册
	 * @param convertHandbook
	 * @return
	 */
	ReturnCode saveHandbook(HandbookRequest convertHandbook);

	/**
	 * 成长手册上下架
	 * @param id
	 * @param status
	 * @return
	 */
	ReturnCode updateStatus(String id, String status);

	/**
	 * 更新成长手册类型名
	 * @param classify
	 */
	void updateHandbookTypeName(SmGrowthHandbookClassify classify);

	/**
	 * 按类型更新成长手册状态
	 * @param parseInt
	 */
	void updateHandbookStatusByType(int parseInt);
}
