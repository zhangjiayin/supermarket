package com.linkwee.web.service;

import java.util.List;

import com.linkwee.api.request.cim.OrgMoneyDataRequest;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.DtOrgNewMoneyWeek;
import com.linkwee.web.model.cim.OrgMoneyDataDetail;
import com.linkwee.web.service.DtOrgNewMoneyWeekService;
 /**
 * 
 * @描述： DtOrgNewMoneyWeekService服务接口
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2018年06月06日 16:49:42
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface DtOrgNewMoneyWeekService extends GenericService<DtOrgNewMoneyWeek,Long>{

	/**
	 * 查询DtOrgNewMoneyWeek列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);

	/**
	 * 查询机构每周成交量
	 * @param orgMoneyDataRequest
	 * @return
	 */
	List<OrgMoneyDataDetail> queryOrgdata(OrgMoneyDataRequest orgMoneyDataRequest);
}
