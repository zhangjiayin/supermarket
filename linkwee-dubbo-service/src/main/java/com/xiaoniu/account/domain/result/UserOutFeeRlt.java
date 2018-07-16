package com.xiaoniu.account.domain.result;

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 
 * @Description: 查询用户的提现手续费结果
 * @author 周锋恒
 * @date 2015年8月21日
 *
 */
public class UserOutFeeRlt implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**  是否有手续费   */
	private boolean isHasFee;

	/** 提现手续费金额  */
	private Long fee = 0l;
	
	/** 提现收手续费剩余次数  */
	private int limitTimes;

	/**
	 * 充值投资状态
	 * 1-充值已投资提现小于等于3次
	 * 2-充值未投资
	 * 3-充值已投资提现大于3次
	 */
 	private Integer recInvestStatus;
	
	public boolean isHasFee() {
		return isHasFee;
	}

	public void setHasFee(boolean isHasFee) {
		this.isHasFee = isHasFee;
	}

	public Long getFee() {
		return fee;
	}

	public int getLimitTimes() {
		return limitTimes;
	}

	public void setLimitTimes(int limitTimes) {
		this.limitTimes = limitTimes;
	}

	public void setFee(Long fee) {
		this.fee = fee;
	}

	public Integer getRecInvestStatus() {
		return recInvestStatus;
	}

	public void setRecInvestStatus(Integer recInvestStatus) {
		this.recInvestStatus = recInvestStatus;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
	
}
