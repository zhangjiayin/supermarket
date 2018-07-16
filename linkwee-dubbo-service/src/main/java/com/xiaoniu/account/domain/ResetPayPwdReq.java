package com.xiaoniu.account.domain;

import javax.validation.constraints.NotNull;


public class ResetPayPwdReq extends PayPasswordBaseReq{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@NotNull
	private String newPayPassword;
	@NotNull
	private String payPassword;
	
	public String getPayPassword() {
		return payPassword;
	}

	public void setPayPassword(String payPassword) {
		this.payPassword = payPassword;
	}

	public String getNewPayPassword() {
		return newPayPassword;
	}

	public void setNewPayPassword(String newPayPassword) {
		this.newPayPassword = newPayPassword;
	}
}
