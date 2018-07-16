package com.linkwee.xoss.funds.sdk.ifast.model;

public class Investorholdings {

	/**
	 * 	持有组合下的资产详情
	 */
	private FundHoldings fundHoldings;
	/**
	 * 持有组合下的在途资产总值
	 */
	private double intransitAssetsTotal;
	/**
	 * 如该持有资产是通过购买组合而来，则返回关联的推荐组合原型代码，否则为空值
	 */
	private String modelPortfolioCode;
	/**
	 * 组合编码
	 */
	private Integer portfolioId;
	/**
	 * 组合名称
	 */
	private String portfolioName;
	/**
	 * 是否可以自由控制 Y=是，N=否
	 */
	private String rebalanceEnable;
	
	public FundHoldings getFundHoldings() {
		return fundHoldings;
	}
	public void setFundHoldings(FundHoldings fundHoldings) {
		this.fundHoldings = fundHoldings;
	}
	public double getIntransitAssetsTotal() {
		return intransitAssetsTotal;
	}
	public void setIntransitAssetsTotal(double intransitAssetsTotal) {
		this.intransitAssetsTotal = intransitAssetsTotal;
	}
	public String getModelPortfolioCode() {
		return modelPortfolioCode;
	}
	public void setModelPortfolioCode(String modelPortfolioCode) {
		this.modelPortfolioCode = modelPortfolioCode;
	}
	public Integer getPortfolioId() {
		return portfolioId;
	}
	public void setPortfolioId(Integer portfolioId) {
		this.portfolioId = portfolioId;
	}
	public String getPortfolioName() {
		return portfolioName;
	}
	public void setPortfolioName(String portfolioName) {
		this.portfolioName = portfolioName;
	}
	public String getRebalanceEnable() {
		return rebalanceEnable;
	}
	public void setRebalanceEnable(String rebalanceEnable) {
		this.rebalanceEnable = rebalanceEnable;
	}
}
