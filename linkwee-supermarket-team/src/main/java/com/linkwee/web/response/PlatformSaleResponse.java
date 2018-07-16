package com.linkwee.web.response;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linkwee.core.jackson.MoneySerializer;

public class PlatformSaleResponse {

	/**
	 * 出单金额
	 */
	@JsonSerialize(using=MoneySerializer.class)
	private BigDecimal saleAmount;
	/**
	 * 出单年化
	 */
	@JsonSerialize(using=MoneySerializer.class)
	private BigDecimal saleYearAmount;
	/**
	 * 平台首投金额
	 */
	@JsonSerialize(using=MoneySerializer.class)
	private BigDecimal platformFirstInvestAmount;
	/**
	 * 平台复投金额
	 */
	@JsonSerialize(using=MoneySerializer.class)
	private BigDecimal platformRepInvestAmount;
	/**
	 * 平台首投年化
	 */
	@JsonSerialize(using=MoneySerializer.class)
	private BigDecimal platformFirstInvestYearAmount;
	/**
	 * 平台复投年化
	 */
	@JsonSerialize(using=MoneySerializer.class)
	private BigDecimal platformRepInvestYearAmount;
	/**
	 * 在投金额
	 */
	@JsonSerialize(using=MoneySerializer.class)
	private BigDecimal investingAmount;
	/**
	 * 出单人数
	 */
	private int saleNumber;
	/**
	 * 在投人数
	 */
	private int investingNumber;
	/**
	 * 平台名称
	 */
	private String orgName;
	
	public BigDecimal getSaleYearAmount() {
		return saleYearAmount;
	}
	public void setSaleYearAmount(BigDecimal saleYearAmount) {
		this.saleYearAmount = saleYearAmount;
	}
	public BigDecimal getPlatformFirstInvestAmount() {
		return platformFirstInvestAmount;
	}
	public void setPlatformFirstInvestAmount(BigDecimal platformFirstInvestAmount) {
		this.platformFirstInvestAmount = platformFirstInvestAmount;
	}
	public BigDecimal getPlatformRepInvestAmount() {
		return platformRepInvestAmount;
	}
	public void setPlatformRepInvestAmount(BigDecimal platformRepInvestAmount) {
		this.platformRepInvestAmount = platformRepInvestAmount;
	}
	public BigDecimal getPlatformFirstInvestYearAmount() {
		return platformFirstInvestYearAmount;
	}
	public void setPlatformFirstInvestYearAmount(
			BigDecimal platformFirstInvestYearAmount) {
		this.platformFirstInvestYearAmount = platformFirstInvestYearAmount;
	}
	public BigDecimal getPlatformRepInvestYearAmount() {
		return platformRepInvestYearAmount;
	}
	public void setPlatformRepInvestYearAmount(
			BigDecimal platformRepInvestYearAmount) {
		this.platformRepInvestYearAmount = platformRepInvestYearAmount;
	}
	public BigDecimal getInvestingAmount() {
		return investingAmount;
	}
	public void setInvestingAmount(BigDecimal investingAmount) {
		this.investingAmount = investingAmount;
	}
	public int getSaleNumber() {
		return saleNumber;
	}
	public void setSaleNumber(int saleNumber) {
		this.saleNumber = saleNumber;
	}
	public int getInvestingNumber() {
		return investingNumber;
	}
	public void setInvestingNumber(int investingNumber) {
		this.investingNumber = investingNumber;
	}
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
}
