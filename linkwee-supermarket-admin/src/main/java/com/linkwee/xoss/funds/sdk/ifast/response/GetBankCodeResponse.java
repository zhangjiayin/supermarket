package com.linkwee.xoss.funds.sdk.ifast.response;

public class GetBankCodeResponse {

	/**
	 * 银行代码
	 */
	private String bankCode;
	
	/**
	 * 银行名字
	 */
	private String bankName;

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
}
