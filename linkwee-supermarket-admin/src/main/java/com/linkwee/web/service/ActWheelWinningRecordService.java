package com.linkwee.web.service;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.ActWheelWinningRecord;
import com.linkwee.web.service.ActWheelWinningRecordService;
 /**
 * 
 * @描述： ActWheelWinningRecordService服务接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2018年04月09日 12:48:46
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface ActWheelWinningRecordService extends GenericService<ActWheelWinningRecord,Long>{

	/**
	 * 查询ActWheelWinningRecord列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);
}
