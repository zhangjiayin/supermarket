package com.xiaoniu.account.domain.result;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import javax.validation.constraints.NotNull;

/**
 * 
 * @author 周锋恒
 * 
 * @Date 2016年1月8日
 */
public class PartnerConRlt implements Serializable {

	private static final long serialVersionUID = 1L;

	private String partnerId;

	private String tradeKey;

	private String paymentKey;
	// 审核开关类型
	private Integer auditOutSwitchType;
	// 审核开关取值
	private String auditOutSwitchValue;

	private String autoAuditOutSwitch;

	private Long userOutAmountMinLimit;

	private Long userOutAmountLimit;

	private Long userOutDayAmountLimit;

	private Long userOutMonthAmountLimit;

	private Integer userOutDayTimesLimit;

	private Integer userOutMonthTimesLimit;

	private Long outDayAmountLimit;

	private Long outMonthAmountLimit;

	private Integer outDayTimesLimit;

	private Integer outMonthTimesLimit;

	private String alarmMobile;

	private Integer alarmCashErrors;

	private String taskExecutionTime;

	private String ruleBankRangeSwitch;

	private String ruleBankRange;

	private String extra1;

	private String extra2;

	private String createdBy;

	private Date createdTime;

	private String updatedBy;

	private Date updatedTime;

	private Integer dataVersion;

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}

	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	public String getTradeKey() {
		return tradeKey;
	}

	public void setTradeKey(String tradeKey) {
		this.tradeKey = tradeKey;
	}

	public String getPaymentKey() {
		return paymentKey;
	}

	public void setPaymentKey(String paymentKey) {
		this.paymentKey = paymentKey;
	}

	public String getAutoAuditOutSwitch() {
		return autoAuditOutSwitch;
	}

	public void setAutoAuditOutSwitch(String autoAuditOutSwitch) {
		this.autoAuditOutSwitch = autoAuditOutSwitch;
	}

	public Long getUserOutAmountMinLimit() {
		return userOutAmountMinLimit;
	}

	public void setUserOutAmountMinLimit(Long userOutAmountMinLimit) {
		this.userOutAmountMinLimit = userOutAmountMinLimit;
	}

	public Long getUserOutAmountLimit() {
		return userOutAmountLimit;
	}

	public void setUserOutAmountLimit(Long userOutAmountLimit) {
		this.userOutAmountLimit = userOutAmountLimit;
	}

	public Long getUserOutDayAmountLimit() {
		return userOutDayAmountLimit;
	}

	public void setUserOutDayAmountLimit(Long userOutDayAmountLimit) {
		this.userOutDayAmountLimit = userOutDayAmountLimit;
	}

	public Long getUserOutMonthAmountLimit() {
		return userOutMonthAmountLimit;
	}

	public void setUserOutMonthAmountLimit(Long userOutMonthAmountLimit) {
		this.userOutMonthAmountLimit = userOutMonthAmountLimit;
	}

	public Integer getUserOutDayTimesLimit() {
		return userOutDayTimesLimit;
	}

	public void setUserOutDayTimesLimit(Integer userOutDayTimesLimit) {
		this.userOutDayTimesLimit = userOutDayTimesLimit;
	}

	public Integer getUserOutMonthTimesLimit() {
		return userOutMonthTimesLimit;
	}

	public void setUserOutMonthTimesLimit(Integer userOutMonthTimesLimit) {
		this.userOutMonthTimesLimit = userOutMonthTimesLimit;
	}

	public Long getOutDayAmountLimit() {
		return outDayAmountLimit;
	}

	public void setOutDayAmountLimit(Long outDayAmountLimit) {
		this.outDayAmountLimit = outDayAmountLimit;
	}

	public Long getOutMonthAmountLimit() {
		return outMonthAmountLimit;
	}

	public void setOutMonthAmountLimit(Long outMonthAmountLimit) {
		this.outMonthAmountLimit = outMonthAmountLimit;
	}

	public Integer getOutDayTimesLimit() {
		return outDayTimesLimit;
	}

	public void setOutDayTimesLimit(Integer outDayTimesLimit) {
		this.outDayTimesLimit = outDayTimesLimit;
	}

	public Integer getOutMonthTimesLimit() {
		return outMonthTimesLimit;
	}

	public void setOutMonthTimesLimit(Integer outMonthTimesLimit) {
		this.outMonthTimesLimit = outMonthTimesLimit;
	}

	public String getExtra1() {
		return extra1;
	}

	public void setExtra1(String extra1) {
		this.extra1 = extra1;
	}

	public String getExtra2() {
		return extra2;
	}

	public void setExtra2(String extra2) {
		this.extra2 = extra2;
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

	public Integer getDataVersion() {
		return dataVersion;
	}

	public void setDataVersion(Integer dataVersion) {
		this.dataVersion = dataVersion;
	}

	public Integer getAuditOutSwitchType() {
		return auditOutSwitchType;
	}

	public void setAuditOutSwitchType(Integer auditOutSwitchType) {
		this.auditOutSwitchType = auditOutSwitchType;
	}

	public String getAuditOutSwitchValue() {
		return auditOutSwitchValue;
	}

	public void setAuditOutSwitchValue(String auditOutSwitchValue) {
		this.auditOutSwitchValue = auditOutSwitchValue;
	}

	public String getAlarmMobile() {
		return alarmMobile;
	}

	public void setAlarmMobile(String alarmMobile) {
		this.alarmMobile = alarmMobile;
	}

	public Integer getAlarmCashErrors() {
		return alarmCashErrors;
	}

	public void setAlarmCashErrors(Integer alarmCashErrors) {
		this.alarmCashErrors = alarmCashErrors;
	}

	public String getTaskExecutionTime() {
		return taskExecutionTime;
	}

	public void setTaskExecutionTime(String taskExecutionTime) {
		this.taskExecutionTime = taskExecutionTime;
	}

	public String getRuleBankRange() {
		return ruleBankRange;
	}

	public void setRuleBankRange(String ruleBankRange) {
		this.ruleBankRange = ruleBankRange;
	}

	public String getRuleBankRangeSwitch() {
		return ruleBankRangeSwitch;
	}

	public void setRuleBankRangeSwitch(String ruleBankRangeSwitch) {
		this.ruleBankRangeSwitch = ruleBankRangeSwitch;
	}
}
