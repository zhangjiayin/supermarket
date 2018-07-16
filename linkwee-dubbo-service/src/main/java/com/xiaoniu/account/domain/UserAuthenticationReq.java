package com.xiaoniu.account.domain;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 
 * @Description: 用户认证请求信息
 * @author 周锋恒
 * @date 2015年8月5日
 *
 */
public class UserAuthenticationReq implements Serializable {

	private static final long serialVersionUID = -7866137297373508122L;

	/** 用户编号 */
	@NotNull
	private String userId;
	
	@NotNull
	private String partnerId;
	
	/** 用户认证姓名 */
	@NotNull
	private String userName;
	
	/** 身份证号 */
	@NotNull
	private String identityCard;
	
	/** 银行预留手机号码  */
	private String userMobile;
	
	/** 用户银行卡号 */
	@NotNull
	private String bankCardNo;
	
	/** 银行代码   */
	@NotNull
	private String bankCode;
	
	/** 银行名称  */
	private String bankName;
	
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getIdentityCard() {
		return identityCard;
	}

	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public String getBankCardNo() {
		return bankCardNo;
	}

	public void setBankCardNo(String bankCardNo) {
		this.bankCardNo = bankCardNo;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
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
