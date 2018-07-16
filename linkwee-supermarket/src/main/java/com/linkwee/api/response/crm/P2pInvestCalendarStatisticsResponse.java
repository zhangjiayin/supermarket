package com.linkwee.api.response.crm;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linkwee.core.jackson.MoneySerializer;

public class P2pInvestCalendarStatisticsResponse {
	
	/**
	 * 累计投资金额-网贷
	 */
	@JsonSerialize(using = MoneySerializer.class)
	private BigDecimal p2pInvestAmtTotal;
		
	/**
	 * 累计佣金-网贷
	 */
	@JsonSerialize(using = MoneySerializer.class)
	private BigDecimal p2pFeeAmountSumTotal;

	public BigDecimal getP2pInvestAmtTotal() {
		return p2pInvestAmtTotal;
	}

	public void setP2pInvestAmtTotal(BigDecimal p2pInvestAmtTotal) {
		this.p2pInvestAmtTotal = p2pInvestAmtTotal;
	}

	public BigDecimal getP2pFeeAmountSumTotal() {
		return p2pFeeAmountSumTotal;
	}

	public void setP2pFeeAmountSumTotal(BigDecimal p2pFeeAmountSumTotal) {
		this.p2pFeeAmountSumTotal = p2pFeeAmountSumTotal;
	}
	
	
}
