package com.xiaoniu.account.domain;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;


/**
 * 
 * @Description: 接收支付平台通知表单
 * @author 周锋恒
 * @date 2015年7月24日
 *
 */
public class InRecordAcceptNoticeReq implements Serializable {

	private static final long serialVersionUID = 8021095800760455216L;

	/** 充值记录流水号 */
	@NotNull
	private Long inRecordNo;
	
	@NotNull
	private String userId;
	
	private String userName;
	
	private String userMobile;
	
	@NotNull
	private Long totalAmount; 
	
	@NotNull
	private String payNo;
	
	private String paymentMethod;
	
	
	private String bankCardNo;
	
	
	/** 返回代码 */
	@NotNull
	private String returnCode;
	
	private String returnMsg;


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	public Long getInRecordNo() {
		return inRecordNo;
	}

	public void setInRecordNo(Long inRecordNo) {
		this.inRecordNo = inRecordNo;
	}


	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public String getUserId() {
		return userId;
	}

	public String getBankCardNo() {
		return bankCardNo;
	}

	public void setBankCardNo(String bankCardNo) {
		this.bankCardNo = bankCardNo;
	}

	public String getReturnMsg() {
		return returnMsg;
	}

	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}

	public String getPayNo() {
		return payNo;
	}

	public void setPayNo(String payNo) {
		this.payNo = payNo;
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

	public Long getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Long totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}


}
