package com.linkwee.api.response.crm;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linkwee.core.jackson.MoneySerializer;

public class CfplannerDataResponse {

	/**
	 * 当月理财业绩
	 */
	@JsonSerialize(using=MoneySerializer.class)
	private BigDecimal monthInvestAmt;
	
	/**
	 * 当月收入
	 */
	@JsonSerialize(using=MoneySerializer.class)
	private BigDecimal monthIncome;
	
	/**
	 * 理财师人数
	 */
	private Integer teamMember;
	
	/**
	 * 客户人数
	 */
	private Integer customerMember;
	
	/**
	 * 职级信息
	 */
	private String grade;
	
	/**
	 * 查询日期
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date queryDate;

	public BigDecimal getMonthInvestAmt() {
		return monthInvestAmt;
	}

	public void setMonthInvestAmt(BigDecimal monthInvestAmt) {
		this.monthInvestAmt = monthInvestAmt;
	}

	public BigDecimal getMonthIncome() {
		return monthIncome;
	}

	public void setMonthIncome(BigDecimal monthIncome) {
		this.monthIncome = monthIncome;
	}

	public Integer getTeamMember() {
		return teamMember;
	}

	public void setTeamMember(Integer teamMember) {
		this.teamMember = teamMember;
	}

	public Integer getCustomerMember() {
		return customerMember;
	}

	public void setCustomerMember(Integer customerMember) {
		this.customerMember = customerMember;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public Date getQueryDate() {
		return queryDate;
	}

	public void setQueryDate(Date queryDate) {
		this.queryDate = queryDate;
	}
}
