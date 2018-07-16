package com.linkwee.xoss.funds.sdk.ifast.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linkwee.core.jackson.DoubleSerializer;

public class HoldingsStatistic {

	/**
	*总资产现值(元)
	*/
	@JsonSerialize(using = DoubleSerializer.class)
	private double	currentAmount;

	/**
	*在途资产
	*/
	@JsonSerialize(using = DoubleSerializer.class)
	private double	intransitAssets;

	/**
	*总资产成本(元)
	*/
	@JsonSerialize(using = DoubleSerializer.class)
	private double	investmentAmount;

	/**
	*盈利/亏损(元)
	*/
	@JsonSerialize(using = DoubleSerializer.class)
	private double	profitLoss;

	/**
	*昨日收益。根据最上2个净值对比得到的（货币型使用万份收益计算得到）,如果返回null，表示还在计算中
	*/
	@JsonSerialize(using = DoubleSerializer.class)
	private double	profitLossDaily;

	/**
	*盈利/亏损(%)
	*/
	@JsonSerialize(using = DoubleSerializer.class)
	private double	profitLossPercent;

	public double getCurrentAmount() {
		return currentAmount;
	}

	public void setCurrentAmount(double currentAmount) {
		this.currentAmount = currentAmount;
	}

	public double getIntransitAssets() {
		return intransitAssets;
	}

	public void setIntransitAssets(double intransitAssets) {
		this.intransitAssets = intransitAssets;
	}

	public double getInvestmentAmount() {
		return investmentAmount;
	}

	public void setInvestmentAmount(double investmentAmount) {
		this.investmentAmount = investmentAmount;
	}

	public double getProfitLoss() {
		return profitLoss;
	}

	public void setProfitLoss(double profitLoss) {
		this.profitLoss = profitLoss;
	}

	public double getProfitLossDaily() {
		return profitLossDaily;
	}

	public void setProfitLossDaily(double profitLossDaily) {
		this.profitLossDaily = profitLossDaily;
	}

	public double getProfitLossPercent() {
		return profitLossPercent;
	}

	public void setProfitLossPercent(double profitLossPercent) {
		this.profitLossPercent = profitLossPercent;
	}
}
