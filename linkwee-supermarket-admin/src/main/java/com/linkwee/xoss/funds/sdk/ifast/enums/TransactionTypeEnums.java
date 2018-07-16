package com.linkwee.xoss.funds.sdk.ifast.enums;

import com.linkwee.core.base.KvmEnum;

public enum TransactionTypeEnums implements KvmEnum {
	
	TRANSACTIONTYPE_1(1,"buy","申购"),
	TRANSACTIONTYPE_2(2,"ipo","认购"),
	TRANSACTIONTYPE_3(3,"sell","赎回"),
	TRANSACTIONTYPE_4(4,"rsp","定投"),
	TRANSACTIONTYPE_5(5,"dividend","红利再投"),
	TRANSACTIONTYPE_6(6,"intra.swtich","同公司转换"),
	TRANSACTIONTYPE_7(7,"inter.switch","跨公司转换"),
	TRANSACTIONTYPE_8(8,"rapid.sell","快速取现");
	
	TransactionTypeEnums(int key, String value, String msg) {
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
