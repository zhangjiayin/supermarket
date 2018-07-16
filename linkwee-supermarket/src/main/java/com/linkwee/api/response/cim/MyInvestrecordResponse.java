package com.linkwee.api.response.cim;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linkwee.core.jackson.MoneySerializer;

public class MyInvestrecordResponse implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 平台logo
	 */
	private String platformlistIco;
	/**
	 * 产品名称
	 */
	private String productName;
	/**
	 * 是否可赎回  0-不可赎回  1-可赎回
	 */
	private Integer canRedemption;
	/**
	 * 购买时间
	 */
	private String startTime;
    /**
     *购买本金
     */
	@JsonSerialize(using = MoneySerializer.class)
	private BigDecimal investAmt;
    /**
     *收益
     */
	@JsonSerialize(using = MoneySerializer.class)
	private BigDecimal profit;
	/**
	 * 回款时间
	 */
	private String endTime;
	/**
	 * 投资记录id
	 */
	private String investId;
	/**
	 * 最后一次读取时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	private Date lastReaddate;
	/**
	 * 是否已读   true已读，false未读
	 */
	private boolean readFlag;
	/**
	 * 业务日期
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")	
	private Date bizTime;
	
	public String getPlatformlistIco() {
		return platformlistIco;
	}
	public void setPlatformlistIco(String platformlistIco) {
		this.platformlistIco = platformlistIco;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Integer getCanRedemption() {
		return canRedemption;
	}
	public void setCanRedemption(Integer canRedemption) {
		this.canRedemption = canRedemption;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public BigDecimal getInvestAmt() {
		return investAmt;
	}
	public void setInvestAmt(BigDecimal investAmt) {
		this.investAmt = investAmt;
	}
	public BigDecimal getProfit() {
		return profit;
	}
	public void setProfit(BigDecimal profit) {
		this.profit = profit;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getInvestId() {
		return investId;
	}
	public void setInvestId(String investId) {
		this.investId = investId;
	}
	public Date getLastReaddate() {
		return lastReaddate;
	}
	public void setLastReaddate(Date lastReaddate) {
		this.lastReaddate = lastReaddate;
	}
	public Date getBizTime() {
		return bizTime;
	}
	public void setBizTime(Date bizTime) {
		this.bizTime = bizTime;
	}
	public boolean isReadFlag() {
		readFlag = lastReaddate==null?true:(lastReaddate.compareTo(bizTime)>-1);
		return readFlag;
	}
	public void setReadFlag(boolean readFlag) {
		this.readFlag = readFlag;
	}
}
