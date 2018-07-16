package com.linkwee.act.rankList.model;

public class ActRanklistVirtualDetail {

	private String headImg;
	
	/**
     *用户手机
     */
	private String userMobile;
	
    /**
     *用户名称
     */
	private String userName;
	
	private Integer maxProfit;
	private Integer minProfit;
	
	
	
	
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
	public Integer getMaxProfit() {
		return maxProfit;
	}
	public void setMaxProfit(Integer maxProfit) {
		this.maxProfit = maxProfit;
	}
	public Integer getMinProfit() {
		return minProfit;
	}
	public void setMinProfit(Integer minProfit) {
		this.minProfit = minProfit;
	}
	
}
