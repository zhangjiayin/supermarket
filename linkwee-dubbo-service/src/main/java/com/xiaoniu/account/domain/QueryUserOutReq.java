package com.xiaoniu.account.domain;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;


/**
 * 
 * @Description: 查询用户提现的总资金和提现中的资金入参
 * @author 周锋恒
 * @date 2015年8月21日
 *
 */
public class QueryUserOutReq implements Serializable {

	private static final long serialVersionUID = 121781158882363837L;
	
	/** 用户编号 */
	@NotNull
	private String userId;
	
	
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

	public String getUserId() {
		return userId;
	}

	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
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

}
