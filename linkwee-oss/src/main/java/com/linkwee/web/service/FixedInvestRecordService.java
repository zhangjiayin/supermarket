package com.linkwee.web.service;

import org.apache.ibatis.annotations.Param;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.FixedInvestRecord;
import com.linkwee.web.request.InvestRecordRequest;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年06月07日 10:42:59
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface FixedInvestRecordService extends GenericService<FixedInvestRecord,Long>{

	/**
	 * 查询FixedInvestRecord列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable,InvestRecordRequest investRecordRequest);
}
