package com.linkwee.api.request.crm;


public class InvestCalendarDetailRequest {
	
	/**
	 * 投资记录id
	 */
	private String investId;
	
	/**
	 * 用户id
	 */
	private String userId;

	public String getInvestId() {
		return investId;
	}

	public void setInvestId(String investId) {
		this.investId = investId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
