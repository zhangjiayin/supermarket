package com.linkwee.api.activity.response;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linkwee.core.jackson.MoneySerializer;

public class NewYearHelpStatusResponse {
	
	/**
	 * 保险数量
	 */
	private int insureSum;
	/**
	 * 平台首投数量
	 */
	private int platfromFirstInvestSum;
	/**
	 * 累计投资额
	 */
	@JsonSerialize(using=MoneySerializer.class)
	private BigDecimal totalInvest = new BigDecimal(0);
	/**
	 * 获奖比率
	 */
	private int rewardRate;
	/**
	 * 收益
	 */
	@JsonSerialize(using=MoneySerializer.class)
	private BigDecimal profit;
	/**
	 * 我的累计年化业绩（自己和直推理财师）
	 */
	@JsonSerialize(using=MoneySerializer.class)
	private BigDecimal mySaleAmount;
	
	public int getInsureSum() {
		return insureSum;
	}
	public void setInsureSum(int insureSum) {
		this.insureSum = insureSum;
	}
	public int getPlatfromFirstInvestSum() {
		return platfromFirstInvestSum;
	}
	public void setPlatfromFirstInvestSum(int platfromFirstInvestSum) {
		this.platfromFirstInvestSum = platfromFirstInvestSum;
	}
	public BigDecimal getTotalInvest() {
		return totalInvest;
	}
	public void setTotalInvest(BigDecimal totalInvest) {
		this.totalInvest = totalInvest;
	}
	public int getRewardRate() {
		if(insureSum > 0){
			rewardRate += 6;
		}
		if(platfromFirstInvestSum > 0){
			rewardRate += 6;
		}
		return rewardRate;
	}
	public void setRewardRate(int rewardRate) {
		this.rewardRate = rewardRate;
	}
	public BigDecimal getProfit() {
		return totalInvest.multiply(new BigDecimal(rewardRate)).divide(new BigDecimal(100));
	}
	public void setProfit(BigDecimal profit) {
		this.profit = profit;
	}
	public BigDecimal getMySaleAmount() {
		return mySaleAmount;
	}
	public void setMySaleAmount(BigDecimal mySaleAmount) {
		this.mySaleAmount = mySaleAmount;
	}
}
