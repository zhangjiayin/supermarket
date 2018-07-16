package com.linkwee.web.service;

import java.util.List;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableEditorOption;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.CimInsuranceCate;
import com.linkwee.web.service.CimInsuranceCateService;
 /**
 * 
 * @描述： CimInsuranceCateService服务接口
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2018年01月09日 14:10:36
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CimInsuranceCateService extends GenericService<CimInsuranceCate,Long>{

	/**
	 * 查询CimInsuranceCate列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);

	/**
	 * 查询datatable editor 选项
	 * @return
	 */
	List<DataTableEditorOption> getDataTableEditorOptions();
}
