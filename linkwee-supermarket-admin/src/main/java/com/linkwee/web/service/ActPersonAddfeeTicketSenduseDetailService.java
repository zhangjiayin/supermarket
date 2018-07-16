package com.linkwee.web.service;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.ActPersonAddfeeTicketSenduseDetail;
import com.linkwee.web.service.ActPersonAddfeeTicketSenduseDetailService;
 /**
 * 
 * @描述： ActPersonAddfeeTicketSenduseDetailService服务接口
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2018年04月12日 16:02:11
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface ActPersonAddfeeTicketSenduseDetailService extends GenericService<ActPersonAddfeeTicketSenduseDetail,Long>{

	/**
	 * 查询ActPersonAddfeeTicketSenduseDetail列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);
}
