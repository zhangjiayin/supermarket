package com.linkwee.api.activity.response;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linkwee.core.jackson.MoneySerializer;
import com.linkwee.web.response.InvestStatisticsResponse;

public class YearStaTeamAchiResponse {

	/**
	 * 团队人数
	 */
	private int teamNumber;
	/**
	 * 投资额
	 */
	@JsonSerialize(using=MoneySerializer.class)
	private BigDecimal investAmount;
	/**
	 * 保险单数
	 */
	private int insuranceNumber;
	/**
	 * 基金投资额
	 */
	@JsonSerialize(using=MoneySerializer.class)
	private BigDecimal fundAmount;
	/**
	 * 机构投资统计
	 */
	private List<InvestStatisticsResponse> investingStatisticList;
	/**
	 * 团队成绩描述
	 */
	private String teamAchiDescription;
	/**
	 * 赚取佣金最大的用户名称
	 */
	private String maxProfitUserName;
	/**
	 * 赚取佣金最大的用户头像
	 */
	private String maxProfitUserHeadImg;
	
	public int getTeamNumber() {
		return teamNumber;
	}
	public void setTeamNumber(int teamNumber) {
		this.teamNumber = teamNumber;
	}
	public int getInsuranceNumber() {
		return insuranceNumber;
	}
	public void setInsuranceNumber(int insuranceNumber) {
		this.insuranceNumber = insuranceNumber;
	}
	public List<InvestStatisticsResponse> getInvestingStatisticList() {
		return investingStatisticList;
	}
	public void setInvestingStatisticList(
			List<InvestStatisticsResponse> investingStatisticList) {
		this.investingStatisticList = investingStatisticList;
	}
	public BigDecimal getInvestAmount() {
		return investAmount;
	}
	public void setInvestAmount(BigDecimal investAmount) {
		this.investAmount = investAmount;
	}
	public BigDecimal getFundAmount() {
		return fundAmount;
	}
	public void setFundAmount(BigDecimal fundAmount) {
		this.fundAmount = fundAmount;
	}
	public String getTeamAchiDescription() {
		String[] descriptions ={"分散投资是指同时投资在不同的平台，目的就是降低投资风险，记住哟！","你真棒！已经完全get分散投资的理论！","鸡蛋不要放在一个篮子里，分散投资是理财师的黄金理论！"};
		int rand = (int) Math.round(Math.random() * 2);
		return descriptions[rand];
	}
	public void setTeamAchiDescription(String teamAchiDescription) {
		this.teamAchiDescription = teamAchiDescription;
	}
	public String getMaxProfitUserName() {
		return maxProfitUserName;
	}
	public void setMaxProfitUserName(String maxProfitUserName) {
		this.maxProfitUserName = maxProfitUserName;
	}
	public String getMaxProfitUserHeadImg() {
		return maxProfitUserHeadImg;
	}
	public void setMaxProfitUserHeadImg(String maxProfitUserHeadImg) {
		this.maxProfitUserHeadImg = maxProfitUserHeadImg;
	}

}
