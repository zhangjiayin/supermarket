package com.linkwee.web.model;

import com.linkwee.xoss.util.business.BusinessUtils;

public class CimInsuranceFeedetailExtends extends CimInsuranceFeedetail {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 投资人用户名称
	 */
	private String userName;
	
	/**
	 * 投资人手机号码
	 */
	private String mobile;
	
	/**
	 * 产品名称
	 */
	private String productName;
	
	/**
	 * 用户名称手机号
	 */
	private String userNameMobile;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getUserNameMobile() {
		userNameMobile = BusinessUtils.getUserNameMobile(userName, mobile);
		return userNameMobile;
	}

	public void setUserNameMobile(String userNameMobile) {
		this.userNameMobile = userNameMobile;
	}
}
