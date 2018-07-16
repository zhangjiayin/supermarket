package com.linkwee.web.model.cim;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class CimProductPayImport implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8379439482652805680L;
	
	private String mobile;
	
	private String orgNumber;
	
	private Integer productDeadLineValue;
	
	private BigDecimal investAmt;
	
	private BigDecimal feeAmt;
	
	private Date investTime;

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getOrgNumber() {
		return orgNumber;
	}

	public void setOrgNumber(String orgNumber) {
		this.orgNumber = orgNumber;
	}

	public Integer getProductDeadLineValue() {
		return productDeadLineValue;
	}

	public void setProductDeadLineValue(Integer productDeadLineValue) {
		this.productDeadLineValue = productDeadLineValue;
	}

	public BigDecimal getInvestAmt() {
		return investAmt;
	}

	public void setInvestAmt(BigDecimal investAmt) {
		this.investAmt = investAmt;
	}

	public BigDecimal getFeeAmt() {
		return feeAmt;
	}

	public void setFeeAmt(BigDecimal feeAmt) {
		this.feeAmt = feeAmt;
	}

	public Date getInvestTime() {
		return investTime;
	}

	public void setInvestTime(Date investTime) {
		this.investTime = investTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
