package com.linkwee.tc.fee.service.impl;

import org.springframework.stereotype.Component;

import com.linkwee.tc.fee.model.FeeSummaryBuilder;
import com.linkwee.web.model.acc.AcBalanceRecord;
import com.linkwee.web.response.tc.CurrentMonthPayFeeStatistics;
@Component("insuranceCurrentMonthPayChildManagementFeeService")
public class InsuranceCurrentMonthPayChildManagementFeeService extends AbstractCurrentMonthPayFeeService{
	
	private static final Integer PRODUCT_CLASSIFY = 1;//0-网贷  1-保险

	private static final String TYPE ="1005";
	
	@Override
	protected String getFeeType() {
		return TYPE;
	}

	@Override
	protected void setFeeSummary(CurrentMonthPayFeeStatistics currentMonthPayFeeStatistics,FeeSummaryBuilder feeSummaryBuilder) {
		feeSummaryBuilder.insuranceChildManagementFee(currentMonthPayFeeStatistics);
		
	}

	@Override
	protected AcBalanceRecord getBalanceRecord(String month) {
		return new AcBalanceRecord(22, "保险直接管理津贴", month +"月份保险直接管理津贴到账");
	}

	@Override
	protected Integer getProductClassify() {
		// TODO Auto-generated method stub
		return PRODUCT_CLASSIFY;
	}

}
