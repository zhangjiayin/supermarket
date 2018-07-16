package com.xiaoniu.account.domain;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.xiaoniu.account.domain.base.PageBackReqBase;

/**
 * 后台查询提现记录
 * @author zhoufengheng
 *
 */
public class SearchOutRecordListReq extends PageBackReqBase implements Serializable {

	private static final long serialVersionUID = 1197271715471036743L;
	
	/** 提现流水号 */
	private Long outRecordNo;
	
	/** 业务ID */
	private String partnerId;
	
	/** 业务流水号 */
	private String partnerTradeNo;
	
	/** 用户ID */
	private String userId;

	/** 用户名称 */
	private String userName;
	
	/** 提值状态 */
	private String status;
	
	/** 支付流水号 */
	private String payNo;

	/** 金额范围  */
	private Long minAmount;

	/** 金额范围  */
	private Long maxAmount;
	
	/** 开始时间 */
	private Date successBeginTime;
	
	/** 结束时间 */
	private Date successEndTime;

	/** 银行编码 */
	private String bankCode;

	/** 银行名称 */
	private String bankName;


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	public Long getOutRecordNo() {
		return outRecordNo;
	}

	public Date getSuccessBeginTime() {
		return successBeginTime;
	}

	public void setSuccessBeginTime(Date successBeginTime) {
		this.successBeginTime = successBeginTime;
	}

	public Date getSuccessEndTime() {
		return successEndTime;
	}

	public void setSuccessEndTime(Date successEndTime) {
		this.successEndTime = successEndTime;
	}

	public Long getMinAmount() {
		return minAmount;
	}

	public void setMinAmount(Long minAmount) {
		this.minAmount = minAmount;
	}

	public Long getMaxAmount() {
		return maxAmount;
	}

	public void setMaxAmount(Long maxAmount) {
		this.maxAmount = maxAmount;
	}

	public void setOutRecordNo(Long outRecordNo) {
		this.outRecordNo = outRecordNo;
	}

	public String getPartnerTradeNo() {
		return partnerTradeNo;
	}

	public void setPartnerTradeNo(String partnerTradeNo) {
		this.partnerTradeNo = partnerTradeNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPayNo() {
		return payNo;
	}

	public void setPayNo(String payNo) {
		this.payNo = payNo;
	}

	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
}
