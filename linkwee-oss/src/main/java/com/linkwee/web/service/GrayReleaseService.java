package com.linkwee.web.service;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.GrayRelease;
import com.linkwee.web.service.GrayReleaseService;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2016年06月27日 15:52:14
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface GrayReleaseService extends GenericService<GrayRelease,Long>{

	/**
	 * 查询GrayRelease列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);
	
	/**
     * 根据手机号码查询灰度用户
     * @param mobile
     * @return
     */
	GrayRelease selectByMobile(String mobile);
}
