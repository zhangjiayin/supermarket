package com.linkwee.web.service;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.CrmOrgAcctRel;
 /**
 * 
 * @描述： CrmOrgAcctRelService服务接口
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2017年08月14日 10:15:42
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CrmOrgAcctRelService extends GenericService<CrmOrgAcctRel,Long>{

	/**
	 * 查询CrmOrgAcctRel列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);
	
	/**
	 * 判断机构账户是否存在
	 * @param userId
	 * @param orgNumber
	 * @return
	 */
	boolean ifOrgAccountExist(String userId,String orgNumber);
	
	/**
	 * 根据用户id和机构编码获取机构账号
	 * @param userId
	 * @param orgNumber
	 * @return
	 */
	String getOrgAccount(String userId,String orgNumber);

	/**
	 * 用户是否注册基金
	 * @param userId
	 * @return
	 */
	boolean hasRegFund(String userId);
}
