package com.linkwee.web.service;

import com.linkwee.api.request.cim.CimInsuranceQuestionSummaryRequest;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.cim.CimInsuranceQuestionSummary;
import com.linkwee.web.service.CimInsuranceQuestionSummaryService;
 /**
 * 
 * @描述： CimInsuranceQuestionSummaryService服务接口
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2017年12月29日 15:37:10
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CimInsuranceQuestionSummaryService extends GenericService<CimInsuranceQuestionSummary,Long>{

	/**
	 * 查询CimInsuranceQuestionSummary列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);
	
	/**
	 * 保险评测接口
	 * */
	void insertCimInsuranceQuestionSummary(String userId, CimInsuranceQuestionSummaryRequest req);

	/**
	 * 保险评测结果
	 * */
	CimInsuranceQuestionSummary queryQquestionResult(String userId);
}
