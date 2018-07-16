package com.linkwee.api.request.cim;

import com.linkwee.core.base.api.PaginatorRequest;

public class ProductPageList4Request extends PaginatorRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    /**
     *分类id
     */
	private Integer cateId;

	/**
	 * 机构编码
	 */
	private String orgCode;
	/**
	 * 期限
	 * a=不限 b=1个月内 c=1-3个月 d=3-6个月 e=6-12个月 f=12个月以上
	 */
	private String deadlineValue;
	/**
	 * 年化收益率
	 * a=不限 b=8%以下 c=8%-12% d=12%以上
	 */
	private String flowRate;
	/**
	 * 用户类型
	 * 0-不限 1-新手用户
	 */
	private Integer ifRookie;
	/**
	 * APP类型  investor,channel
	 */
	private String appKind;
	/**
	 * 是否拥有灰度权限 true=拥有灰度权限  false=没有灰度权限
	 */
	private Boolean ifHaveGray;
	
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getDeadlineValue() {
		return deadlineValue;
	}
	public void setDeadlineValue(String deadlineValue) {
		this.deadlineValue = deadlineValue;
	}
	public String getFlowRate() {
		return flowRate;
	}
	public void setFlowRate(String flowRate) {
		this.flowRate = flowRate;
	}
	public Integer getIfRookie() {
		return ifRookie;
	}
	public void setIfRookie(Integer ifRookie) {
		this.ifRookie = ifRookie;
	}
	public String getAppKind() {
		return appKind;
	}
	public void setAppKind(String appKind) {
		this.appKind = appKind;
	}
	public Boolean getIfHaveGray() {
		return ifHaveGray;
	}
	public void setIfHaveGray(Boolean ifHaveGray) {
		this.ifHaveGray = ifHaveGray;
	}
	public Integer getCateId() {
		return cateId;
	}
	public void setCateId(Integer cateId) {
		this.cateId = cateId;
	}
	
}
