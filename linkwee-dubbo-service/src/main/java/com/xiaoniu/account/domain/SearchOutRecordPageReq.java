package com.xiaoniu.account.domain;

import com.xiaoniu.account.domain.base.PageListReq;
import com.xiaoniu.account.utils.ReqValidateUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 后台分页查询提现记录
 * @author lipw
 *
 */
public class SearchOutRecordPageReq extends PageListReq {

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
	
	/** 开始时间 格式：yyyy-MM-dd */
	private String successBeginTime;
	
	/** 结束时间 格式：yyyy-MM-dd */
	private String successEndTime;

	/** 银行编码 */
	private String bankCode;

	/** 银行名称 */
	private String bankName;


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
	/**
	 * 校验日期参数
	 * @return
	 */
	@Override
	public boolean verifyDateParams(){
		if (StringUtils.isNotEmpty(this.getBeginTime()) && !ReqValidateUtil.isValidDate(this.getBeginTime())) {
			return false;
		}
		if (StringUtils.isNotEmpty(this.getEndTime()) && !ReqValidateUtil.isValidDate(this.getEndTime())) {
			return false;
		}
		if (StringUtils.isNotEmpty(this.getSuccessBeginTime()) && !ReqValidateUtil.isValidDate(this.getSuccessBeginTime())) {
			return false;
		}
		if (StringUtils.isNotEmpty(this.getSuccessEndTime()) && !ReqValidateUtil.isValidDate(this.getSuccessEndTime())) {
			return false;
		}
		return true;
	}

	/**
	 * 设置日期参数
	 * @return
	 */
	@Override
	public void initQueryDateParams(){
		if (StringUtils.isNotEmpty(this.getBeginTime())) {
			this.setBeginTime(this.getBeginTime() + " 00:00:00");
		}
		if (StringUtils.isNotEmpty(this.getEndTime())) {
			this.setEndTime(this.getEndTime() + " 23:59:59");
		}
		if (StringUtils.isNotEmpty(this.getSuccessBeginTime())) {
			this.setSuccessBeginTime(this.getSuccessBeginTime() + " 00:00:00");
		}
		if (StringUtils.isNotEmpty(this.getSuccessEndTime())) {
			this.setSuccessEndTime(this.getSuccessEndTime() + " 23:59:59");
		}
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

	public String getPayNo() {
		return payNo;
	}

	public void setPayNo(String payNo) {
		this.payNo = payNo;
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

	public String getSuccessBeginTime() {
		return successBeginTime;
	}

	public void setSuccessBeginTime(String successBeginTime) {
		this.successBeginTime = successBeginTime;
	}

	public String getSuccessEndTime() {
		return successEndTime;
	}

	public void setSuccessEndTime(String successEndTime) {
		this.successEndTime = successEndTime;
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
