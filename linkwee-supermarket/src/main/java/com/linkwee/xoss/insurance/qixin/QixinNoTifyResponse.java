package com.linkwee.xoss.insurance.qixin;

public class QixinNoTifyResponse {

	/**
	 * 通知类型
	 */
	private int notifyType;
	
	/**
	 * 签名
	 */
	private String sign;
	
	/**
	 * 数据
	 */
	private Object data;

	public int getNotifyType() {
		return notifyType;
	}

	public void setNotifyType(int notifyType) {
		this.notifyType = notifyType;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
