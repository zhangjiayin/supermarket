package com.linkwee.api.activity.response;

public class ChristmasHomePageResponse {
	/**
	 * 信是否已读 1：已读 0：未读
	 */
	private int letterReadStatus;
	/**
	 * 用户名
	 */
	private String userName;
	/**
	 * 总共收集的袜子数
	 */
	private int socksNum;
	/**
	 * 剩余可用的袜子数
	 */
	private int leftSocksNum;
	/**
	 * 是否已助力 1：是 0：否
	 */
	private int hasHelped;	
	/**
	 * 手机号
	 */
	private String mobile;
	
	/**
	 * 用户Id
	 */
	private String userId;
	
	public int getLetterReadStatus() {
		return letterReadStatus;
	}
	public void setLetterReadStatus(int letterReadStatus) {
		this.letterReadStatus = letterReadStatus;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getSocksNum() {
		return socksNum;
	}
	public void setSocksNum(int socksNum) {
		this.socksNum = socksNum;
	}
	public int getLeftSocksNum() {
		return leftSocksNum;
	}
	public void setLeftSocksNum(int leftSocksNum) {
		this.leftSocksNum = leftSocksNum;
	}
	public int getHasHelped() {
		return hasHelped;
	}
	public void setHasHelped(int hasHelped) {
		this.hasHelped = hasHelped;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
}
