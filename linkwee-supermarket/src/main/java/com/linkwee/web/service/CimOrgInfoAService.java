package com.linkwee.web.service;

import java.util.List;
import java.util.Map;

import com.linkwee.api.request.cim.OrginfoaDetailRequest;
import com.linkwee.api.request.cim.OrginfoaListRequest;
import com.linkwee.api.request.cim.OrginfoaPageListRequest;
import com.linkwee.api.response.cim.OrginfoaDetailResponse;
import com.linkwee.api.response.cim.OrginfoaPageListResponse;
import com.linkwee.core.base.api.PaginatorResponse;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.CimOrgInfoA;
import com.linkwee.web.request.orgInfo.OrgUrlSkipParameterRequest;
import com.linkwee.web.service.CimOrgInfoAService;
import com.linkwee.xoss.api.AppRequestHead;
 /**
 * 
 * @描述： CimOrgInfoAService服务接口
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2018年05月09日 16:41:24
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CimOrgInfoAService extends GenericService<CimOrgInfoA,Long>{

	/**
	 * 查询CimOrgInfoA列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);

	/**
	 * 查询A平台机构列表
	 * @param orginfoaPageListRequest
	 * @return
	 */
	PaginatorResponse<OrginfoaPageListResponse> queryPageList(OrginfoaPageListRequest orginfoaPageListRequest);

	/**
	 * 查询A机构机构详情
	 * @param orginfoaDetailRequest
	 * @return
	 */
	OrginfoaDetailResponse queryOrginfoaDetail(OrginfoaDetailRequest orginfoaDetailRequest);
	
	/**
	 * 根据机构编码查询A平台机构详情
	 * @param orgNumber
	 * @return
	 */
	OrginfoaDetailResponse queryOrginfoaDetail(String orgNumber);
	
	/**
	 * 根据机构编码查询机构详情
	 * @param orgNumber
	 * @return
	 */
	CimOrgInfoA queryOrginfoa(String orgNumber);

	 /**
	  * A专区机构跳转参数
	  * @param orgUrlSkipParameterRequest
	  * @param head
	  * @return
	  */
	Map<String,String> getOrgUrlSkipParameter(OrgUrlSkipParameterRequest orgUrlSkipParameterRequest, AppRequestHead head);

	 /**
	  * 查询A机构列表
	  * @param orginfoaListRequest
	  * @return
	  */
	 List<CimOrgInfoA> queryOrginfoaList(OrginfoaListRequest orginfoaListRequest);
 }
