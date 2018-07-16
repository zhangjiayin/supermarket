package com.linkwee.web.service;

import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.web.request.CustomerInvestRequest;

public interface TcInvestService {

	
	/**
	 * 统计用户在机构的投资信息
	 * @param req
	 * @return
	 */
	public DataTableReturn queryCustomerInvestStatistics(CustomerInvestRequest req);
	
	/**
	 * 查询用户在机构投资详情
	 * @param req
	 * @return
	 */
	public DataTableReturn queryCustomerInvestDetail(CustomerInvestRequest req);
}
