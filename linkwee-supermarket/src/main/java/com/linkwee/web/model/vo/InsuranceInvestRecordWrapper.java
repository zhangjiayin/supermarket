package com.linkwee.web.model.vo;

import java.math.BigDecimal;

public class InsuranceInvestRecordWrapper {
	
	/**
	 * 投资人ID
	 */
	private String investorId;
	/**
	 *产品编号
	 */
	private String productId;
	/**
	 *产品名称
	 */
	private String productName;
    /**
     *购买产品金额 单位 元
     */
	private BigDecimal productAmount;
    /**
     *佣金费率
     */
	private BigDecimal feeRate;
    /**
     *年化金额
     */
	private BigDecimal yearpurAmount;
    /**
     *投资记录id(系统产生的唯一标识)
     */
	private String billId;
    /**
     *产品所属机构编号
     */
	private String productOrgId;
	/**
	 * 产品所属机构名称
	 */
	private String orgName;
	/**
	 * 备注信息
	 */
	private String remark;
	
	public String getInvestorId() {
		return investorId;
	}
	public void setInvestorId(String investorId) {
		this.investorId = investorId;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public BigDecimal getProductAmount() {
		return productAmount;
	}
	public void setProductAmount(BigDecimal productAmount) {
		this.productAmount = productAmount;
	}
	public BigDecimal getFeeRate() {
		return feeRate;
	}
	public void setFeeRate(BigDecimal feeRate) {
		this.feeRate = feeRate;
	}
	public BigDecimal getYearpurAmount() {
		return yearpurAmount;
	}
	public void setYearpurAmount(BigDecimal yearpurAmount) {
		this.yearpurAmount = yearpurAmount;
	}
	public String getBillId() {
		return billId;
	}
	public void setBillId(String billId) {
		this.billId = billId;
	}
	public String getProductOrgId() {
		return productOrgId;
	}
	public void setProductOrgId(String productOrgId) {
		this.productOrgId = productOrgId;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
