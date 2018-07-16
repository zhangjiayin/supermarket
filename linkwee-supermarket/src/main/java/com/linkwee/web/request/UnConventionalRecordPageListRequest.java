package com.linkwee.web.request;

import com.linkwee.core.base.api.PaginatorRequest;

/**
 * 理财师绑定记录分页
 */
public class UnConventionalRecordPageListRequest extends PaginatorRequest {

	private static final long serialVersionUID = -6403667546670342519L;

	private String customerMobile;

	public String getCustomerMobile() {
		return customerMobile;
	}

	public void setCustomerMobile(String customerMobile) {
		this.customerMobile = customerMobile;
	}

	
	
	
}
