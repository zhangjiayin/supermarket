package com.linkwee.xoss.funds.sdk.ifast.base;

public class IfastBaseRespons<R,S> {

	/**
	 * 返回代码
	 */
	private String code;
	/**
	 * 返回信息
	 */
	private String message;
	/**
	 * 请求信息
	 */
	private R request;
	/**
	 * 返回信息
	 */
	private S data;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public R getRequest() {
		return request;
	}
	public void setRequest(R request) {
		this.request = request;
	}
	public S getData() {
		return data;
	}
	public void setData(S data) {
		this.data = data;
	}
}
