package com.linkwee.api.request.crm;

import com.linkwee.core.base.api.PaginatorRequest;

public class InvestCalendarRequest extends PaginatorRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 投资时间
	 */
	private String investTime;
	
	/**
	 * 用户id
	 */
	private String userId;
	
	/**
	 * 查询类型  0-所有(本人及下级)  1-本人  2-本人以外
	 */
	private Integer queryType;

	public String getInvestTime() {
		return investTime;
	}

	public void setInvestTime(String investTime) {
		this.investTime = investTime;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getQueryType() {
		return queryType;
	}

	public void setQueryType(Integer queryType) {
		this.queryType = queryType;
	}

}
