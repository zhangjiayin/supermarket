package com.linkwee.web.service;

import java.math.BigDecimal;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.CimFundOrder;
import com.linkwee.xoss.funds.sdk.ifast.model.HoldingsStatistic;
 /**
 * 
 * @描述： CimFundOrderService服务接口
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2017年10月24日 17:36:45
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CimFundOrderService extends GenericService<CimFundOrder,Long>{

	/**
	 * 查询CimFundOrder列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);
	
	/**
	 * 根据userId查询客户购买基金额总金额
	 * @param userId
	 * @return
	 */
	BigDecimal getTransactionAmountByUserId(String userId);
	
	/**
	 * 根据userId查询客户总资产
	 * @param userId
	 * @return
	 */
	HoldingsStatistic getOnTransactionAmountByUserId(String userId);
}
