package com.linkwee.openapi.request;

import com.linkwee.core.base.api.PaginatorRequest;

public class OmsProductRequest extends PaginatorRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 机构编码
	 */
	private String orgNumber;

	public String getOrgNumber() {
		return orgNumber;
	}

	public void setOrgNumber(String orgNumber) {
		this.orgNumber = orgNumber;
	}
}
