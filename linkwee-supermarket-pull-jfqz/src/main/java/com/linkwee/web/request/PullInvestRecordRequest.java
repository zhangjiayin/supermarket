package com.linkwee.web.request;

public class PullInvestRecordRequest {

	/**
	 * 投资记录开始时间
	 */
	private String startTime;
	/**
	 * 投资记录截止时间
	 */
	private String endTime;
	/**
	 * 投资编号
	 */
	private String investId;
	/**
	 * 用户id
	 */
	private String userId;
	
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
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
