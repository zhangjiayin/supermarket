package com.linkwee.web.service;

import java.util.List;
import java.util.Map;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.tc.fee.model.vo.InsuranceFeedetailWrapper;
import com.linkwee.web.model.CimInsuranceFeedetail;
import com.linkwee.web.model.CimInsuranceFeedetailExtends;
 /**
 * 
 * @描述： CimInsuranceFeedetailService服务接口
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2017年12月01日 17:03:46
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CimInsuranceFeedetailService extends GenericService<CimInsuranceFeedetail,Long>{

	/**
	 * 查询CimInsuranceFeedetail列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);
	/**
	 * 保存保险佣金明细
	 * @param insuranceFeedetailWrappers
	 */
	void insertFeedetail(InsuranceFeedetailWrapper[] insuranceFeedetailWrappers) throws Exception;

	/**
	 * 根据订单编号查询最初的佣金结算明细信息
	 * @param billId
	 * @return
	 */
	List<CimInsuranceFeedetailExtends> queryInitInsuranceFeeDetailByBillId(String billId);
	
	/**
	 * 根据理财师编号与佣金类型 批量更新佣金明细结算状态
	 * @param cfplannerIds 获利理财师userid list
	 * @param feeType 佣金类型
	 * @param balanceMaps  获利理财师与结算单号map
	 * @param balanceStatus 结算状态：0=未结算|1=结算中|2=结算成功|3=结算失败
	 * @param begintime 开始时间
	 * @param endTime	结束时间
	 * @return
	 */
	int batchUpdateBalanceStatusBycfplannerIdAndFeeType(List<String> cfplannerIds, String feeType,List<Map<String, String>> balanceMaps, int balanceStatus, String begintime,String endTime);
}
