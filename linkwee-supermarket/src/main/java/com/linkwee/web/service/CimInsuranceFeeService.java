package com.linkwee.web.service;

import java.util.List;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.tc.fee.model.vo.InsuranceFeedetailWrapper;
import com.linkwee.web.model.CimInsuranceFee;
import com.linkwee.web.service.CimInsuranceFeeService;
 /**
 * 
 * @描述： CimInsuranceFeeService服务接口
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2017年12月01日 17:03:46
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CimInsuranceFeeService extends GenericService<CimInsuranceFee,Long>{

	/**
	 * 查询CimInsuranceFee列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);

	/**
	 * 保存佣金
	 * @param insuranceFeedetailWrappers
	 */
	void saveFees(InsuranceFeedetailWrapper[] insuranceFeedetailWrappers);

	/**
	 * 根据投资订单编号查询保险佣金数据
	 * @param billId
	 * @return
	 */
	List<CimInsuranceFee> selectByBillId(String billId);
}
