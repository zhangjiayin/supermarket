package com.linkwee.web.response.insurance.qixin;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linkwee.core.jackson.MoneySerializer;

public class InsuranceNotifyAuditResponse {
	
	/**
	 * ID
	 */
	private Integer id;
	/**
	 * 出单人
	 */
	private String userName;
	/**
	 * 手机号
	 */
	private String mobile;
	/**
	 * 产品名称
	 */
	private String productName;
	/**
	 * 投保单号
	 */
	private String insureNum;
	/**
	 * 支付金额
	 */
	@JsonSerialize(using=MoneySerializer.class)
	private BigDecimal price;
	/**
	 * 佣金率
	 */
	@JsonSerialize(using=MoneySerializer.class)
	private BigDecimal feeRatio;
	
	/**
	 * 佣金
	 */
	@JsonSerialize(using=MoneySerializer.class)
	private BigDecimal fee;
	/**
	 * 起保日期
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	private Date startDate;
	/**
	 * 犹豫期
	 */
	private Integer hesitateDate;
	/**
	 * 系统结算
	 */
	private Integer clearingStatus;
	/**
	 * 审核状态
	 */
	private Integer auditStatus;
	/**
	 * 是否显示审核按钮 0-显示 1-不显示 
	 */
	private Integer ifShowAuditButton;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
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
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getInsureNum() {
		return insureNum;
	}
	public void setInsureNum(String insureNum) {
		this.insureNum = insureNum;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public BigDecimal getFeeRatio() {
		return feeRatio;
	}
	public void setFeeRatio(BigDecimal feeRatio) {
		this.feeRatio = feeRatio;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Integer getHesitateDate() {
		return hesitateDate;
	}
	public void setHesitateDate(Integer hesitateDate) {
		this.hesitateDate = hesitateDate;
	}
	public Integer getClearingStatus() {
		return clearingStatus;
	}
	public void setClearingStatus(Integer clearingStatus) {
		this.clearingStatus = clearingStatus;
	}
	public Integer getAuditStatus() {
		return auditStatus;
	}
	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
	}
	public BigDecimal getFee() {
		fee = price.multiply(feeRatio).divide(new BigDecimal(100));
		return fee;
	}
	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}
	public Integer getIfShowAuditButton() {
		return ifShowAuditButton;
	}
	public void setIfShowAuditButton(Integer ifShowAuditButton) {
		this.ifShowAuditButton = ifShowAuditButton;
	}
}
