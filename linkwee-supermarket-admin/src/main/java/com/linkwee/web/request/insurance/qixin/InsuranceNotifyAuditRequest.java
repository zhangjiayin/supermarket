package com.linkwee.web.request.insurance.qixin;

import com.linkwee.core.datatable.DataTable;

public class InsuranceNotifyAuditRequest  extends DataTable{

	/**
     * 理财师姓名
     */
	private String userName;
	/**
     * 手机号
     */
	private String mobile;
	/**
     * 保单号
     */
	private String insureNum;
    /**
     *审核状态(99-全部  1-系统审核通过 2-系统审核失败 3-管理员审核通过 4-管理员审核失败)
     */
	private Integer auditStatus;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getInsureNum() {
		return insureNum;
	}
	public void setInsureNum(String insureNum) {
		this.insureNum = insureNum;
	}
	public Integer getAuditStatus() {
		return auditStatus;
	}
	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
	}
}
