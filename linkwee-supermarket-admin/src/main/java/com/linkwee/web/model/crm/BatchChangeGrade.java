package com.linkwee.web.model.crm;

import java.io.Serializable;

public class BatchChangeGrade implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5204475246348147454L;
	
	private String mobile;
	
	private String grade;
	
	private String userId;

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
	

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
