package com.linkwee.api.response.crm;

import java.io.Serializable;
 /**
 * 
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2017年10月17日 15:33:01
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class CustomerCfpmember implements Serializable {
	
	private static final long serialVersionUID = 4705570091076552752L;
	
	
    /**
     *	职级
     */
	private String grade;
	
    /**
     *	头像
     */
	private String headImage;
	
    /**
     *最近一笔投资
     */
	private String recentTranDate;
	
    /**
     *姓名
     */
	private String userName;
	
	/**
     *姓名
     */
	private String mobile;
	
	 /**
     *用户id
     */
	private String userId;
	
	/**
    *注册时间
    */
	private String registTime;
	
	
	public String getRegistTime() {
		return registTime;
	}

	public void setRegistTime(String registTime) {
		this.registTime = registTime;
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getHeadImage() {
		return headImage;
	}

	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}

	public String getRecentTranDate() {
		return recentTranDate;
	}

	public void setRecentTranDate(String recentTranDate) {
		this.recentTranDate = recentTranDate;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
		
}

