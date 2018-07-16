package com.linkwee.web;

public class CimInsuranceCategory {

	public CimInsuranceCategory() {

	}
	
	public CimInsuranceCategory(String category, String message) {
		super();
		this.category = category;
		this.message = message;
	}

	/**
	 * 类型
	 */
	private String category;
	
	/**
	 * 描述
	 */
	private String message;

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
