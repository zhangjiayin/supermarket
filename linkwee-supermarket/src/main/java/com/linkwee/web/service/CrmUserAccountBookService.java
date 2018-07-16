package com.linkwee.web.service;

import com.linkwee.api.response.AccountBookStatisticResponse;
import com.linkwee.core.base.api.PaginatorResponse;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.model.CrmUserAccountBook;
import com.linkwee.web.service.CrmUserAccountBookService;
 /**
 * 
 * @描述： CrmUserAccountBookService服务接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2017年10月14日 15:31:08
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CrmUserAccountBookService extends GenericService<CrmUserAccountBook,Long>{

	/**
	 * 记账本统计
	 * @param userId
	 * @return
	 */
	AccountBookStatisticResponse statistics(String userId);

	/**
	 * 在投列表
	 * @param page
	 * @param userId
	 * @return
	 */
	PaginatorResponse<CrmUserAccountBook> investingList(Page<CrmUserAccountBook> page, String userId);
}
