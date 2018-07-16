package com.linkwee.tc.fee.service.impl;

import org.springframework.stereotype.Component;

import com.linkwee.tc.fee.model.FeeSummaryBuilder;
import com.linkwee.web.model.acc.AcBalanceRecord;
import com.linkwee.web.response.tc.CurrentMonthPayFeeStatistics;

@Component("currentMonthPayPersonAddFeeService")
public class CurrentMonthPayPersonAddFeeService extends AbstractCurrentMonthPayFeeService{
	
	private static final Integer PRODUCT_CLASSIFY = 0;//0-网贷  1-保险
	
	private static final String TYPE ="1021";//个人加佣券加佣
	
	@Override
	protected String getFeeType() {
		return TYPE;
	}

	@Override
	protected Integer getProductClassify() {
		return PRODUCT_CLASSIFY;
	}

	@Override
	protected void setFeeSummary(CurrentMonthPayFeeStatistics currentMonthPayFeeStatistics,FeeSummaryBuilder feeSummaryBuilder) {
		feeSummaryBuilder.personFeeAdd(currentMonthPayFeeStatistics);	
	}

	@Override
	protected AcBalanceRecord getBalanceRecord(String month) {
		// TODO Auto-generated method stub
		return new AcBalanceRecord(24, "个人加拥", month +"月份个人加拥佣金到账");
	}

}
