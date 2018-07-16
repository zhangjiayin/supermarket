package com.linkwee.web.model.cim;

import java.io.Serializable;

public class RechargeLimitImport implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8379439482652805680L;
	
	/**
	 * 支持银行
	 */
	private String bank;
	
	/**
	 * 单笔限额
	 */
	private String orderLimit;
	
	/**
	 * 每日限额
	 */
	private String dayLimit;
	
	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getOrderLimit() {
		return orderLimit;
	}

	public void setOrderLimit(String orderLimit) {
		this.orderLimit = orderLimit;
	}

	public String getDayLimit() {
		return dayLimit;
	}

	public void setDayLimit(String dayLimit) {
		this.dayLimit = dayLimit;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
