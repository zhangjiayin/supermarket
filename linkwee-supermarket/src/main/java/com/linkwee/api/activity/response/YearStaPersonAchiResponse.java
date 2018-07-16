package com.linkwee.api.activity.response;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linkwee.core.jackson.DateSerializer;
import com.linkwee.core.jackson.MoneySerializer;
import com.linkwee.web.model.acc.MonthProfixTotalListResp;

public class YearStaPersonAchiResponse {

	/**
	 * 注册时间
	 */
	@JsonSerialize(using=DateSerializer.class)
	private String registerTime;
	/**
	 * 首投时间
	 */
	@JsonSerialize(using=DateSerializer.class)
	private String firstInvestTime;
	/**
	 * 首投金额
	 */
	@JsonSerialize(using=MoneySerializer.class)
	private BigDecimal firstInvestAmount;
	/**
	 * 首投平台
	 */
	private String firstInvestOrgName;
	/**
	 * 首个客户时间
	 */
	@JsonSerialize(using=DateSerializer.class)
	private String hadInvestorTime;
	/**
	 * 团队创建时间
	 */
	@JsonSerialize(using=DateSerializer.class)
	private String hadTeamTime;
	/**
	 * 佣金最多的月份
	 */
	private String maxFeeMonth;
	/**
	 * 佣金最多月份的金额
	 */
	@JsonSerialize(using=MoneySerializer.class)
	private BigDecimal maxFeeMonthAmount;
	/**
	 * 个人成绩描述
	 */
	private String personAchiDescription;
	/**
	 * 出单平台数量
	 */
	private int saleOrgNumber = 0;
	/**
	 * 网贷出单金额
	 */
	@JsonSerialize(using=MoneySerializer.class)
	private BigDecimal saleAmount = new BigDecimal("0.00");
	/**
	 * 出单保险数量
	 */
	private int saleInsuNumber;
	/**
	 * 出单基金金额
	 */
	@JsonSerialize(using=MoneySerializer.class)
	private BigDecimal saleFundAmount;
	/**
	 * 每月收入列表
	 */
	List<MonthProfixTotalListResp> monthProfitList;
	/**
	 * 总收入
	 */
	@JsonSerialize(using=MoneySerializer.class)
	private BigDecimal totalProfit;
	
	public String getRegisterTime() {
		return registerTime;
	}
	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}
	public String getFirstInvestTime() {
		return firstInvestTime;
	}
	public void setFirstInvestTime(String firstInvestTime) {
		this.firstInvestTime = firstInvestTime;
	}
	public String getFirstInvestOrgName() {
		return firstInvestOrgName;
	}
	public void setFirstInvestOrgName(String firstInvestOrgName) {
		this.firstInvestOrgName = firstInvestOrgName;
	}
	public String getHadInvestorTime() {
		return registerTime;
	}
	public void setHadInvestorTime(String hadInvestorTime) {
		this.hadInvestorTime = hadInvestorTime;
	}
	public String getHadTeamTime() {
		return hadTeamTime;
	}
	public void setHadTeamTime(String hadTeamTime) {
		this.hadTeamTime = hadTeamTime;
	}
	public String getMaxFeeMonth() {
		return maxFeeMonth;
	}
	public void setMaxFeeMonth(String maxFeeMonth) {
		this.maxFeeMonth = maxFeeMonth;
	}
	public String getPersonAchiDescription() {
		String[] descriptions ={"超神的动作那么666，搬好小板凳等你来给大家分享经验","大神，珠穆朗玛峰在前方等你来超越！","难道你就是传说中猎财大师最佳理财师！？","猎财大师赚钱达人！快收我的膝盖？"};
		int rand = (int) Math.round(Math.random() * 3);
		return descriptions[rand];
	}
	public void setPersonAchiDescription(String personAchiDescription) {
		this.personAchiDescription = personAchiDescription;
	}
	public int getSaleOrgNumber() {
		return saleOrgNumber;
	}
	public void setSaleOrgNumber(int saleOrgNumber) {
		this.saleOrgNumber = saleOrgNumber;
	}
	public int getSaleInsuNumber() {
		return saleInsuNumber;
	}
	public void setSaleInsuNumber(int saleInsuNumber) {
		this.saleInsuNumber = saleInsuNumber;
	}
	public List<MonthProfixTotalListResp> getMonthProfitList() {
		return monthProfitList;
	}
	public void setMonthProfitList(List<MonthProfixTotalListResp> monthProfitList) {
		this.monthProfitList = monthProfitList;
	}
	public BigDecimal getFirstInvestAmount() {
		return firstInvestAmount;
	}
	public void setFirstInvestAmount(BigDecimal firstInvestAmount) {
		this.firstInvestAmount = firstInvestAmount;
	}
	public BigDecimal getMaxFeeMonthAmount() {
		return maxFeeMonthAmount;
	}
	public void setMaxFeeMonthAmount(BigDecimal maxFeeMonthAmount) {
		this.maxFeeMonthAmount = maxFeeMonthAmount;
	}
	public BigDecimal getSaleAmount() {
		return saleAmount;
	}
	public void setSaleAmount(BigDecimal saleAmount) {
		this.saleAmount = saleAmount;
	}
	public BigDecimal getSaleFundAmount() {
		return saleFundAmount;
	}
	public void setSaleFundAmount(BigDecimal saleFundAmount) {
		this.saleFundAmount = saleFundAmount;
	}
	public BigDecimal getTotalProfit() {
		return totalProfit;
	}
	public void setTotalProfit(BigDecimal totalProfit) {
		this.totalProfit = totalProfit;
	}

}
