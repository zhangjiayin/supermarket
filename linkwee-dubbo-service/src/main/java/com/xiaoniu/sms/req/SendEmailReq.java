package com.xiaoniu.sms.req;

import java.io.Serializable;

public class SendEmailReq extends BaseReq implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3165664148674960264L;
	private String emial;// 接收信息的号码，多个以英文逗号（,）分隔，最多100个
	private String value = "";//替换值，多个以英文逗号分隔

	public String getEmial() {
		return emial;
	}
	public void setEmial(String emial) {
		this.emial = emial;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
