package com.linkwee.web.service;

import java.util.Map;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.CimOrgFeeGather;
 /**
 * 
 * @描述： CimOrgFeedetailService服务接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年07月28日 17:06:53
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CimOrgFeeGatherService extends GenericService<CimOrgFeeGather,Long>{

	/**
	 * 查询CimOrgFeedetail列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);
	
   
	 public DataTableReturn queryOrgSaleFee(Map<String,Object> params, DataTable dataTable) throws Exception;	
}
