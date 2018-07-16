package com.linkwee.web.service;

import java.util.List;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.CimOrgFresGradeType;
import com.linkwee.web.model.cim.OrgGradeType;
 /**
 * 
 * @描述： CimOrgFresGradeTypeService服务接口
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2018年06月07日 14:14:21
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

	/**
	 * 根据机构编码查询机构FRES评分体系
	 * @param orgNumber
	 * @return
	 */
	List<OrgGradeType> queryGradeTypeEdit(String orgNumber);
}
