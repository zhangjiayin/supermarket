package com.linkwee.openplatform.common.vo;

import java.math.BigDecimal;

public class CommonInvestRecordVO{
	
    /**
     *投资记录id(系统产生的唯一标识)
     */
	private String investId;
	
    /**
     *交易编号
     */
	private String txId;
	
    /**
     *用户id
     */
	private String userId;
	
    /**
     *第三方机构产品编码
     */
	private String productId;
	
    /**
     *购买本金
     */
	private BigDecimal investAmount;
	
    /**
     *收益
     */
	private BigDecimal profit;
	
    /**
     *产品购买日期
     */
	private String investStartTime;
	
    /**
     *产品回款日期
     */
	private String investEndTime;

	public String getInvestId() {
		return investId;
	}

	public void setInvestId(String investId) {
		this.investId = investId;
	}

	public String getTxId() {
		return txId;
	}

	public void setTxId(String txId) {
		this.txId = txId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public BigDecimal getProfit() {
		return profit;
	}

	public void setProfit(BigDecimal profit) {
		this.profit = profit;
	}

	public String getInvestStartTime() {
		return investStartTime;
	}

	public void setInvestStartTime(String investStartTime) {
		this.investStartTime = investStartTime;
	}

	public String getInvestEndTime() {
		return investEndTime;
	}

	public void setInvestEndTime(String investEndTime) {
		this.investEndTime = investEndTime;
	}

	public BigDecimal getInvestAmount() {
		return investAmount;
	}

	public void setInvestAmount(BigDecimal investAmount) {
		this.investAmount = investAmount;
	}
}
