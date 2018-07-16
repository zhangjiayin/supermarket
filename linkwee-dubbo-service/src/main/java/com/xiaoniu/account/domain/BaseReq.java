package com.xiaoniu.account.domain;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class BaseReq implements Serializable {

	private static final long serialVersionUID = 2206082162544274664L;

	/** 业务编号 	*/
	@NotNull
	private String partnerId;
	
	/** 业务流水号  */
	@NotNull
	private String partnerTradeNo;
	
	/** 用户ID   */
	@NotNull
	private String userId;
	
	/** 签名的编码 */
	@NotNull
	private String charset;
	
	/** 签名串  */
	@NotNull
	private String sign;

	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getPartnerTradeNo() {
		return partnerTradeNo;
	}

	public void setPartnerTradeNo(String partnerTradeNo) {
		this.partnerTradeNo = partnerTradeNo;
	}
	
}
