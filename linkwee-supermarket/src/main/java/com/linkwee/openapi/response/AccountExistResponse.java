package com.linkwee.openapi.response;

public class AccountExistResponse {

	/**
	 * 用户是否已存在  Y存在 N 不存在
	 */
	private String isExist;

	public String getIsExist() {
		return isExist;
	}

	public void setIsExist(String isExist) {
		this.isExist = isExist;
	}
}
