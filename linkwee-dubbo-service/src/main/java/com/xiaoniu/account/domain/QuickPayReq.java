package com.xiaoniu.account.domain;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;


/**
 * 
 * @author 周锋恒
 * 
 * @Date 2016年3月16日
 */
public class QuickPayReq extends QuickPaySendSmsReq {

	private static final long serialVersionUID = -1409189609098599898L;
	
	/** 手机验证码  */
	@NotEmpty
	private String validCode;
	
	/** 充值流水号   */
	@NotNull
	private Long recordNo;

	public String getValidCode() {
		return validCode;
	}

	public void setValidCode(String validCode) {
		this.validCode = validCode;
	}

	public Long getRecordNo() {
		return recordNo;
	}

	public void setRecordNo(Long recordNo) {
		this.recordNo = recordNo;
	}
	
}
