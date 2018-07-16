package com.linkwee.web.enums.cim;

import com.linkwee.core.base.KvmEnum;

public enum OrgGradeEnum implements KvmEnum{
	
	B(1,"1","B"),
	BB(2,"2","BB"),
	BBB(3,"3","BBB"),
	A(4,"4","A"),
	AA(5,"5","AA"),
	AAA(6,"6","AAA");

	private OrgGradeEnum(int key, String value, String msg) {
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
	public void setKey(int key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}

}
