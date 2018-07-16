package com.xiaoniu.account.domain.result;

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 
 * @Description: 统计用户的提现金额
 * @author 周锋恒
 * @date 2015年8月21日
 *
 */
public class UserOutSumRlt implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/** 用户提现的总金额 */
	private Long outTotalAmount;
	
	/** 用户提现的总手续费 */
	private Long outTotalFee;
	
	/** 用户提现中的金额 */
	private Long outingAmount;
	
	/** 提现中的手续费 */
	private Long outingFee;

	public Long getOutTotalAmount() {
		return outTotalAmount;
	}

	public void setOutTotalAmount(Long outTotalAmount) {
		this.outTotalAmount = outTotalAmount;
	}
	
	public Long getOutingAmount() {
		return outingAmount;
	}

	public Long getOutTotalFee() {
		return outTotalFee;
	}

	public void setOutTotalFee(Long outTotalFee) {
		this.outTotalFee = outTotalFee;
	}

	public Long getOutingFee() {
		return outingFee;
	}

	public void setOutingFee(Long outingFee) {
		this.outingFee = outingFee;
	}

	public void setOutingAmount(Long outingAmount) {
		this.outingAmount = outingAmount;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
	
}
