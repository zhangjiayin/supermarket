package com.linkwee.web.request.orgInfo;

import com.linkwee.core.base.api.PaginatorRequest;

public class OrgPageList4Request extends PaginatorRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
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
