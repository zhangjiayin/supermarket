package com.linkwee.web.model.crm;

import java.util.List;

import com.linkwee.core.base.BaseEntity;

public class LcsDetailSalesInfoResp extends BaseEntity {

	private static final long serialVersionUID = 1840107652864359699L;

	/**
	 * ID
	 */
	private List<LcsHongbaoListResponse> hongbaoList;
	/**
	 * 理财师销售额
	 */
	private Double totalSaleAmount;
	/**
	 * 累计销售笔数
	 */
	private int totalSaleCount;
	/**
	 * 客户在投总金额
	 */
	private Double currInvestAmount;
	/**
	 * 销售佣金
	 */
	private Double fee;

	/**
	 * 推荐奖励
	 */
	private Double allowance;
	/**
	 * 活动奖励
	 */
	private Double activityReward;
	/**
	 * leader奖励
	 */
	private Double leaderReward;

	/**
	 * 累计收益
	 */
	private Double totalProfit;

	public List<LcsHongbaoListResponse> getHongbaoList() {
		return hongbaoList;
	}

	public void setHongbaoList(List<LcsHongbaoListResponse> hongbaoList) {
		this.hongbaoList = hongbaoList;
	}

	public Double getTotalSaleAmount() {
		return totalSaleAmount;
	}

	public void setTotalSaleAmount(Double totalSaleAmount) {
		this.totalSaleAmount = totalSaleAmount;
	}

	public int getTotalSaleCount() {
		return totalSaleCount;
	}

	public void setTotalSaleCount(int totalSaleCount) {
		this.totalSaleCount = totalSaleCount;
	}

	public Double getCurrInvestAmount() {
		return currInvestAmount;
	}

	public void setCurrInvestAmount(Double currInvestAmount) {
		this.currInvestAmount = currInvestAmount;
	}

	public Double getFee() {
		return fee;
	}

	public void setFee(Double fee) {
		this.fee = fee;
	}

	public Double getAllowance() {
		return allowance;
	}

	public void setAllowance(Double allowance) {
		this.allowance = allowance;
	}

	public Double getActivityReward() {
		return activityReward;
	}

	public void setActivityReward(Double activityReward) {
		this.activityReward = activityReward;
	}

	public Double getLeaderReward() {
		return leaderReward;
	}

	public void setLeaderReward(Double leaderReward) {
		this.leaderReward = leaderReward;
	}

	public Double getTotalProfit() {
		return totalProfit;
	}

	public void setTotalProfit(Double totalProfit) {
		this.totalProfit = totalProfit;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
}
