package com.linkwee.web.service;

import com.linkwee.core.base.ReturnCode;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.ActForgeInvestAmount;
import com.linkwee.web.service.ActForgeInvestAmountService;
 /**
 * 
 * @描述： ActForgeInvestAmountService服务接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2017年08月25日 10:10:09
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface ActForgeInvestAmountService extends GenericService<ActForgeInvestAmount,Long>{

	/**
	 * 查询ActForgeInvestAmount列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);

	/**
	 * 数据列表
	 * @param actForgeInvestAmount
	 * @param dataTable
	 * @return
	 */
	DataTableReturn findForgeinvestamountList(ActForgeInvestAmount actForgeInvestAmount, DataTable dataTable);

	/**
	 * 删除数据
	 * @param parseInt
	 * @return
	 */
	ReturnCode deleteForgeinvestamountList(int parseInt);

	/**
	 * 更新数据
	 * @param convert
	 * @return
	 */
	ReturnCode updateActForgeInvestAmount(ActForgeInvestAmount convert);

	/**
	 * 添加数据
	 * @param convert
	 * @return
	 */
	ReturnCode saveActForgeInvestAmount(ActForgeInvestAmount convert);
}
