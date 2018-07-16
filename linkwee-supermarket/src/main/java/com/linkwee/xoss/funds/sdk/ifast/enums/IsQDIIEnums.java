package com.linkwee.xoss.funds.sdk.ifast.enums;

import com.linkwee.core.base.KvmEnum;

public enum IsQDIIEnums implements KvmEnum {

	ISQDII_0(0,"0","QDII"),
	ISQDII_1(1,"1","非QDII");

	IsQDIIEnums(int key, String value, String msg) {
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
