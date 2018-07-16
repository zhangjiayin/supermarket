package com.linkwee.api.request.cim;

import javax.validation.constraints.NotNull;

public class OrgMoneyDataRequest {

	/**
	 * 平台编码
	 */
	@NotNull(message="平台编码不能为空")
	private String orgNo;
	
	/**
	 * 查询分类类型  1=成交量   2=资金净流入
	 */
	@NotNull(message="分类类型不能为空")
	private  Integer queryType;
	
	/**
	 * 时间类型  1=日   2=周  3=月
	 */
	@NotNull(message="时间类型不能为空")
	private  Integer timeType;
	
	/**
	 * 数据数量
	 */
	private Integer dataNumber = 7;

	public String getOrgNo() {
		return orgNo;
	}

	public void setOrgNo(String orgNo) {
		this.orgNo = orgNo;
	}

	public Integer getQueryType() {
		return queryType;
	}

	public void setQueryType(Integer queryType) {
		this.queryType = queryType;
	}

	public Integer getTimeType() {
		return timeType;
	}

	public void setTimeType(Integer timeType) {
		this.timeType = timeType;
	}

	public Integer getDataNumber() {
		return dataNumber;
	}

	public void setDataNumber(Integer dataNumber) {
		this.dataNumber = dataNumber;
	}
}
