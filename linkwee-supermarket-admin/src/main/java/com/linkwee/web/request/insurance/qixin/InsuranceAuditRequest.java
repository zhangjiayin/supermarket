package com.linkwee.web.request.insurance.qixin;


public class InsuranceAuditRequest{

	/**
	 * ID
	 */
	private Integer id;
	/**
	 * 审核状态
	 */
	private Integer auditStatus;
	
	/**
	 * 用户名称
	 */
	private String userName;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getAuditStatus() {
		return auditStatus;
	}
	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
}
