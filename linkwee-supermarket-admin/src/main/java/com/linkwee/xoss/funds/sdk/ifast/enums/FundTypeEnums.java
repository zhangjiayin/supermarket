package com.linkwee.xoss.funds.sdk.ifast.enums;

import com.linkwee.core.base.KvmEnum;

public enum FundTypeEnums implements KvmEnum {
	
	FUNDTYPE_0(0,"MM","货币型"),
	FUNDTYPE_1(1,"BOND","债券型"),
	FUNDTYPE_2(2,"MIXED","混合型"),
	FUNDTYPE_3(3,"CP","保本型"),
	FUNDTYPE_4(4,"EQ","股票型"),
	FUNDTYPE_5(5,"AI","另类型"),
	FUNDTYPE_6(6,"INDEX","指数型"),
	FUNDTYPE_7(7,"ST","分级型"),
	FUNDTYPE_8(8,"UNKNOWN","其他");
	
	private FundTypeEnums(int key, String value, String msg) {
		this.key = key;
		this.value = value;
		this.msg = msg;
	}

	private int key;
	private String value;
	private String msg;
	
	@Override
	public int getKey() {
		// TODO Auto-generated method stub
		return this.key;
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return this.value;
	}

	@Override
	public String getMsg() {
		// TODO Auto-generated method stub
		return this.msg;
	}

}
