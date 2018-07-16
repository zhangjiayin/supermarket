package com.linkwee.xoss.funds.sdk.ifast.enums;

import com.linkwee.core.base.KvmEnum;

public enum FundStatusEnums implements KvmEnum{
	
	FUNDSTATUS_0(0,"0","募集期基金"),
	FUNDSTATUS_1(1,"1","申购期基金"),
	FUNDSTATUS_2(2,"2","封闭期基金"),
	FUNDSTATUS_3(3,"3","已清盘的基金");
	
	FundStatusEnums(int key, String value, String msg) {
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
