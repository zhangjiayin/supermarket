package com.linkwee.web.service;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.CimOrgFresGradeType;
import com.linkwee.web.service.CimOrgFresGradeTypeService;
 /**
 * 
 * @描述： CimOrgFresGradeTypeService服务接口
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2018年06月07日 17:06:03
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CimOrgFresGradeTypeService extends GenericService<CimOrgFresGradeType,Long>{

	/**
	 * 查询CimOrgFresGradeType列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);
}
