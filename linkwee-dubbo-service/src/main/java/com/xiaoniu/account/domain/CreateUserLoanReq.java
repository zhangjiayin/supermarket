package com.xiaoniu.account.domain;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 
 * @Description: 创建用户借贷账户请求参数。
 * @author 颜彩云
 * @date 2016年5月19日
 *
 */
public class CreateUserLoanReq implements Serializable {

	private static final long serialVersionUID = -5866466436237594707L;

	/** 业务编号  */
	@NotNull
	private String partnerId;
	
	/** 用户编号 */
	@NotNull
	private String userId;
	
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

	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}
	
}
