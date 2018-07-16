package com.linkwee.web.service;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.activity.ActHelpRaiseRate;
 /**
 * 
 * @描述： ActHelpRaiseRateService服务接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2017年03月02日 11:39:07
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface ActHelpRaiseRateService extends GenericService<ActHelpRaiseRate,Long>{

	/**
	 * 查询ActHelpRaiseRate列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);

	/**
	 * 助力
	 * @return
	 */
	Double help(String userId, String weixinIcoUrl, String weixinNickname, String openid) throws Exception;
	
}
