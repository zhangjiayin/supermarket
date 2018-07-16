package com.linkwee.openApi.request;

import java.io.Serializable;


public class AccountBankRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    /**
     *用户Id
     */
	//@NotNull(message="用户Id不能为空")
	private String userId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	

}
