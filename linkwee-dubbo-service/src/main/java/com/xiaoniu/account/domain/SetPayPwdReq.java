package com.xiaoniu.account.domain;

import javax.validation.constraints.NotNull;


public class SetPayPwdReq extends PayPasswordBaseReq{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@NotNull
	private String payPassword;

	public String getPayPassword() {
		return payPassword;
	}

	public void setPayPassword(String payPassword) {
		this.payPassword = payPassword;
	}
}
