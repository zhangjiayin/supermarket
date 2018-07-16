package com.linkwee.web.service;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.WxAccessToken;

 /**
 * 
 * @描述： WxAccessTokenService服务接口
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2017年03月08日 17:14:05
 * 
 * Copyright (c) 深圳米格网络科技有限公司-版权所有
 */
public interface WxAccessTokenService extends GenericService<WxAccessToken,Long>{

	/**
	 * 查询WxAccessToken列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);

	/**
	 * 获取最新的微信AccessToken
	 * @return
	 */
	WxAccessToken selectNewAccessToken(int appType);
}
