package com.linkwee.web.service;

import java.util.List;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.crm.CrmCfplannerOperation;
 /**
 * 
 * @描述： CrmCfplannerOperationService服务接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年08月09日 10:33:49
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CrmCfplannerOperationService extends GenericService<CrmCfplannerOperation,Long>{

	/**
	 * 查询CrmCfplannerOperation列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);

	/**
	 * 更换上级记录
	 * @param userId
	 * @return
	 */
	List<CrmCfplannerOperation> queryChangeParentRecordList(String userId);
}
