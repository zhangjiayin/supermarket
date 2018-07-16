package com.linkwee.api.request.crm;


public class InvestCalendarStatisticsRequest{
	
	/**
	 * 投资时间
	 */
	private String investMonth;
	
	/**
	 * 用户id
	 */
	private String userId;
	
	/**
	 * 查询类型  0-所有(本人及下级)  1-本人  2-本人以外
	 */
	private Integer queryType;

	public String getInvestMonth() {
		return investMonth;
	}

	public void setInvestMonth(String investMonth) {
		this.investMonth = investMonth;
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
