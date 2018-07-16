package com.linkwee.api.response.crm;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linkwee.core.jackson.MoneySerializer;

public class RepamentCalendarResponse {

	/**
	 * 回款时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	private Date endTime;
	
	/**
	 * 回款时间 字符串
	 */
	private String endTimeStr;
	
	/**
	 * 	头像
	 */
	private String headImage;
	
	/**
	 * 回款本金
	 */
	@JsonSerialize(using = MoneySerializer.class)
	private BigDecimal investAmt;
	
	/**
	 * 投资记录id
	 */
	private String  investId;
	
	/**
	 * 排序
	 */
	private Integer orderType;
	
	/**
	 * 平台名称
	 */
	private String platformName;
	
	/**
	 * 预期收益
	 */
	@JsonSerialize(using = MoneySerializer.class)
	private BigDecimal profit;
	
	/**
	 * 回款客户类型  	0- 客户 1-直推
	 */
	private Integer repaymentUserType;
	
	/**
	 * 用户名称
	 */
	private String userName;

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getEndTimeStr() {
		return endTimeStr;
	}

	public void setEndTimeStr(String endTimeStr) {
		this.endTimeStr = endTimeStr;
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

	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	public String getPlatformName() {
		return platformName;
	}

	public void setPlatformName(String platformName) {
		this.platformName = platformName;
	}

	public BigDecimal getProfit() {
		return profit;
	}

	public void setProfit(BigDecimal profit) {
		this.profit = profit;
	}

	public Integer getRepaymentUserType() {
		return repaymentUserType;
	}

	public void setRepaymentUserType(Integer repaymentUserType) {
		this.repaymentUserType = repaymentUserType;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
