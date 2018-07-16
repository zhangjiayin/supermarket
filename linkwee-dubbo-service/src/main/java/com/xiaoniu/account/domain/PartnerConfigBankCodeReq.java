/*************************************************************************
 *                  XIAONIU66 CONFIDENTIAL AND PROPRIETARY                                                      
 *
 *                COPYRIGHT (C) XIAONIU66 CORPORATION 2016                                                      
 *    ALL RIGHTS RESERVED BY XIAONIU66 CORPORATION. THIS PROGRAM    
 * MUST BE USED  SOLELY FOR THE PURPOSE FOR WHICH IT WAS FURNISHED BY   
 * XIAONIU66 CORPORATION. NO PART OF THIS PROGRAM MAY BE REPRODUCED
 * OR DISCLOSED TO OTHERS,IN ANY FORM, WITHOUT THE PRIOR WRITTEN       
 * PERMISSION OF XIAONIU66 CORPORATION. USE OF COPYRIGHT NOTICE   
 * DOES NOT EVIDENCE PUBLICATION OF THE PROGRAM.                       
 *                  XIAONIU66 CONFIDENTIAL AND PROPRIETARY        
 *************************************************************************/

package com.xiaoniu.account.domain;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 业务银行编码请求参数.
 *
 * @author lipw
 * @version 1.0
 * @date 2016/9/30 14:31
 */
public class PartnerConfigBankCodeReq implements Serializable {

	private static final long serialVersionUID = 8625598652823218473L;

	//业务编码
	@NotNull
	private String partnerId;

	//银行编码
	@NotNull
	private String bankCode;

	//银行名称
	@NotNull
	private String bankName;

	//支付渠道编码
	@NotNull
	private String payChannelCode;

	//支付渠道名称
	@NotNull
	private String payChannelName;

	//支付渠道银行编码
	@NotNull
	private String payChannelBankCode;

	//支付渠道额度限制
	@NotNull
	private String payChannelAmountLimit;

	//支付渠道到账时效
	@NotNull
	private String payChannelAmountArrivalTime;

	//用户单笔提现金额最少限额
	@NotNull
	private Long withdrawAmountMinLimit;

	//用户单笔金额限制
	@NotNull
	private Long withdrawAmountLimit;

	//单日金额限制
	@NotNull
	private Long withdrawOutDayAmountLimit;

	//单月金额限制
	@NotNull
	private Long withdrawOutMonthAmountLimit;

	//服务电话
	private String servicePhone;

	//规则检查开关
	@NotNull
	private String ruleCheckSwitch;

	//预计到账时间取值
	@NotNull
	private String expectedArrivalTimeValue;

	//备注
	private String remark;

	//创建人
	private String createdBy;

	//更新人
	private String updatedBy;

	private String charset;

	private String sign;

	@Override public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
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

	public String getPayChannelCode() {
		return payChannelCode;
	}

	public void setPayChannelCode(String payChannelCode) {
		this.payChannelCode = payChannelCode;
	}

	public String getPayChannelName() {
		return payChannelName;
	}

	public void setPayChannelName(String payChannelName) {
		this.payChannelName = payChannelName;
	}

	public String getPayChannelBankCode() {
		return payChannelBankCode;
	}

	public void setPayChannelBankCode(String payChannelBankCode) {
		this.payChannelBankCode = payChannelBankCode;
	}

	public String getPayChannelAmountLimit() {
		return payChannelAmountLimit;
	}

	public void setPayChannelAmountLimit(String payChannelAmountLimit) {
		this.payChannelAmountLimit = payChannelAmountLimit;
	}

	public String getPayChannelAmountArrivalTime() {
		return payChannelAmountArrivalTime;
	}

	public void setPayChannelAmountArrivalTime(String payChannelAmountArrivalTime) {
		this.payChannelAmountArrivalTime = payChannelAmountArrivalTime;
	}

	public Long getWithdrawAmountMinLimit() {
		return withdrawAmountMinLimit;
	}

	public void setWithdrawAmountMinLimit(Long withdrawAmountMinLimit) {
		this.withdrawAmountMinLimit = withdrawAmountMinLimit;
	}

	public Long getWithdrawAmountLimit() {
		return withdrawAmountLimit;
	}

	public void setWithdrawAmountLimit(Long withdrawAmountLimit) {
		this.withdrawAmountLimit = withdrawAmountLimit;
	}

	public Long getWithdrawOutDayAmountLimit() {
		return withdrawOutDayAmountLimit;
	}

	public void setWithdrawOutDayAmountLimit(Long withdrawOutDayAmountLimit) {
		this.withdrawOutDayAmountLimit = withdrawOutDayAmountLimit;
	}

	public Long getWithdrawOutMonthAmountLimit() {
		return withdrawOutMonthAmountLimit;
	}

	public void setWithdrawOutMonthAmountLimit(Long withdrawOutMonthAmountLimit) {
		this.withdrawOutMonthAmountLimit = withdrawOutMonthAmountLimit;
	}

	public String getServicePhone() {
		return servicePhone;
	}

	public void setServicePhone(String servicePhone) {
		this.servicePhone = servicePhone;
	}

	public String getRuleCheckSwitch() {
		return ruleCheckSwitch;
	}

	public void setRuleCheckSwitch(String ruleCheckSwitch) {
		this.ruleCheckSwitch = ruleCheckSwitch;
	}

	public String getExpectedArrivalTimeValue() {
		return expectedArrivalTimeValue;
	}

	public void setExpectedArrivalTimeValue(String expectedArrivalTimeValue) {
		this.expectedArrivalTimeValue = expectedArrivalTimeValue;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
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
