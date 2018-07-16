package com.linkwee.api.response.funds.ifast;

public class IfRegisterResponse {

	/**
	 * 是否注册
	 */
	private boolean ifRegister;
	
	public IfRegisterResponse() {
		super();
	}

	public IfRegisterResponse(boolean ifRegister) {
		super();
		this.ifRegister = ifRegister;
	}

	public boolean isIfRegister() {
		return ifRegister;
	}

	public void setIfRegister(boolean ifRegister) {
		this.ifRegister = ifRegister;
	}
}
