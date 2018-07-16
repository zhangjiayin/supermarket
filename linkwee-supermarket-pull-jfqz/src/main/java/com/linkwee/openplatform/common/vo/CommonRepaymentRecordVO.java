package com.linkwee.openplatform.common.vo;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonFormat;

public class CommonRepaymentRecordVO{

   /**
    *回款编号
    */
	private String repaymentId;
	
   /**
    *用户编号
    */
	private String userId;
	
   /**
    *投资编号
    */
	private String investId;
	
   /**
    *第三方机构产品编码
    */
	private String productId;
	
   /**
    *产品回款日期
    */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private String repaymentTime;
	
   /**
    *回款金额
    */
	private BigDecimal repaymentAmount;
	
   /**
    *精准收益
    */
	private BigDecimal profit;
	
   /**
    *回款状态(2=回款中|3=回款成功|4=提前赎回部分本金)
    */
	private Integer status;

	public String getRepaymentId() {
		return repaymentId;
	}
	
	public void setRepaymentId(String repaymentId) {
		this.repaymentId = repaymentId;
	}
	
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getInvestId() {
		return investId;
	}
	
	public void setInvestId(String investId) {
		this.investId = investId;
	}
	
	public String getProductId() {
		return productId;
	}
	
	public void setProductId(String productId) {
		this.productId = productId;
	}
	
	public String getRepaymentTime() {
		return repaymentTime;
	}
	
	public void setRepaymentTime(String repaymentTime) {
		this.repaymentTime = repaymentTime;
	}
	
	public BigDecimal getRepaymentAmount() {
		return repaymentAmount;
	}
	
	public void setRepaymentAmount(BigDecimal repaymentAmount) {
		this.repaymentAmount = repaymentAmount;
	}
	
	public BigDecimal getProfit() {
		return profit;
	}
	
	public void setProfit(BigDecimal profit) {
		this.profit = profit;
	}
	
	public Integer getStatus() {
		return status;
	}
	
	public void setStatus(Integer status) {
		this.status = status;
	}
}
