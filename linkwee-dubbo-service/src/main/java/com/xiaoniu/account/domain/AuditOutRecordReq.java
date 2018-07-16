package com.xiaoniu.account.domain;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class AuditOutRecordReq implements Serializable {


	private static final long serialVersionUID = 332572553089042907L;

	@NotNull
	private Long outRecordNo;
	
	/**  0：成功，1:失败 	*/
	@NotNull
	private String dealCode;

	/** 审核类型 0-即时审核，1-定时审核，2-人工审核，3-定时任务审核*/
	private Integer auditType;
	

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	public Long getOutRecordNo() {
		return outRecordNo;
	}

	public void setOutRecordNo(Long outRecordNo) {
		this.outRecordNo = outRecordNo;
	}

	public String getDealCode() {
		return dealCode;
	}

	public void setDealCode(String dealCode) {
		this.dealCode = dealCode;
	}

	public Integer getAuditType() {
		return auditType;
	}

	public void setAuditType(Integer auditType) {
		this.auditType = auditType;
	}
}
