package com.linkwee.xoss.insurance.qixin;

public class QixinNotifyBaseResponse {

	/**
	 * 状态
	 */
	private boolean state;
	
	/**
	 * 失败原因
	 */
	private String failMsg;

	public QixinNotifyBaseResponse() {
		super();
	}

	public QixinNotifyBaseResponse(boolean state, String failMsg) {
		super();
		this.state = state;
		this.failMsg = failMsg;
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public String getFailMsg() {
		return failMsg;
	}

	public void setFailMsg(String failMsg) {
		this.failMsg = failMsg;
	}
}
