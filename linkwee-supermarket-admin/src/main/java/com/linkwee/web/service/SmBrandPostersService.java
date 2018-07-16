package com.linkwee.web.service;

import java.util.List;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.SmBrandPosters;
import com.linkwee.web.response.acc.SmBrandPosterResponse;
 /**
 * 
 * @描述： SmBrandPostersService服务接口
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2018年01月06日 15:13:33
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface SmBrandPostersService extends GenericService<SmBrandPosters,Long>{

	/**
	 * 查询SmBrandPosters列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);

	DataTableReturn findBrandPosters(SmBrandPosters pageRequest, DataTable dataTable);

	DataTableReturn selectByDatatables(SmBrandPosters smBrandPromotion, DataTable dataTable);

	/**
     * 顶置
     * */
	void overheadSmBrandPosters(String typeValue);

	List<SmBrandPosterResponse> selectBrandPosterList();
}
