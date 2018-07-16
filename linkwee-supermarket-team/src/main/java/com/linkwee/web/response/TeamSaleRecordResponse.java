package com.linkwee.web.response;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linkwee.core.jackson.MoneySerializer;
import com.linkwee.core.util.DateUtils;

public class TeamSaleRecordResponse {

	/**
	 * 姓名
	 */
	private String userName;
	/**
	 * 手机号
	 */
	private String mobile;
	/**
	 * 出单平台
	 */
	private String orgName;
	/**
	 * 出单金额
	 */
	@JsonSerialize(using=MoneySerializer.class)
	private BigDecimal saleAmount;
	/**
	 * 产品期限
	 */
	private int productDeadLineMinValue;
	/**
	 * 我的佣金
	 */
	@JsonSerialize(using=MoneySerializer.class)
	private BigDecimal myFeeAmount;
	/**
	 * 出单时间
	 */
	private String saleTime;
	/**
	 * 层级
	 */
	private int teamLevel;
	/**
	 * 是否首投
	 */
	private int isFirstInvest;
	/**
	 * 用户ID
	 */
	private String userId;
	/**
	 * 最近复投时间
	 */
	private String lastReinvestTime;
	
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
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public BigDecimal getSaleAmount() {
		return saleAmount;
	}
	public void setSaleAmount(BigDecimal saleAmount) {
		this.saleAmount = saleAmount;
	}
	public int getProductDeadLineMinValue() {
		return productDeadLineMinValue;
	}
	public void setProductDeadLineMinValue(int productDeadLineMinValue) {
		this.productDeadLineMinValue = productDeadLineMinValue;
	}
	public BigDecimal getMyFeeAmount() {
		return myFeeAmount;
	}
	public void setMyFeeAmount(BigDecimal myFeeAmount) {
		this.myFeeAmount = myFeeAmount;
	}
	public String getSaleTime() {
		return saleTime;
	}
	public void setSaleTime(String saleTime) {
		this.saleTime = saleTime;
	}
	public int getTeamLevel() {
		return teamLevel;
	}
	public void setTeamLevel(int teamLevel) {
		this.teamLevel = teamLevel;
	}
	public int getIsFirstInvest() {
		return isFirstInvest;
	}
	public void setIsFirstInvest(int isFirstInvest) {
		this.isFirstInvest = isFirstInvest;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getLastReinvestTime() {
		int saleDays = DateUtils.countDays(saleTime);
		int times = saleDays / productDeadLineMinValue;
		Date lastReinvestDate = DateUtils.addDay(DateUtils.parse(saleTime, DateUtils.FORMAT_LONG), times * productDeadLineMinValue);
		return DateUtils.format(lastReinvestDate, DateUtils.FORMAT_LONG);
	}
	public void setLastReinvestTime(String lastReinvestTime) {
		this.lastReinvestTime = lastReinvestTime;
	}
	
}
