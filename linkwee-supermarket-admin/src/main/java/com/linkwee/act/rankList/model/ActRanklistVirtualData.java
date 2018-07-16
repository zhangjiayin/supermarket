package com.linkwee.act.rankList.model;

import java.io.Serializable;
import java.math.BigDecimal;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class ActRanklistVirtualData implements  Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3420939791306541791L;
	
	
	public ActRanklistVirtualData() {
	}
	
	public ActRanklistVirtualData(String rankListCode,String levelName,String headImg, String userMobile, String userName,BigDecimal totalProfit) {
		super();
		this.rankListCode = rankListCode;
		this.levelName = levelName;
		this.headImg = headImg;
		this.userMobile = userMobile;
		this.userName = userName;
		this.totalProfit = totalProfit;
	}
	
	private String rankListCode;

	private String headImg;
	
	/**
     *用户手机
     */
	private String userMobile;
	
    /**
     *用户名称
     */
	private String userName;
	private String levelName;
	
	private BigDecimal totalProfit;
	
	
	

	public String getRankListCode() {
		return rankListCode;
	}

	public void setRankListCode(String rankListCode) {
		this.rankListCode = rankListCode;
	}
	
	

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

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

	public BigDecimal getTotalProfit() {
		return totalProfit;
	}

	public void setTotalProfit(BigDecimal totalProfit) {
		this.totalProfit = totalProfit;
	}

	
	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}

}
