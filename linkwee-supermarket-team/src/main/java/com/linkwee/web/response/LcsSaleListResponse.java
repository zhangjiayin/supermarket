package com.linkwee.web.response;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linkwee.core.jackson.MoneySerializer;

public class LcsSaleListResponse {

	/**
	 * 出单平台
	 */
	private String orgName;
	/**
	 * 出单金额
	 */
	@JsonSerialize(using=MoneySerializer.class)
	private BigDecimal saleAmount;
	/**
	 * 产品期限
	 */
	private int productDeadLineMinValue;
	/**
	 * 我的佣金
	 */
	@JsonSerialize(using=MoneySerializer.class)
	private BigDecimal myFeeAmount;
	/**
	 * 出单时间
	 */
	private String saleTime;
	/**
	 * 预期到期时间
	 */
	private String saleEndTime;
	/**
	 * 产品名称
	 */
	private String productName;
	/**
	 * 投资状态
	 */
	private int investStatus;
	
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public BigDecimal getSaleAmount() {
		return saleAmount;
	}
	public void setSaleAmount(BigDecimal saleAmount) {
		this.saleAmount = saleAmount;
	}
	public int getProductDeadLineMinValue() {
		return productDeadLineMinValue;
	}
	public void setProductDeadLineMinValue(int productDeadLineMinValue) {
		this.productDeadLineMinValue = productDeadLineMinValue;
	}
	public BigDecimal getMyFeeAmount() {
		return myFeeAmount;
	}
	public void setMyFeeAmount(BigDecimal myFeeAmount) {
		this.myFeeAmount = myFeeAmount;
	}
	public String getSaleTime() {
		return saleTime;
	}
	public void setSaleTime(String saleTime) {
		this.saleTime = saleTime;
	}
	public String getSaleEndTime() {
		return saleEndTime;
	}
	public void setSaleEndTime(String saleEndTime) {
		this.saleEndTime = saleEndTime;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getInvestStatus() {
		return investStatus;
	}
	public void setInvestStatus(int investStatus) {
		this.investStatus = investStatus;
	}
}
