package com.linkwee.xoss.funds.sdk.ifast.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linkwee.core.jackson.DoubleSerializer;
import com.linkwee.core.jackson.TimestampStringSerializer;

public class FundHolding {

	/**
	 * 基金可处理份额。单位为份，精准到0.01，如100000.00份
	 */
	@JsonSerialize(using = DoubleSerializer.class)
	private double availableUnit;

	/**
	 * 收费模式。0=前端收费，1=后端收费
	 */
	private String chargeMode;

	/**
	 * 资产现值。一般等于：总份额×最新净值+在途资产+未分配收益
	 */
	@JsonSerialize(using = DoubleSerializer.class)
	private double currentValue;

	/**
	 * 持有的基金目前为止收到的所有现金分红。单位为元，精准到0.01，如10.01元
	 */
	@JsonSerialize(using = DoubleSerializer.class)
	private double dividendCash;

	/**
	 * 分红方式。0=红利再投，1=现金分红
	 */
	private String dividendInstruction;

	/**
	 * 基金代码
	 */
	private String fundCode;

	/**
	 * 基金简称
	 */
	private String fundName;

	/**
	 * 在途资产
	 */
	@JsonSerialize(using = DoubleSerializer.class)
	private double intransitAssets;

	/**
	 * 资产成本。所有份额的总投资成本
	 */
	@JsonSerialize(using = DoubleSerializer.class)
	private double investmentAmount;

	/**
	 * 最新净值。精准到4位小数，如1.0000
	 */
	@JsonSerialize(using = DoubleSerializer.class)
	private double nav;

	/**
	 * 净值日期
	 */
	@JsonSerialize(using = TimestampStringSerializer.class)
	private String navDate;

	/**
	 * 昨日收益。根据最上2个净值对比得到的（货币型使用万份收益计算得到）,如果返回null，表示还在计算中
	 */
	@JsonSerialize(using = DoubleSerializer.class)
	private double previousProfitNLoss;

	/**
	 * 盈利/亏损。基金现值-基金成本
	 */
	@JsonSerialize(using = DoubleSerializer.class)
	private double profitNLoss;

	/**
	 * 基金持有总份额。单位为份，精准到0.01，如100000.00份
	 */
	@JsonSerialize(using = DoubleSerializer.class)
	private double totalUnit;

	/**
	 * 未分配收益。对于不是每日结转的货币型基金，未分配的收益（即未结转为份额的收益）
	 */
	@JsonSerialize(using = DoubleSerializer.class)
	private double undistributeMonetaryIncome;

	public double getAvailableUnit() {
		return availableUnit;
	}

	public void setAvailableUnit(double availableUnit) {
		this.availableUnit = availableUnit;
	}

	public String getChargeMode() {
		return chargeMode;
	}

	public void setChargeMode(String chargeMode) {
		this.chargeMode = chargeMode;
	}

	public double getCurrentValue() {
		return currentValue;
	}

	public void setCurrentValue(double currentValue) {
		this.currentValue = currentValue;
	}

	public double getDividendCash() {
		return dividendCash;
	}

	public void setDividendCash(double dividendCash) {
		this.dividendCash = dividendCash;
	}

	public String getDividendInstruction() {
		return dividendInstruction;
	}

	public void setDividendInstruction(String dividendInstruction) {
		this.dividendInstruction = dividendInstruction;
	}

	public String getFundCode() {
		return fundCode;
	}

	public void setFundCode(String fundCode) {
		this.fundCode = fundCode;
	}

	public String getFundName() {
		return fundName;
	}

	public void setFundName(String fundName) {
		this.fundName = fundName;
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

	public double getNav() {
		return nav;
	}

	public void setNav(double nav) {
		this.nav = nav;
	}

	public String getNavDate() {
		return navDate;
	}

	public void setNavDate(String navDate) {
		this.navDate = navDate;
	}

	public double getPreviousProfitNLoss() {
		return previousProfitNLoss;
	}

	public void setPreviousProfitNLoss(double previousProfitNLoss) {
		this.previousProfitNLoss = previousProfitNLoss;
	}

	public double getProfitNLoss() {
		return profitNLoss;
	}

	public void setProfitNLoss(double profitNLoss) {
		this.profitNLoss = profitNLoss;
	}

	public double getTotalUnit() {
		return totalUnit;
	}

	public void setTotalUnit(double totalUnit) {
		this.totalUnit = totalUnit;
	}

	public double getUndistributeMonetaryIncome() {
		return undistributeMonetaryIncome;
	}

	public void setUndistributeMonetaryIncome(double undistributeMonetaryIncome) {
		this.undistributeMonetaryIncome = undistributeMonetaryIncome;
	}

}
