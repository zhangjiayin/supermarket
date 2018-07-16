package com.xiaoniu.account.domain;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import javax.validation.constraints.NotNull;

/**
 * 投资下单请求参数
 * @author 周锋恒
 * 
 * @Date 2015年12月2日
 */
public class ReConfirmInvestOrderReq extends InvestRecordReq {

	private static final long serialVersionUID = -8240154407883431175L;
	
	/** 红包金额 */
	@NotNull
	private Long redPaperTotalAmount;

	/*****************************/
	/** 借款人ID */
	@NotNull
	private String borrowUserId;
	/** 借款单号 */
	@NotNull
	private Long borrowRecordNo;
	/** 借款金额 */
	@NotNull
	private Long borrowAmount;
	/** 借款用途 */
	private String borrowPurpose;
	/** 还款期数*/
	@NotNull
	private Integer repaymentTimes;
	/** 管理费 */
	@NotNull
	private Long manageFee;
	/** 借款金额 */
	private Long guaranteeFee;
	/** 借款利率 */
	@NotNull
	private Long borrowRate;
	/** 借款开始时间  yyyy-MM-dd HH:mm:ss*/
	@NotNull
	private String borrowBiginDate;
	/** 借款结束时间  yyyy-MM-dd HH:mm:ss*/
	@NotNull
	private String borrowEndDate;


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	public Long getRedPaperTotalAmount() {
		return redPaperTotalAmount;
	}

	public void setRedPaperTotalAmount(Long redPaperTotalAmount) {
		this.redPaperTotalAmount = redPaperTotalAmount;
	}

	public Long getBorrowAmount() {
		return borrowAmount;
	}

	public void setBorrowAmount(Long borrowAmount) {
		this.borrowAmount = borrowAmount;
	}

	public String getBorrowPurpose() {
		return borrowPurpose;
	}

	public void setBorrowPurpose(String borrowPurpose) {
		this.borrowPurpose = borrowPurpose;
	}

	public Integer getRepaymentTimes() {
		return repaymentTimes;
	}

	public void setRepaymentTimes(Integer repaymentTimes) {
		this.repaymentTimes = repaymentTimes;
	}

	public Long getManageFee() {
		return manageFee;
	}

	public void setManageFee(Long manageFee) {
		this.manageFee = manageFee;
	}

	public Long getGuaranteeFee() {
		return guaranteeFee;
	}

	public void setGuaranteeFee(Long guaranteeFee) {
		this.guaranteeFee = guaranteeFee;
	}

	public Long getBorrowRate() {
		return borrowRate;
	}

	public void setBorrowRate(Long borrowRate) {
		this.borrowRate = borrowRate;
	}

	public String getBorrowBiginDate() {
		return borrowBiginDate;
	}

	public void setBorrowBiginDate(String borrowBiginDate) {
		this.borrowBiginDate = borrowBiginDate;
	}

	public String getBorrowEndDate() {
		return borrowEndDate;
	}

	public void setBorrowEndDate(String borrowEndDate) {
		this.borrowEndDate = borrowEndDate;
	}

	public Long getBorrowRecordNo() {
		return borrowRecordNo;
	}

	public void setBorrowRecordNo(Long borrowRecordNo) {
		this.borrowRecordNo = borrowRecordNo;
	}

	public String getBorrowUserId() {
		return borrowUserId;
	}

	public void setBorrowUserId(String borrowUserId) {
		this.borrowUserId = borrowUserId;
	}
}
