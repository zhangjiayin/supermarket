package com.linkwee.openapi.response;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class OmsAccountBindResponse {

	/**
	 * 领会userId
	 */
	private String userId;
	
	/**
	 * 是否是领会新用户  "Y"（是）或者"N"（否）
	 */
	private String isNewUser;
	
	/**
	 * 绑定时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8") 
	private Date bindTime;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getIsNewUser() {
		return isNewUser;
	}

	public void setIsNewUser(String isNewUser) {
		this.isNewUser = isNewUser;
	}

	public Date getBindTime() {
		return bindTime;
	}

	public void setBindTime(Date bindTime) {
		this.bindTime = bindTime;
	}
}
