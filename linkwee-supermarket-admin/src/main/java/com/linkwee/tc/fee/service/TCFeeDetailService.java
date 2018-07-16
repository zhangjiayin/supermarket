package com.linkwee.tc.fee.service;

import java.util.List;
import java.util.Map;

import com.linkwee.core.generic.GenericService;
import com.linkwee.tc.fee.model.TCFeedetail;
import com.linkwee.tc.fee.model.vo.FeedetailWrapper;

/**
 * 佣金明细服务
 * @author ch
 * @serial 2016-07-22 15:09:52
 *
 */
public interface TCFeeDetailService extends GenericService<TCFeedetail,Long>{
	
	/**
	 * 插入佣金明细
	 * @param feedetailWrapper
	 */
	void insertFeedetail(FeedetailWrapper... feedetailWrapper);
	
	int batchUpdateBalanceStatus(List<String> cfplannerIds,List<Map<String, String>> balanceMaps,int balanceStatus,String begintime,String endTime);
	
	/**
	 * 根据理财师编号与佣金类型 批量更新佣金明细结算状态
	 * @param cfplannerIds
	 * @param feeType
	 * @param balanceMaps
	 * @param balanceStatus
	 * @param begintime
	 * @param endTime
	 * @return
	 */
	int batchUpdateBalanceStatusBycfplannerIdAndFeeType(List<String> cfplannerIds,String feeType,List<Map<String, String>> balanceMaps,int balanceStatus,String begintime,String endTime);
}
