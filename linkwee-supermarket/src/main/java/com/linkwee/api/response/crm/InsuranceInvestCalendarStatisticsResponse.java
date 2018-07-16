package com.linkwee.api.response.crm;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linkwee.core.jackson.MoneySerializer;

public class InsuranceInvestCalendarStatisticsResponse {
	
	/**
	 * 累计投资金额-保险
	 */
	@JsonSerialize(using = MoneySerializer.class)
	private BigDecimal insuranceInvestAmtTotal;
		
	/**
	 * 累计佣金-保险
	 */
	@JsonSerialize(using = MoneySerializer.class)
	private BigDecimal insuranceFeeAmountSumTotal;

	public BigDecimal getInsuranceInvestAmtTotal() {
		return insuranceInvestAmtTotal;
	}

	public void setInsuranceInvestAmtTotal(BigDecimal insuranceInvestAmtTotal) {
		this.insuranceInvestAmtTotal = insuranceInvestAmtTotal;
	}

	public BigDecimal getInsuranceFeeAmountSumTotal() {
		return insuranceFeeAmountSumTotal;
	}

	public void setInsuranceFeeAmountSumTotal(BigDecimal insuranceFeeAmountSumTotal) {
		this.insuranceFeeAmountSumTotal = insuranceFeeAmountSumTotal;
	}
}
