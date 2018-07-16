package com.linkwee.web.response;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linkwee.core.jackson.MoneySerializer;

public class TotalStatisticResponse {

	/**
	 * 出单金额
	 */
	@JsonSerialize(using=MoneySerializer.class)
	private BigDecimal saleAmount;
	/**
	 * 猎财首投金额
	 */
	@JsonSerialize(using=MoneySerializer.class)
	private BigDecimal firstInvestAmount;
	/**
	 * 猎财复投金额
	 */
	@JsonSerialize(using=MoneySerializer.class)
	private BigDecimal repInvestAmount;
	/**
	 * 在投金额
	 */
	@JsonSerialize(using=MoneySerializer.class)
	private BigDecimal investingAmount;
	/**
	 * 我的佣金
	 */
	@JsonSerialize(using=MoneySerializer.class)
	private BigDecimal myFeeAmount;
	/**
	 * 出单年化
	 */
	@JsonSerialize(using=MoneySerializer.class)
	private BigDecimal saleYearAmount;
	/**
	 * 出单人数
	 */
	private int saleNumber;
	/**
	 * 猎财首投人数
	 */
	private int firstInvestNumber;
	/**
	 * 猎财复投人数
	 */
	private int repInvestNumber;
	/**
	 * 在投人数
	 */
	private int investingNumber;
	/**
	 * 团队注册人数
	 */
	private int teamNumber;
	/**
	 * 三级团队人数
	 */
	private int profitTeamNumber;
	
	public BigDecimal getSaleAmount() {
		return saleAmount;
	}
	public void setSaleAmount(BigDecimal saleAmount) {
		this.saleAmount = saleAmount;
	}
	public BigDecimal getFirstInvestAmount() {
		return firstInvestAmount;
	}
	public void setFirstInvestAmount(BigDecimal firstInvestAmount) {
		this.firstInvestAmount = firstInvestAmount;
	}
	public BigDecimal getInvestingAmount() {
		return investingAmount;
	}
	public void setInvestingAmount(BigDecimal investingAmount) {
		this.investingAmount = investingAmount;
	}
	public BigDecimal getMyFeeAmount() {
		return myFeeAmount;
	}
	public void setMyFeeAmount(BigDecimal myFeeAmount) {
		this.myFeeAmount = myFeeAmount;
	}
	public BigDecimal getSaleYearAmount() {
		return saleYearAmount;
	}
	public void setSaleYearAmount(BigDecimal saleYearAmount) {
		this.saleYearAmount = saleYearAmount;
	}
	public int getSaleNumber() {
		return saleNumber;
	}
	public void setSaleNumber(int saleNumber) {
		this.saleNumber = saleNumber;
	}
	public int getFirstInvestNumber() {
		return firstInvestNumber;
	}
	public void setFirstInvestNumber(int firstInvestNumber) {
		this.firstInvestNumber = firstInvestNumber;
	}
	public int getRepInvestNumber() {
		return repInvestNumber;
	}
	public void setRepInvestNumber(int repInvestNumber) {
		this.repInvestNumber = repInvestNumber;
	}
	public int getInvestingNumber() {
		return investingNumber;
	}
	public void setInvestingNumber(int investingNumber) {
		this.investingNumber = investingNumber;
	}
	public int getTeamNumber() {
		return teamNumber;
	}
	public void setTeamNumber(int teamNumber) {
		this.teamNumber = teamNumber;
	}
	public int getProfitTeamNumber() {
		return profitTeamNumber;
	}
	public void setProfitTeamNumber(int profitTeamNumber) {
		this.profitTeamNumber = profitTeamNumber;
	}
	public BigDecimal getRepInvestAmount() {
		return repInvestAmount;
	}
	public void setRepInvestAmount(BigDecimal repInvestAmount) {
		this.repInvestAmount = repInvestAmount;
	}
}
