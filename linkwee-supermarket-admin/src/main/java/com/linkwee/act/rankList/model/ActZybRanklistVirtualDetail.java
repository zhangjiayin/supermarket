package com.linkwee.act.rankList.model;

public class ActZybRanklistVirtualDetail {

	private String headImg="";
	
	/**
     *用户手机
     */
	private String userMobile;
	
    /**
     *用户名称
     */
	private String userName;
	
	private Integer activityProfit;
	
	private Integer maxTotalProfit;
	private Integer minTotalProfit;
	
	
	
	public String getHeadImg() {
		return headImg;
	}
	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}
	public String getUserMobile() {
		return userMobile;
	}
	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public int getActivityProfit() {
		return activityProfit;
	}
	public Integer getMaxTotalProfit() {
		return maxTotalProfit;
	}
	public void setMaxTotalProfit(Integer maxTotalProfit) {
		this.maxTotalProfit = maxTotalProfit;
	}
	public Integer getMinTotalProfit() {
		return minTotalProfit;
	}
	public void setMinTotalProfit(Integer minTotalProfit) {
		this.minTotalProfit = minTotalProfit;
	}
	public void setActivityProfit(Integer activityProfit) {
		this.activityProfit = activityProfit;
	}
	
}
