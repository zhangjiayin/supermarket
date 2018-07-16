package com.linkwee.api.request.crm;

import com.linkwee.core.base.api.PaginatorRequest;

public class RepamentCalendarRequest  extends PaginatorRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 回款时间
	 */
	private String repamentTime;
	
	/**
	 * 0-待回款 1-已回款 非必需 默认查所有
	 */
	private Integer repamentType;
	
	/**
	 * 用户id
	 */
	private String userId;
	
	/**
	 * 查询类型  0-所有(本人及下级)  1-本人  2-本人以外
	 */
	private Integer queryType;

	public String getRepamentTime() {
		return repamentTime;
	}

	public void setRepamentTime(String repamentTime) {
		this.repamentTime = repamentTime;
	}

	public Integer getRepamentType() {
		return repamentType;
	}

	public void setRepamentType(Integer repamentType) {
		this.repamentType = repamentType;
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
