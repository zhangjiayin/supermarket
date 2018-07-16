package com.linkwee.openplatform.xyb.vo;

import java.io.Serializable;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class XYBInvestRecordVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4752087473975075999L;
	
	private String id;
	private String bid;
	private String burl;
	private String userId;
	private String username;
	private Float amount;
	private Float actualAmount;
	private Float income;
	private String investAt;
	private String investEndTime;
	private Integer status;
	private String txId;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBid() {
		return bid;
	}
	public void setBid(String bid) {
		this.bid = bid;
	}
	public String getBurl() {
		return burl;
	}
	public void setBurl(String burl) {
		this.burl = burl;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Float getAmount() {
		return amount;
	}
	public void setAmount(Float amount) {
		this.amount = amount;
	}
	public Float getActualAmount() {
		return actualAmount;
	}
	public void setActualAmount(Float actualAmount) {
		this.actualAmount = actualAmount;
	}
	public Float getIncome() {
		return income;
	}
	public void setIncome(Float income) {
		this.income = income;
	}
	
	public String getInvestAt() {
		return investAt;
	}
	public void setInvestAt(String investAt) {
		this.investAt = investAt;
	}
	public String getInvestEndTime() {
		return investEndTime;
	}
	public void setInvestEndTime(String investEndTime) {
		this.investEndTime = investEndTime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getTxId() {
		return txId;
	}
	public void setTxId(String txId) {
		this.txId = txId;
	}
	
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
	
}
