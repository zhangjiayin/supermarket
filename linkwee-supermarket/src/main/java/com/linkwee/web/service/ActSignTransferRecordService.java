package com.linkwee.web.service;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.ActSignTransferRecord;
import com.linkwee.web.service.ActSignTransferRecordService;
 /**
 * 
 * @描述： ActSignTransferRecordService服务接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2017年11月14日 09:32:27
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface ActSignTransferRecordService extends GenericService<ActSignTransferRecord,Long>{

	/**
	 * 查询ActSignTransferRecord列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);
}
