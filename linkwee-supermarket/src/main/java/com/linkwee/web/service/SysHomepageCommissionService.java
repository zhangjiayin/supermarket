package com.linkwee.web.service;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.SysHomepageCommission;
 /**
 * 
 * @描述： SysHomepageCommissionService服务接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2017年06月21日 10:34:25
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface SysHomepageCommissionService extends GenericService<SysHomepageCommission,Long>{

	/**
	 * 查询SysHomepageCommission列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);

	/**
	 * 查询最新的首页发放佣金
	 * @return
	 */
	SysHomepageCommission selectNewest();

	/**
	 * 2017年最新的首页发放佣金
	 * @return
	 */
	SysHomepageCommission select2017Newest();
}
