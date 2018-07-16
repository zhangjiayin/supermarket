package com.xiaoniu.account.domain;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 
 * @Description: 查找用户填卡的信息
 * @author 周锋恒
 * @date 2015年7月23日
 *
 */
public class SearchCardInfoReq implements Serializable {
	
	private static final long serialVersionUID = 1928529009580877565L;

	/** 业务编号  */
	@NotNull
	private String partnerId;
	
	/** 买家编号  */
	@NotNull
	private String userId;
	
	/**	用户卡号 */
	@NotNull
	private String userPayAccount;
	
	/** 支付渠道    kuai_qian */
	@NotNull
	private String provider;
	
	/** 签名的编码 */
	@NotNull
	private String charset;
	
	/** 签名 */
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPayAccount() {
		return userPayAccount;
	}

	public void setUserPayAccount(String userPayAccount) {
		this.userPayAccount = userPayAccount;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
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