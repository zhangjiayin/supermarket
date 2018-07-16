package com.linkwee.web.response;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linkwee.core.jackson.MoneySerializer;

public class LevelSaleResponse {
	
	/**
	 * 团队层级
	 */
	private Integer level;
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
	private BigDecimal firstInvestAmount;
	/**
	 * 平台复投金额
	 */
	@JsonSerialize(using=MoneySerializer.class)
	private BigDecimal repInvestAmount;
	/**
	 * 平台首投年化
	 */
	@JsonSerialize(using=MoneySerializer.class)
	private BigDecimal firstInvestYearAmount;
	/**
	 * 平台复投年化
	 */
	@JsonSerialize(using=MoneySerializer.class)
	private BigDecimal repInvestYearAmount;
	/**
	 * 在投金额
	 */
	@JsonSerialize(using=MoneySerializer.class)
	private BigDecimal investingAmount;
	
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public BigDecimal getSaleYearAmount() {
		return saleYearAmount;
	}
	public void setSaleYearAmount(BigDecimal saleYearAmount) {
		this.saleYearAmount = saleYearAmount;
	}
	public BigDecimal getFirstInvestAmount() {
		return firstInvestAmount;
	}
	public void setFirstInvestAmount(BigDecimal firstInvestAmount) {
		this.firstInvestAmount = firstInvestAmount;
	}
	public BigDecimal getRepInvestAmount() {
		return repInvestAmount;
	}
	public void setRepInvestAmount(BigDecimal repInvestAmount) {
		this.repInvestAmount = repInvestAmount;
	}
	public BigDecimal getFirstInvestYearAmount() {
		return firstInvestYearAmount;
	}
	public void setFirstInvestYearAmount(BigDecimal firstInvestYearAmount) {
		this.firstInvestYearAmount = firstInvestYearAmount;
	}
	public BigDecimal getRepInvestYearAmount() {
		return repInvestYearAmount;
	}
	public void setRepInvestYearAmount(BigDecimal repInvestYearAmount) {
		this.repInvestYearAmount = repInvestYearAmount;
	}
	public BigDecimal getInvestingAmount() {
		return investingAmount;
	}
	public void setInvestingAmount(BigDecimal investingAmount) {
		this.investingAmount = investingAmount;
	}
	public BigDecimal getSaleAmount() {
		return saleAmount;
	}
	public void setSaleAmount(BigDecimal saleAmount) {
		this.saleAmount = saleAmount;
	}
}
