package com.linkwee.tc.fee.service.impl;

import org.springframework.stereotype.Component;

import com.linkwee.tc.fee.model.FeeSummaryBuilder;
import com.linkwee.web.model.acc.AcBalanceRecord;
import com.linkwee.web.response.tc.CurrentMonthPayFeeStatistics;
@Component("currentMonthPayRecommendFeeService")
public class CurrentMonthPayRecommendFeeService extends AbstractCurrentMonthPayFeeService{
	
	private static final Integer PRODUCT_CLASSIFY = 0;//0-网贷  1-保险

	private static final String TYPE ="1002";
	
	@Override
	protected String getFeeType() {
		return TYPE;
	}

	@Override
	protected void setFeeSummary(CurrentMonthPayFeeStatistics currentMonthPayFeeStatistics,FeeSummaryBuilder feeSummaryBuilder) {
		feeSummaryBuilder.recommendFee(currentMonthPayFeeStatistics);
		
	}

	@Override
	protected AcBalanceRecord getBalanceRecord(String month) {
		return new AcBalanceRecord(13, "推荐奖励", month +"月份推荐奖励到账");
	}

	@Override
	protected Integer getProductClassify() {
		// TODO Auto-generated method stub
		return PRODUCT_CLASSIFY;
	}

}
