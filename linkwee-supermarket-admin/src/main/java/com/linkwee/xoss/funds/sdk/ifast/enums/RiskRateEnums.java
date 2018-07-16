package com.linkwee.xoss.funds.sdk.ifast.enums;

import com.linkwee.core.base.KvmEnum;

public enum RiskRateEnums implements KvmEnum {

	RISKRATE_1(1,"1","低风险"),
	RISKRATE_2(2,"2","中低风险"),
	RISKRATE_3(3,"3","中风险"),
	RISKRATE_4(4,"4","中高风险"),
	RISKRATE_5(5,"5","高风险");

	RiskRateEnums(int key, String value, String msg) {
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
