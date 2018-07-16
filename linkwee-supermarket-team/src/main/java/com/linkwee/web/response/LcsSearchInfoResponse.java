package com.linkwee.web.response;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linkwee.core.jackson.MoneySerializer;

public class LcsSearchInfoResponse {

	/**
	 * 姓名
	 */
	private String userName;
	/**
	 * 手机号
	 */
	private String mobile;
	/**
	 * 层级
	 */
	private int teamDepth;
	/**
	 * 本月出单金额
	 */
	@JsonSerialize(using=MoneySerializer.class)
	private BigDecimal monthSaleAmount;
	/**
	 * 累计出单金额
	 */
	@JsonSerialize(using=MoneySerializer.class)
	private BigDecimal totalSaleAmount;
	/**
	 * 在投金额
	 */
	@JsonSerialize(using=MoneySerializer.class)
	private BigDecimal investingAmount;
	/**
	 * 为我贡献佣金
	 */
	@JsonSerialize(using=MoneySerializer.class)
	private BigDecimal myFeeAmount;
	/**
	 * 所有下级
	 */
	private int teamNumber;
	/**
	 * 注册时间
	 */
	private String regTime;
	/**
	 * 最近登录时间
	 */
	private String lastLoginTime;
	/**
	 * 理财师Id
	 */
	private String userId;
	/**
	 * 关注状态
	 */
	private int fouseStatus;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public BigDecimal getMonthSaleAmount() {
		return monthSaleAmount;
	}
	public void setMonthSaleAmount(BigDecimal monthSaleAmount) {
		this.monthSaleAmount = monthSaleAmount;
	}
	public BigDecimal getTotalSaleAmount() {
		return totalSaleAmount;
	}
	public void setTotalSaleAmount(BigDecimal totalSaleAmount) {
		this.totalSaleAmount = totalSaleAmount;
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
	public int getTeamNumber() {
		return teamNumber;
	}
	public void setTeamNumber(int teamNumber) {
		this.teamNumber = teamNumber;
	}
	public String getRegTime() {
		return regTime;
	}
	public void setRegTime(String regTime) {
		this.regTime = regTime;
	}
	public String getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public int getTeamDepth() {
		return teamDepth;
	}
	public void setTeamDepth(int teamDepth) {
		this.teamDepth = teamDepth;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getFouseStatus() {
		return fouseStatus;
	}
	public void setFouseStatus(int fouseStatus) {
		this.fouseStatus = fouseStatus;
	}
}
