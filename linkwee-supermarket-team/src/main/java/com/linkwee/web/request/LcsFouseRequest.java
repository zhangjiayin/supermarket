package com.linkwee.web.request;

public class LcsFouseRequest {

	/**
	 * 关注状态
	 */
	private int status;
	/**
	 * 理财师手机号
	 */
	private String cfpMobile;
	/**
	 * 合伙人
	 */
	private String salesOrgId;
	/**
	 * 理财师userid
	 */
	private String cfplanner;
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getCfpMobile() {
		return cfpMobile;
	}
	public void setCfpMobile(String cfpMobile) {
		this.cfpMobile = cfpMobile;
	}
	public String getSalesOrgId() {
		return salesOrgId;
	}
	public void setSalesOrgId(String salesOrgId) {
		this.salesOrgId = salesOrgId;
	}
	public String getCfplanner() {
		return cfplanner;
	}
	public void setCfplanner(String cfplanner) {
		this.cfplanner = cfplanner;
	}
}
