package com.linkwee.web.model.crm;

import java.util.Date;

import com.linkwee.core.base.BaseEntity;

public class LcsHongbaoListResp extends BaseEntity {

	private static final long serialVersionUID = 1840107652864359699L;

	/**
	 * 红包金额
	 */
	private Double hongbaoAmount;
	/**
	 * 适用平台
	 */
	private String platform;
	/**
	 * 首投限制
	 */
	private int firstInvestLimit;
	/**
	 * 过期时间
	 */
	private Date expireTime;
	
	/**
	 * 金额限制类型
	 */
	private int amountLimit;
	
	/**
	 * 限制金额
	 */
	private Double amount;
	
	/**
	 * 产品期限限制类型
	 */
	private int productLimit;
	
	/**
	 * 现在产品期限
	 */
	private int productDeadline;

	public Double getHongbaoAmount() {
		return hongbaoAmount;
	}

	public void setHongbaoAmount(Double hongbaoAmount) {
		this.hongbaoAmount = hongbaoAmount;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public int getFirstInvestLimit() {
		return firstInvestLimit;
	}

	public void setFirstInvestLimit(int firstInvestLimit) {
		this.firstInvestLimit = firstInvestLimit;
	}

	public Date getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}

	public int getAmountLimit() {
		return amountLimit;
	}

	public void setAmountLimit(int amountLimit) {
		this.amountLimit = amountLimit;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public int getProductLimit() {
		return productLimit;
	}

	public void setProductLimit(int productLimit) {
		this.productLimit = productLimit;
	}

	public int getProductDeadline() {
		return productDeadline;
	}

	public void setProductDeadline(int productDeadline) {
		this.productDeadline = productDeadline;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	

	
	
}
