package com.linkwee.api.response.crm;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linkwee.core.jackson.MoneySerializer;

public class InvestCalendarResponse {

	/**
	 * 投资时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	private Date startTime;
	
	/**
	 * 投资时间 字符串
	 */
	private String startTimeStr;
	
	/**
	 * 理财师头像
	 */
	private String headImage;
	
	/**
	 * 投资金额
	 */
	@JsonSerialize(using = MoneySerializer.class)
	private BigDecimal investAmt;
	
	/**
	 * 投资记录id
	 */
	private String  investId;
	
	/**
	 * 用户类型  0- 客户 1-直推 2-二级 3-三级
	 */
	private Integer userType;
	
	/**
	 * 平台名称
	 */
	private String platformName;
	
	/**
	 * 我的佣金
	 */
	@JsonSerialize(using = MoneySerializer.class)
	private BigDecimal feeAmountSum;
	
	/**
	 * 理财师名称
	 */
	private String userName;
	
	/**
	 * 保险审核佣金计算状态 0-待计算 1-计算成功 2-计算失败
	 */
	private Integer clearingStatus;

	
	public Integer getClearingStatus() {
		return clearingStatus;
	}

	public void setClearingStatus(Integer clearingStatus) {
		this.clearingStatus = clearingStatus;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public String getStartTimeStr() {
		return startTimeStr;
	}

	public void setStartTimeStr(String startTimeStr) {
		this.startTimeStr = startTimeStr;
	}

	public String getHeadImage() {
		return headImage;
	}

	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}

	public BigDecimal getInvestAmt() {
		return investAmt;
	}

	public void setInvestAmt(BigDecimal investAmt) {
		this.investAmt = investAmt;
	}

	public String getInvestId() {
		return investId;
	}

	public void setInvestId(String investId) {
		this.investId = investId;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public String getPlatformName() {
		return platformName;
	}

	public void setPlatformName(String platformName) {
		this.platformName = platformName;
	}

	public BigDecimal getFeeAmountSum() {
		return feeAmountSum;
	}

	public void setFeeAmountSum(BigDecimal feeAmountSum) {
		this.feeAmountSum = feeAmountSum;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
