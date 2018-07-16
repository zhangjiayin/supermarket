package com.linkwee.web.service;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.ActOneYuanDrawVirtualAddfourtuneRecord;
import com.linkwee.web.request.PrizeSendRequest;
import com.linkwee.web.service.ActOneYuanDrawVirtualAddfourtuneRecordService;
 /**
 * 
 * @描述： ActOneYuanDrawVirtualAddfourtuneRecordService服务接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2018年04月09日 14:19:15
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface ActOneYuanDrawVirtualAddfourtuneRecordService extends GenericService<ActOneYuanDrawVirtualAddfourtuneRecord,Long>{

	/**
	 * 查询ActOneYuanDrawVirtualAddfourtuneRecord列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);

	/**
	 * 增加幸运积分历史记录
	 * @param prizeSendRequest
	 * @param dataTable
	 * @return
	 */
	DataTableReturn addFourtuneHistory(PrizeSendRequest prizeSendRequest,DataTable dataTable);
}
