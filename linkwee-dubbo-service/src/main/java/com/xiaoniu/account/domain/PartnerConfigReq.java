package com.xiaoniu.account.domain;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 
 * @author 周锋恒
 * 
 * @Date 2016年1月8日
 */
public class PartnerConfigReq implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 业务编号 */
	@NotNull
	private String partnerId;

	private String tradeKey;

	private String paymentKey;

	/** 自动审核开关 ：开：on， 关：off */
	@NotNull
	private String autoAuditOutSwitch;
	//审核开关类型:0-及时，1-定时
	@NotNull
	private Integer auditOutSwitchType;

	//审核开关取值
	private String auditOutSwitchValue;

	@NotNull
	private Long userOutAmountMinLimit;
	@NotNull
	private Long userOutAmountLimit;
	@NotNull
	private Long userOutDayAmountLimit;
	@NotNull
	private Long userOutMonthAmountLimit;
	@NotNull
	private Integer userOutDayTimesLimit;
	@NotNull
	private Integer userOutMonthTimesLimit;
	@NotNull
	private Long outDayAmountLimit;
	@NotNull
	private Long outMonthAmountLimit;
	@NotNull
	private Integer outDayTimesLimit;
	@NotNull
	private Integer outMonthTimesLimit;
	/** 多个手机号用逗号隔开 */
	@NotNull
	private String alarmMobile;
	@NotNull
	private Integer alarmCashErrors;
	//定时任务执行时间
	private String taskExecutionTime;
	/** 规则校验银行开关 */
	private String ruleBankRangeSwitch;

	/** 多个值以逗号隔开，规则校验银行范围 */
	private String ruleBankRange;

	private String extra1;

	private String extra2;

	private String createdBy;

	private String updatedBy;

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
