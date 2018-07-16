package com.linkwee.web.service;

import java.util.List;

import com.linkwee.api.response.tc.CfpFeeInfoResponse;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.CimFee;
 /**
 * 
 * @描述： CimFeeService服务接口
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2017年10月19日 16:14:18
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CimFeeServiceNew extends GenericService<CimFee,Long>{

	/**
	 * 查询CimFee列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);
	
	/**
	 * 根据投资订单编号和获利理财师编号  查询该理财师该订单的佣金明细  按照佣金类型分类求和
	 * @param billId
	 * @param profitCfplannerId
	 * @return
	 */
	List<CimFee> selectCimFeeByBillIdProfit(String billId,String profitCfplannerId);

	/**
	 * 伪造的佣金发放记录
	 * @return
	 */
	List<CfpFeeInfoResponse> virtualFeeList();
}
