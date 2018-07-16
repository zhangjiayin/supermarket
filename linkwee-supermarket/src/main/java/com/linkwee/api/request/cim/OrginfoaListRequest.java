package com.linkwee.api.request.cim;

public class OrginfoaListRequest {

	/**
	 * 是否拥有灰度权限 true=拥有灰度权限  false=没有灰度权限
	 */
	private Boolean ifHaveGray;

	public Boolean getIfHaveGray() {
		return ifHaveGray;
	}

	public void setIfHaveGray(Boolean ifHaveGray) {
		this.ifHaveGray = ifHaveGray;
	}
}
