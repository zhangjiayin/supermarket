package com.linkwee.web.push.vo;

public class ResultVO {
	
	
	
	public ResultVO(String bizId, Integer code, String msg) {
		super();
		this.bizId = bizId;
		this.code = code;
		this.msg = msg;
	}
	private String bizId;
	/**
	 * api返回码 0成功 | 1 失败
	 */
	private Integer code;
	/**
	 * api返回消息
	 */
	private String msg;
	public String getBizId() {
		return bizId;
	}
	public void setBizId(String bizId) {
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
