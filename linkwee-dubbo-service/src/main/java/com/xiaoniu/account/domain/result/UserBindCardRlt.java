package com.xiaoniu.account.domain.result;

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 
 * @Description: 用户绑卡信息
 * @author 周锋恒
 * @date 2015年10月26日
 *
 */
public class UserBindCardRlt  implements Serializable {

	private static final long serialVersionUID = -6356122010190513527L;

	
	private String userId;
	
	private String userName;
	
	private String identityCard;
	
	private String userCardNo;
	
	private String userPhoneNo;
	
	
	private String bankCode;
	
	private String bankName;
	
	private long recordLimitAmount;
	
	private long dayLimitAmount;
	
	private long monthLimitAmount;
	
	private String provider;
	
	private String providerName;
	
	private String servicePhone;
	
	private String remark;
	
	/**  当前支付渠道是否是首次支付    */
	private boolean isProviderFirstPay;
	
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	
	public String getUserId() {
		return userId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public boolean isProviderFirstPay() {
		return isProviderFirstPay;
	}

	public void setProviderFirstPay(boolean isProviderFirstPay) {
		this.isProviderFirstPay = isProviderFirstPay;
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

	public String getUserCardNo() {
		return userCardNo;
	}

	public void setUserCardNo(String userCardNo) {
		this.userCardNo = userCardNo;
	}

	public String getUserPhoneNo() {
		return userPhoneNo;
	}

	public void setUserPhoneNo(String userPhoneNo) {
		this.userPhoneNo = userPhoneNo;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public long getRecordLimitAmount() {
		return recordLimitAmount;
	}

	public void setRecordLimitAmount(long recordLimitAmount) {
		this.recordLimitAmount = recordLimitAmount;
	}

	public long getDayLimitAmount() {
		return dayLimitAmount;
	}

	public void setDayLimitAmount(long dayLimitAmount) {
		this.dayLimitAmount = dayLimitAmount;
	}

	public long getMonthLimitAmount() {
		return monthLimitAmount;
	}

	public void setMonthLimitAmount(long monthLimitAmount) {
		this.monthLimitAmount = monthLimitAmount;
	}

	public String getProviderName() {
		return providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

	public String getServicePhone() {
		return servicePhone;
	}

	public void setServicePhone(String servicePhone) {
		this.servicePhone = servicePhone;
	}
	
}
