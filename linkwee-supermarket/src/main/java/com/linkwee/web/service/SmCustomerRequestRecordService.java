package com.linkwee.web.service;

import com.linkwee.api.request.sm.AddRequestRecordRequest;
import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.SmCustomerRequestRecord;
import com.linkwee.web.service.SmCustomerRequestRecordService;
import com.linkwee.xoss.api.AppRequestHead;
 /**
 * 
 * @描述： SmCustomerRequestRecordService服务接口
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2018年05月23日 09:46:16
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface SmCustomerRequestRecordService extends GenericService<SmCustomerRequestRecord,Long>{

	/**
	 * 查询SmCustomerRequestRecord列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);

	/**
	 * 添加请求记录
	 * @param appRequestHead
	 * @param addRequestRecordRequest
	 * @return
	 */
	BaseResponse addRequestRecord(AppRequestHead appRequestHead,AddRequestRecordRequest addRequestRecordRequest);
}
