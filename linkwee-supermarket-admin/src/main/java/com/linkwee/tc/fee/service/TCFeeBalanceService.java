package com.linkwee.tc.fee.service;

import java.util.Date;
import java.util.List;

import com.linkwee.core.base.ResponseResult;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.tc.fee.model.TCFeebalance;
import com.linkwee.web.request.tc.FeeDetailRequest;
import com.linkwee.web.request.tc.FeeRequest;
import com.linkwee.web.response.tc.FeeSummaryResponse;
import com.linkwee.web.response.tc.FeebalanceListResponse;

public interface TCFeeBalanceService {
	
	
	boolean isSummary(String biz,Integer type);
	
	void insertFeeSummary(String month, String[] date);
	
	FeeSummaryResponse getSummary(String month);
	
	void insertFeebalances(String  month, Date time,String begintime,String endtime,Page<TCFeebalance> page)throws Exception;
	
	DataTableReturn getFeebalanceList(FeeRequest feeRequest);
	
	DataTableReturn getFeebalanceRecordByMobile(FeeDetailRequest feeRequest);
	
	DataTableReturn getFeeDetailRecord(FeeDetailRequest feeRequest);
	
	List<FeebalanceListResponse> getFeebalanceListByMonth();
	
	ResponseResult feePay(String operator) throws Exception;
	
	
	
	int updateStatusAndGetCalculateCount(String begintime,String endTime,List<Integer> beforeStatus,Integer afterStatus);
}
