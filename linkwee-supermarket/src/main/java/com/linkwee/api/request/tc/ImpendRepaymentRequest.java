package com.linkwee.api.request.tc;

import com.linkwee.core.base.api.PaginatorRequest;

public class ImpendRepaymentRequest extends PaginatorRequest{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3255126950997417042L;
	
	/**
	 * 用户编号
	 */
	private String customerId;

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	
}
