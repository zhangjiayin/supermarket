package com.linkwee.api.request.act;

import com.linkwee.core.base.api.PaginatorRequest;

public class ActPersonAddfeeTicketRequest extends PaginatorRequest {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 理财师userId
	 */
	private String userId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
