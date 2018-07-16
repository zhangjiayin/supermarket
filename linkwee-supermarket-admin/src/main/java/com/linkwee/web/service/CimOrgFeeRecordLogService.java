package com.linkwee.web.service;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.CimOrgFeeRecordLog;
import com.linkwee.web.service.CimOrgFeeRecordLogService;
 /**
 * 
 * @描述： CimOrgFeeRecordLogService服务接口
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2017年05月03日 18:30:26
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CimOrgFeeRecordLogService extends GenericService<CimOrgFeeRecordLog,Long>{

	/**
	 * 查询CimOrgFeeRecordLog列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);

	/**
	 * 写入日志记录
	 * @param orgNumber
	 * @param username
	 * @param remark
	 */
	void insertLog(String orgNumber, String username, String remark);
}
