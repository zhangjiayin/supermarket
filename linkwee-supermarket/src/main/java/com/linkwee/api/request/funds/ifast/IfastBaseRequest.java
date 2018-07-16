package com.linkwee.api.request.funds.ifast;

public class IfastBaseRequest {

	/**
	 * 用户的奕丰账户,用户的奕丰账户。在开户接口有返回给商户
	 */
	private String	accountNumber;

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
}
