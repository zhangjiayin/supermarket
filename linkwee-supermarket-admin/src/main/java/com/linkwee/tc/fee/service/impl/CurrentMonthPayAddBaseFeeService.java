package com.linkwee.tc.fee.service.impl;

import org.springframework.stereotype.Component;

import com.linkwee.tc.fee.model.FeeSummaryBuilder;
import com.linkwee.web.model.acc.AcBalanceRecord;
import com.linkwee.web.response.tc.CurrentMonthPayFeeStatistics;

@Component("currentMonthPayAddBaseFeeService")
public class CurrentMonthPayAddBaseFeeService extends AbstractCurrentMonthPayFeeService {
	
	private static final Integer PRODUCT_CLASSIFY = 0;//0-网贷  1-保险
	
	private static final String TYPE ="1011";
	
	@Override
	protected String getFeeType() {
		return TYPE;
	}

	@Override
	protected void setFeeSummary(CurrentMonthPayFeeStatistics currentMonthPayFeeStatistics,FeeSummaryBuilder feeSummaryBuilder) {
		feeSummaryBuilder.baseFeeAdd(currentMonthPayFeeStatistics);
	}

	@Override
	protected AcBalanceRecord getBalanceRecord(String month) {
		return new AcBalanceRecord(18, "基础加拥", month +"月份基础加拥佣金到账");
	}

	@Override
	protected Integer getProductClassify() {
		// TODO Auto-generated method stub
		return PRODUCT_CLASSIFY;
	}
}
