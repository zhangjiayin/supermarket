package com.linkwee.api.response.tc;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linkwee.core.base.BaseEntity;
import com.linkwee.core.jackson.MoneySerializer;

public class CfpFeeInfoResponse extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7472726169878215583L;
	/**
	 * 序号
	 */
	private int rowNumber;
	/**
	 * 时间字符串
	 */
	private String timeString;
	/**
	 * 手机号
	 */
	private String mobile;
	/**
	 * 获得的佣金
	 */
	@JsonSerialize(using=MoneySerializer.class)
	private BigDecimal feeAmount;
	
	public int getRowNumber() {
		return rowNumber;
	}
	public void setRowNumber(int rowNumber) {
		this.rowNumber = rowNumber;
	}
	public String getTimeString() {
		if(rowNumber >= 1 && rowNumber <= 5){
			return "10分钟前";
		}else if(rowNumber >= 6 && rowNumber <= 8){
			return "15分钟前";
		}else{
			return "30分钟前";
		}
	}
	public void setTimeString(String timeString) {
		this.timeString = timeString;
	}
	public String getMobile() {
		return mobile.substring(0, 3)+"****"+mobile.substring(7);
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public BigDecimal getFeeAmount() {
		int temp = Integer.valueOf(mobile.substring(0, 7))%599;
		if(temp < 30){
			temp += 30;
		}
		BigDecimal temp2 = new BigDecimal(mobile.substring(7)).divide(new BigDecimal(9999),2, BigDecimal.ROUND_DOWN);
		return temp2.add(new BigDecimal(temp));
	}
	public void setFeeAmount(BigDecimal feeAmount) {
		this.feeAmount = feeAmount;
	}
}
