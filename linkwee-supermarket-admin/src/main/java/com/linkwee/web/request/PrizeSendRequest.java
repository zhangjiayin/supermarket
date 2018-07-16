package com.linkwee.web.request;

public class PrizeSendRequest {

	/**
	 * 中奖用户手机号码
	 */
	private String mobile;
	/**
	 * 开始时间
	 */
	private String startTime;
	/**
	 * 结束时间
	 */
	private String endTime;
	/**
	 * 发放状态
	 */
	private Integer issued;
	/**
	 * 活动ID
	 */
	private String activityId;
	
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
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
	public Integer getIssued() {
		return issued;
	}
	public void setIssued(Integer issued) {
		this.issued = issued;
	}
	public String getActivityId() {
		return activityId;
	}
	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}
}
