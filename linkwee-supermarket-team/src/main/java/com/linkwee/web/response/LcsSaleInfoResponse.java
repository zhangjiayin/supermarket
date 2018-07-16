package com.linkwee.web.response;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linkwee.core.jackson.MoneySerializer;

public class LcsSaleInfoResponse {

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
	 * 直推成员
	 */
	private int directNumber;
	/**
	 * 三级团队
	 */
	private int profitNumber;
	/**
	 * 所有下级
	 */
	private int teamNumber;
	
	public BigDecimal getSaleAmount() {
		return saleAmount;
	}
	public void setSaleAmount(BigDecimal saleAmount) {
		this.saleAmount = saleAmount;
	}
	public BigDecimal getSaleYearAmount() {
		return saleYearAmount;
	}
	public void setSaleYearAmount(BigDecimal saleYearAmount) {
		this.saleYearAmount = saleYearAmount;
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
	public int getProfitNumber() {
		return profitNumber;
	}
	public void setProfitNumber(int profitNumber) {
		this.profitNumber = profitNumber;
	}
	public int getTeamNumber() {
		return teamNumber;
	}
	public void setTeamNumber(int teamNumber) {
		this.teamNumber = teamNumber;
	}
	public int getDirectNumber() {
		return directNumber;
	}
	public void setDirectNumber(int directNumber) {
		this.directNumber = directNumber;
	}
}
