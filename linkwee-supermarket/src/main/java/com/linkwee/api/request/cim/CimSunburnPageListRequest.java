package com.linkwee.api.request.cim;

import com.linkwee.core.base.api.PaginatorRequest;

public class CimSunburnPageListRequest extends PaginatorRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    /**
     *分类id
     */
	private Integer type;
	
	private String userId;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}

	
}
