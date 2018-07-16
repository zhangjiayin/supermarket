package com.xiaoniu.account.domain;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 后台查询提现记录
 * @author zhoufengheng
 *
 */
public class SearchOutRecordReq implements Serializable {

	private static final long serialVersionUID = 1197271715471036743L;
	
	/** 提现流水号 */
	@NotNull
	private Long outRecordNo;
	
	/** 业务ID */
	@NotNull
	private String partnerId;

	/** 签名编码 */
	@NotNull
	private String charset;

	/** 签名 */
	@NotNull
	private String sign;


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

	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
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
}
