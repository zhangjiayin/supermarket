package com.linkwee.tc.fee.service.impl;

import org.springframework.stereotype.Component;

import com.linkwee.tc.fee.model.FeeSummaryBuilder;
import com.linkwee.web.model.acc.AcBalanceRecord;
import com.linkwee.web.response.tc.CurrentMonthPayFeeStatistics;

@Component("insuranceCurrentMonthPayTeamManagementFeeService")
public class InsuranceCurrentMonthPayTeamManagementFeeService extends AbstractCurrentMonthPayFeeService{

	private static final Integer PRODUCT_CLASSIFY = 1;//0-网贷  1-保险
	
	private static String transType = "1006";

	@Override
	protected String getFeeType() {
		return transType;
	}

	@Override
	protected void setFeeSummary(CurrentMonthPayFeeStatistics currentMonthPayFeeStatistics,FeeSummaryBuilder feeSummaryBuilder) {
		feeSummaryBuilder.insuranceTeamManagementFee(currentMonthPayFeeStatistics);
	}

	@Override
	protected AcBalanceRecord getBalanceRecord(String month) {
		return new AcBalanceRecord(23, "保险团队管理津贴", month +"月保险团队管理津贴到账");
	}

	@Override
	protected Integer getProductClassify() {
		// TODO Auto-generated method stub
		return PRODUCT_CLASSIFY;
	}
}
