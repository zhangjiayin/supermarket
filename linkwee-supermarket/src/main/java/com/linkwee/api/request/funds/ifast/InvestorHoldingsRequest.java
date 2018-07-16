package com.linkwee.api.request.funds.ifast;

public class InvestorHoldingsRequest extends IfastBaseRequest {
	
	/**
	 * 用户的投资组合编码,则过滤返回属于该组合编码下的基金持有情况
	 */
	private String portfolioId;
	
	/**
	 * 基金代码,则过滤返回属于该基金代码的持有情况
	 */
	private String fundCode;

	public String getPortfolioId() {
		return portfolioId;
	}

	public void setPortfolioId(String portfolioId) {
		this.portfolioId = portfolioId;
	}

	public String getFundCode() {
		return fundCode;
	}

	public void setFundCode(String fundCode) {
		this.fundCode = fundCode;
	}
}
