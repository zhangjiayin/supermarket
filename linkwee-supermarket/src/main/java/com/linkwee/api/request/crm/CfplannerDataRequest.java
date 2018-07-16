package com.linkwee.api.request.crm;

import java.util.Date;

public class CfplannerDataRequest {

	/**
	 * 用户ID
	 */
	private String userId;
	
	/**
	 * 查询日期
	 */
	private Date queryDate;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getQueryDate() {
		return queryDate;
	}

	public void setQueryDate(Date queryDate) {
		this.queryDate = queryDate;
	}
}
