package com.linkwee.api.request.cim;

import com.linkwee.core.base.api.PaginatorRequest;

public class MyInvestrecordRequest extends PaginatorRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 0-在投产品 1-已到期产品
	 */
	private Integer investType;
	/**
	 * 用户id
	 */
	private String userId;
	/**
	 * 最近一次读取接口时间
	 */
	private String lastReaddate;

	public Integer getInvestType() {
		return investType;
	}

	public void setInvestType(Integer investType) {
		this.investType = investType;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getLastReaddate() {
		return lastReaddate;
	}

	public void setLastReaddate(String lastReaddate) {
		this.lastReaddate = lastReaddate;
	}
}
