package com.linkwee.xoss.funds.sdk.ifast.enums;

import com.linkwee.core.base.KvmEnum;

public enum IsMMFundEnums implements KvmEnum {

	ISMMFUND_0(0,"0","货币型基金"),
	ISMMFUND_1(1,"1","非货币型基金");

	IsMMFundEnums(int key, String value, String msg) {
		this.key = key;
		this.value = value;
		this.msg = msg;
	}
	
	private int key;
	private String value;
	private String msg;
	
	public int getKey() {
		return key;
	}
	public String getValue() {
		return value;
	}
	public String getMsg() {
		return msg;
	}
}
