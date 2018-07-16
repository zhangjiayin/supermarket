package com.linkwee.api.request.crm;


public class RePaymentCalendarStatisticsRequest{
	
	/**
	 * 回款月份
	 */
	private String rePaymentMonth;
	
	/**
	 * 用户id
	 */
	private String userId;
	
	/**
	 * 0-待回款 1-已回款 非必需 默认查所有
	 */
	private Integer repamentType;
	
	/**
	 * 查询类型  0-所有(本人及下级)  1-本人  2-本人以外
	 */
	private Integer queryType;

	public String getRePaymentMonth() {
		return rePaymentMonth;
	}

	public void setRePaymentMonth(String rePaymentMonth) {
		this.rePaymentMonth = rePaymentMonth;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getRepamentType() {
		return repamentType;
	}

	public void setRepamentType(Integer repamentType) {
		this.repamentType = repamentType;
	}

	public Integer getQueryType() {
		return queryType;
	}

	public void setQueryType(Integer queryType) {
		this.queryType = queryType;
	}
}
