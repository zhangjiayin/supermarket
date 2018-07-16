package com.linkwee.api.response.activity;

import java.math.BigDecimal;

public class YearMaxProfitUserResponse {
	/**
	 * 佣金总额
	 */
	private BigDecimal feeAmount;
	/**
	 * 用户Id
	 */
	private String useId;
	/**
	 * 用户姓名
	 */
	private String userName;
	/**
	 * 用户头像
	 */
	private String headImage;
	
	public BigDecimal getFeeAmount() {
		return feeAmount;
	}
	public void setFeeAmount(BigDecimal feeAmount) {
		this.feeAmount = feeAmount;
	}
	public String getUseId() {
		return useId;
	}
	public void setUseId(String useId) {
		this.useId = useId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getHeadImage() {
		return headImage;
	}
	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}
}
