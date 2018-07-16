package com.linkwee.xoss.funds.sdk.ifast.request;

public class GetBankCodeRequest {

	/**
	 * 银行卡号
	 */
	private String bankNumber;

	public String getBankNumber() {
		return bankNumber;
	}

	public void setBankNumber(String bankNumber) {
		this.bankNumber = bankNumber;
	}
}
