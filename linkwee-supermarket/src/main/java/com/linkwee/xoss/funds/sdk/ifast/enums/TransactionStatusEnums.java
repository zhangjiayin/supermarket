package com.linkwee.xoss.funds.sdk.ifast.enums;

import com.linkwee.core.base.KvmEnum;

public enum TransactionStatusEnums  implements KvmEnum {
	
	TRANSACTIONSTATUS_1(1,"failure","支付失败"),
	TRANSACTIONSTATUS_2(2,"received","下单成功"),
	TRANSACTIONSTATUS_3(3,"priced","确认成功"),
	TRANSACTIONSTATUS_4(4,"completed","交易成功"),
	TRANSACTIONSTATUS_5(5,"ipo.processing","认购确认成功"),
	TRANSACTIONSTATUS_6(6,"pending.payment","等待付款"),
	TRANSACTIONSTATUS_7(7,"canceling","撤单中"),
	TRANSACTIONSTATUS_8(8,"canceled","已撤单"),
	TRANSACTIONSTATUS_9(9,"pending.void","等待退款"),
	TRANSACTIONSTATUS_10(10,"void","交易失败"),
	TRANSACTIONSTATUS_11(11,"payment","支付中"),
	;
	
	TransactionStatusEnums(int key, String value, String msg) {
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
