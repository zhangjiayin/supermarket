package com.linkwee.web.push.vo;

public class ResultVOProduct {
	
	public ResultVOProduct(Integer bizId, Integer code, String msg) {
		super();
		this.bizId = bizId;
		this.code = code;
		this.msg = msg;
	}
	
	private Integer bizId;
	/**
	 * api返回码 0成功 | 1 失败
	 */
	private Integer code;
	/**
	 * api返回消息
	 */
	private String msg;
	
	public Integer getBizId() {
		return bizId;
	}
	public void setBizId(Integer bizId) {
		this.bizId = bizId;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
