package com.linkwee.web.service;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.CimFundInfo;
 /**
 * 
 * @描述： CimFundInfoService服务接口
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2017年08月14日 10:15:42
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CimFundInfoService extends GenericService<CimFundInfo,Long>{

	/**
	 * 查询CimFundInfo列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);

	/**
	 * 根据基金名称查询机构自己秘钥
	 * @param orgNumber
	 * @return
	 */
	String getSelfSecretByOrgNumber(String orgNumber);
	
	/**
	 * 根据基金机构名称查询基金配置信息
	 * @param orgNumber
	 * @return
	 */
	CimFundInfo selectOneByOrgNumber(String orgNumber);
}
