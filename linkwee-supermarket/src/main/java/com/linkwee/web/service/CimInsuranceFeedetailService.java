package com.linkwee.web.service;

import java.util.List;

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
	 * 根据投资记录id和受益人id查询对应的保险佣金明细
	 * @param billId
	 * @param userId
	 * @return
	 */
	List<CimInsuranceFeedetail> selectCimFeeByBillIdProfit(String billId,String userId);
}
