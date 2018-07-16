package com.linkwee.web.response.funds.ifast;

public class EncryptionBaseResponse {
	
	/**
	 * 第三方用户ID，用于绑定账号
	 */
	private String userId;
	
	/**
	 * 用户的手机号码
	 */
	private String phone;
	
	/**
	 * 用户的真实姓名
	 */
	private String trueName;
	
	/**
	 * 用户的证件号码
	 */
	private String idNo;
	
	/**
	 * 固定值，LHT， 第三方的编码
	 */
	private String referral = "LHT";

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getTrueName() {
		return trueName;
	}

	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public String getReferral() {
		return referral;
	}

	public void setReferral(String referral) {
		this.referral = referral;
	}
}
