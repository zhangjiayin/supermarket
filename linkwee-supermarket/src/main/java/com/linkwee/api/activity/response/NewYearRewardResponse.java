package com.linkwee.api.activity.response;

public class NewYearRewardResponse {

	/**
	 * 用户ID
	 */
	private String userId;
	/**
	 * 手机号码
	 */
	private String mobile;
	/**
	 * 比率
	 */
	private int rate;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getMobile() {	
		return mobile.substring(0, 3)+"****"+mobile.substring(7);
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public int getRate() {
		char lastChar = userId.charAt(userId.length()-1);
		if(lastChar > '9'){
			return 6;
		}else{
			return 12;
		}	
	}
	public void setRate(int rate) {
		this.rate = rate;
	}
	
}
