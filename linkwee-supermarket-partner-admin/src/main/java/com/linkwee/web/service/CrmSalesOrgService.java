package com.linkwee.web.service;

import java.util.List;
import java.util.Map;

import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.CrmSalesOrg;
import com.linkwee.web.request.CustomerStatisticalRequest;
import com.linkwee.web.request.LcsStatisticalRequest;
import com.linkwee.web.request.TeamStatisticalRequest;
import com.linkwee.web.response.LcsStatisticalResponse;
import com.linkwee.web.response.TeamStatisticalResponse;
 /**
 * 
 * @描述： CrmSalesOrgService服务接口
 * 
 * @创建人： ch
 * 
 * @创建时间：2016年11月07日 11:41:24
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CrmSalesOrgService extends GenericService<CrmSalesOrg,Long>{
	
	List<Map<String, String>> getPlatfroms();

	LcsStatisticalResponse getLcsStatistical(String salesOrgId);
	
	DataTableReturn getLcsStatisticalList(String salesOrgId,LcsStatisticalRequest req);
	
	DataTableReturn getCustomerStatisticalList(String salesOrgId,CustomerStatisticalRequest req);
	
	
	TeamStatisticalResponse getTeamStatistical(String salesOrgId,TeamStatisticalRequest req);
	
	DataTableReturn getTeamStatisticalList(String salesOrgId,TeamStatisticalRequest req);
}
