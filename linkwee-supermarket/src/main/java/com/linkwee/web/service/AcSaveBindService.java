package com.linkwee.web.service;

import com.linkwee.api.request.acc.AcSaveBind;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
 /**
 * 
 * @描述： AcSaveBindService服务接口
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2018年06月29日 09:51:57
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface AcSaveBindService extends GenericService<AcSaveBind,Long>{

	/**
	 * 查询AcSaveBind列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);

	void saveBankCard(AcSaveBind req);
}
