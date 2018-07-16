package com.linkwee.web.service;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.CimOrgRiskFresGrade;
import com.linkwee.web.service.CimOrgRiskFresGradeService;
 /**
 * 
 * @描述： CimOrgRiskFresGradeService服务接口
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2018年06月07日 14:14:21
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CimOrgRiskFresGradeService extends GenericService<CimOrgRiskFresGrade,Long>{

	/**
	 * 查询CimOrgRiskFresGrade列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);

	/**
	 * 根据机构编码删除机构风控FRES评分
	 * @param orgNumber
	 */
	void deleteByOrgNumber(String orgNumber);
}
