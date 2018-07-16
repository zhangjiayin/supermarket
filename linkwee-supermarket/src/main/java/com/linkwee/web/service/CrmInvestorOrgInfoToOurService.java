package com.linkwee.web.service;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.CrmInvestorOrgInfoToOur;
import com.linkwee.web.service.CrmInvestorOrgInfoToOurService;
 /**
 * 
 * @描述： CrmInvestorOrgInfoToOurService服务接口
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2018年03月01日 17:30:46
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CrmInvestorOrgInfoToOurService extends GenericService<CrmInvestorOrgInfoToOur,Long>{

	/**
	 * 查询CrmInvestorOrgInfoToOur列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);

	/**
	 * 该用户是否已经绑定机构账号
	 * @param orgNumber
	 * @param userId
	 * @return
	 */
	boolean ifExistBindAccount(String orgNumber, String userId);
}
