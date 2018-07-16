package com.linkwee.api.response.acc;

public class TokenResponse {
	
	private String message;
	
	private Token data;
	
	private String code;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Token getData() {
		return data;
	}

	public void setData(Token data) {
		this.data = data;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	

}
