package com.linkwee.api.request.funds.ifast;

public class InvestorOrderInfoRequest extends GetOrderListRequest{

	//组合编码,如提供，则返回过滤后的数据	
	private	String	portfolioId;
	
	public String getPortfolioId() {
		return portfolioId;
	}

	public void setPortfolioId(String portfolioId) {
		this.portfolioId = portfolioId;
	}
}
