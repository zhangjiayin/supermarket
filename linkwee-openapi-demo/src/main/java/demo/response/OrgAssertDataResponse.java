package demo.response;

import java.math.BigDecimal;

public class OrgAssertDataResponse {

	/**
	 * 可用余额
	 */
	private BigDecimal totalAmount;
	/**
	 * 在途金额
	 */
	private BigDecimal inInvestmentAmount;
	/**
	 * 资产总额
	 */
	private BigDecimal totalAssets;
	/**
	 * 累计收益
	 */
	private BigDecimal accumulatedEarnings;
	/**
	 * 待收本息
	 */
	private BigDecimal onPrincipalAndinterest;
	
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
	public BigDecimal getInInvestmentAmount() {
		return inInvestmentAmount;
	}
	public void setInInvestmentAmount(BigDecimal inInvestmentAmount) {
		this.inInvestmentAmount = inInvestmentAmount;
	}
	public BigDecimal getTotalAssets() {
		return totalAssets;
	}
	public void setTotalAssets(BigDecimal totalAssets) {
		this.totalAssets = totalAssets;
	}
	public BigDecimal getAccumulatedEarnings() {
		return accumulatedEarnings;
	}
	public void setAccumulatedEarnings(BigDecimal accumulatedEarnings) {
		this.accumulatedEarnings = accumulatedEarnings;
	}
	public BigDecimal getOnPrincipalAndinterest() {
		return onPrincipalAndinterest;
	}
	public void setOnPrincipalAndinterest(BigDecimal onPrincipalAndinterest) {
		this.onPrincipalAndinterest = onPrincipalAndinterest;
	}
}
