package com.linkwee.xoss.funds.sdk.ifast.enums;

import com.linkwee.core.base.KvmEnum;

public enum PayMethodEnums implements KvmEnum {
	
	PAYMETHOD_0(0,"0","银行卡"),
	PAYMETHOD_1(1,"1","现金钱包支付");
	
	PayMethodEnums(int key, String value, String msg) {
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
