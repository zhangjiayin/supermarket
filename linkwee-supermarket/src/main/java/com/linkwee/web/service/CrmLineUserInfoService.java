package com.linkwee.web.service;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import com.linkwee.api.request.crm.GetWelfareRequest;
import com.linkwee.core.base.api.PaginatorResponse;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.model.crm.CrmLineUserInfo;
 /**
 * 
 * @描述： CrmLineUserInfoService服务接口
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2017年05月19日 19:36:07
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CrmLineUserInfoService extends GenericService<CrmLineUserInfo,Long>{

	/**
	 * 查询CrmLineUserInfo列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);
	
	/**
	 * 线下活动-领取福利
	 * */
	void insetWelfare(GetWelfareRequest req) throws UnsupportedEncodingException ;

	/**
	 * 线下活动-理财师邀请记录
	 * */
	PaginatorResponse<CrmLineUserInfo> queryInvitationRecord(Page<CrmLineUserInfo> page,
			Map<String, Object> conditions);
}
