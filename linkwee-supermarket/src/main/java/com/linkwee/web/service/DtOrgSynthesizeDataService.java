package com.linkwee.web.service;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.DtOrgSynthesizeData;
import com.linkwee.web.service.DtOrgSynthesizeDataService;
 /**
 * 
 * @描述： DtOrgSynthesizeDataService服务接口
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2018年06月06日 16:49:42
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface DtOrgSynthesizeDataService extends GenericService<DtOrgSynthesizeData,Long>{

	/**
	 * 查询DtOrgSynthesizeData列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);

	/**
	 * 根据机构编码获取最新的机构综合数据
	 * @param orgNumber
	 * @return
	 */
	DtOrgSynthesizeData getNewestOrgData(String orgNumber);
}
