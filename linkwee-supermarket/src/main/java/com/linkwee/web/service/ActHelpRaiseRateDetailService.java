package com.linkwee.web.service;

import java.util.List;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.activity.ActHelpRaiseRateDetail;
 /**
 * 
 * @描述： ActHelpRaiseRateDetailService服务接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2017年03月02日 10:39:20
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface ActHelpRaiseRateDetailService extends GenericService<ActHelpRaiseRateDetail,Long>{

	/**
	 * 查询ActHelpRaiseRateDetail列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);

	
	List<ActHelpRaiseRateDetail> queryHelpDetailList(String userId);


	List<ActHelpRaiseRateDetail> queryForUpdate(String userId, String openid);
	
}
