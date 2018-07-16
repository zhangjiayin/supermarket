package com.linkwee.web.enums;

import com.linkwee.core.base.KeyValueEnum;

public enum RepaymentWayEnum implements KeyValueEnum {
	
	一次性到期(1,"一次性到期"),
	一次性按日(101,"一次性按日"),
	一次性按月(102,"一次性按月"),
	一次性按季(103,"一次性按季"),
	等额本息(2,"等额本息"),
	等额本金(3,"等额本金"),
	先息后本(4,"先息后本");
	
	private RepaymentWayEnum(int key, String value) {
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
