package com.linkwee.web.enums;

import com.linkwee.core.base.KvmEnum;

public enum ActicityPersonAddFeeTicketPeriodEnum implements KvmEnum {

	ONE_YUAN_DRAW_ADD_FEE_TICKET_1_PERIOD(20181012,"ONE_YUAN_DRAW_ADD_FEE_TICKET_1_PERIOD","个人加拥券1%");//个人加拥券1% 的期限
	
	private int key;
	private String value;
	private String msg;

	ActicityPersonAddFeeTicketPeriodEnum(int key,String value,String msg){
		this.key = key;
		this.value = value;
		this.msg = msg;
	}

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
