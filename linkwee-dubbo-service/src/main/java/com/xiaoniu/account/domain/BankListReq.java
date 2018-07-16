package com.xiaoniu.account.domain;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;



/**
 * @Description: 取银行列表参数
 * @author 周锋恒
 * @date 2015年7月23日
 *
 */
public class BankListReq implements Serializable {

	private static final long serialVersionUID = 7149014461680582354L;
	
	/** 业务编号  不为空 	*/
	@NotNull
	private String partnerId;
	
	/** 签名的编码 utf-8  不为空*/
	@NotNull
	private String charset;
	
	/** 签名 不为空  */
	@NotNull
	private String sign;
	

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
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
