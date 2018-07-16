package com.xiaoniu.account.domain;

import javax.validation.constraints.NotNull;

/**
 * 
 * @author 颜彩云
 *
 */
public class CheckPayPwdReq extends PayPasswordBaseReq{
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
