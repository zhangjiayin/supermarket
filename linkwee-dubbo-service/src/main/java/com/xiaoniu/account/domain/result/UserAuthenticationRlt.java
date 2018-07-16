package com.xiaoniu.account.domain.result;

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 
 * @Description: 用户认证信息
 * @author 周锋恒
 * @date 2015年8月5日
 *
 */
public class UserAuthenticationRlt  implements Serializable {

	private static final long serialVersionUID = -7580827148458244301L;

	/** 用户编号 */
	private String userId;
	
	/** 用户认证姓名 */
	private String userName;
	
	/** 身份证号 */
	private String identityCard;
	
	/** 银行预留手机号码  */
	private String userMobile;
	
	/** 用户银行卡号 */
	private String bankCardNo;
	
	/** 银行代码   */
	private String bankCode;
	
	/** 开户行名称  */
	private String bankName;
	
	private String recordLimitAmount;
	
	private String dayLimitAmount;
	
	private String monthLimitAmount;
	
	private String provider;
	
	private String providerBankCode;
	
	private String providerName;
	
	private String remark;
	
	private String servicePhone;
	
	/** 是否实名认证 */
	private boolean isAuthentication;
	
	/** 是否绑卡	*/
	private boolean isBindCard;

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
	
	public String getUserId() {
		return userId;
	}

	public String getRecordLimitAmount() {
		return recordLimitAmount;
	}

	public void setRecordLimitAmount(String recordLimitAmount) {
		this.recordLimitAmount = recordLimitAmount;
	}

	public String getDayLimitAmount() {
		return dayLimitAmount;
	}

	public void setDayLimitAmount(String dayLimitAmount) {
		this.dayLimitAmount = dayLimitAmount;
	}

	public String getMonthLimitAmount() {
		return monthLimitAmount;
	}

	public void setMonthLimitAmount(String monthLimitAmount) {
		this.monthLimitAmount = monthLimitAmount;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public String getProviderBankCode() {
		return providerBankCode;
	}

	public void setProviderBankCode(String providerBankCode) {
		this.providerBankCode = providerBankCode;
	}

	public String getProviderName() {
		return providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getServicePhone() {
		return servicePhone;
	}

	public void setServicePhone(String servicePhone) {
		this.servicePhone = servicePhone;
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

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getIdentityCard() {
		return identityCard;
	}

	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
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

	public boolean isAuthentication() {
		return isAuthentication;
	}

	public void setAuthentication(boolean isAuthentication) {
		this.isAuthentication = isAuthentication;
	}

	public boolean isBindCard() {
		return isBindCard;
	}

	public void setBindCard(boolean isBindCard) {
		this.isBindCard = isBindCard;
	}
	
}
