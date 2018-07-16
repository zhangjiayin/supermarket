package com.linkwee.web.service;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.Msg;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： 陈佳良
 * 
 * @创建时间：2016年06月03日 17:34:00
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface MsgService extends GenericService<Msg,Long>{

	/**
	 * 查询Msg列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable,Integer type,Integer appType);
	
	/**
	 * 添加信息
	 * @param msg
	 * @return
	 */
	public void addMsg(Msg msg);
}
