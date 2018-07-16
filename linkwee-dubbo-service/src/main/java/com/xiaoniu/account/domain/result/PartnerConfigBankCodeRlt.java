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

package com.xiaoniu.account.domain.result;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Date;

/**
 * 业务方银行编码配置输出对象.
 *
 * @author lipw
 * @version 1.0
 * @date 2016/9/30 14:40
 */
public class PartnerConfigBankCodeRlt implements Serializable {

	private static final long serialVersionUID = -7382770071509830017L;

	//ID
	private Integer id;

	//业务编码
	private String partnerId;

	//银行编码
	private String bankCode;

	//银行名称
	private String bankName;

	//支付渠道编码
	private String payChannelCode;

	//支付渠道名称
	private String payChannelName;

	//支付渠道银行编码
	private String payChannelBankCode;

	//支付渠道额度限制
	private String payChannelAmountLimit;

	//支付渠道到账时效
	private String payChannelAmountArrivalTime;

	//用户单笔提现金额最少限额
	private Long withdrawAmountMinLimit;

	//用户单笔金额限制
	private Long withdrawAmountLimit;

	//单日金额限制
	private Long withdrawOutDayAmountLimit;

	//单月金额限制
	private Long withdrawOutMonthAmountLimit;

	//服务电话
	private String servicePhone;

	//规则检查开关
	private String ruleCheckSwitch;

	//预计到账时间取值
	private String expectedArrivalTimeValue;

	//备注
	private String remark;

	//创建人
	private String createdBy;

	//创建时间
	private Date createdTime;

	//更新人
	private String updatedBy;

	//更新时间
	private Date updatedTime;

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
}
