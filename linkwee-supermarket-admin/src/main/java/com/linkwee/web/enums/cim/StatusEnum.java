package com.linkwee.web.enums.cim;

import com.linkwee.core.base.KeyValueEnum;

public enum StatusEnum implements KeyValueEnum {
	
	在售(1,"在售"),
	售罄(2,"售罄"),
	募集失败(3,"募集失败");
	
	private StatusEnum(int key, String value) {
		this.key = key;
		this.value = value;
	}
	
	private int key;
	private String value;
	
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
}
