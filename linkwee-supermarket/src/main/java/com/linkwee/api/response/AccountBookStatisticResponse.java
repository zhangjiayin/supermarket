package com.linkwee.api.response;

import com.linkwee.core.base.BaseEntity;

public class AccountBookStatisticResponse extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5061175192317331456L;
	/**
	 * 待收本金
	 */
	private String investTotalAmt;
	/**
	 * 待收收益
	 */
	private String investTotalProfit;
	/**
	 * 待收总额
	 */
	private String investTotal = "0.00";
	
	public String getInvestTotalAmt() {
		return investTotalAmt;
	}
	public void setInvestTotalAmt(String investTotalAmt) {
		this.investTotalAmt = investTotalAmt;
	}
	public String getInvestTotalProfit() {
		return investTotalProfit;
	}
	public void setInvestTotalProfit(String investTotalProfit) {
		this.investTotalProfit = investTotalProfit;
	}
	public String getInvestTotal() {
		return investTotal;
	}
	public void setInvestTotal(String investTotal) {
		this.investTotal = investTotal;
	}
}
