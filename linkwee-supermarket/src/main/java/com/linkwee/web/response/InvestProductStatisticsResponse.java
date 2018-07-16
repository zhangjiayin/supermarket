package com.linkwee.web.response;

import java.math.BigDecimal;

import com.linkwee.core.base.BaseEntity;

public class InvestProductStatisticsResponse extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7786209296800430213L;
	
	/**
	 * 产品投资额度
	 */
	private BigDecimal investingAmt;
	/**
	 * 产品利率
	 */
	private BigDecimal productRate;
	/**
	 * 产品期限
	 */
	private int productDeadLine;
	/**
	 * 产品ID
	 */
	private String productId;
	/**
	 * 年化金额
	 */
	private BigDecimal yearAmount;
	/**
	 * 红包收益
	 */
	private BigDecimal redpacketProfit;
	/**
	 * 平台奖励
	 */
	private BigDecimal virtualPaltformBouns;
	/**
	 * 佣金收益
	 */
	private BigDecimal totalFeeAmount;
	
	public BigDecimal getInvestingAmt() {
		return investingAmt;
	}
	public void setInvestingAmt(BigDecimal investingAmt) {
		this.investingAmt = investingAmt;
	}
	public BigDecimal getProductRate() {
		return productRate;
	}
	public void setProductRate(BigDecimal productRate) {
		this.productRate = productRate;
	}
	public int getProductDeadLine() {
		return productDeadLine;
	}
	public void setProductDeadLine(int productDeadLine) {
		this.productDeadLine = productDeadLine;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public BigDecimal getYearAmount() {
		return yearAmount;
	}
	public void setYearAmount(BigDecimal yearAmount) {
		this.yearAmount = yearAmount;
	}
	public BigDecimal getRedpacketProfit() {
		return redpacketProfit;
	}
	public void setRedpacketProfit(BigDecimal redpacketProfit) {
		this.redpacketProfit = redpacketProfit;
	}
	public BigDecimal getVirtualPaltformBouns() {
		return virtualPaltformBouns;
	}
	public void setVirtualPaltformBouns(BigDecimal virtualPaltformBouns) {
		this.virtualPaltformBouns = virtualPaltformBouns;
	}
	public BigDecimal getTotalFeeAmount() {
		return totalFeeAmount;
	}
	public void setTotalFeeAmount(BigDecimal totalFeeAmount) {
		this.totalFeeAmount = totalFeeAmount;
	}

}
