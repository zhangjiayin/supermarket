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

/**
 * 借款单请求.
 *
 * @author lipw
 * @version 1.0
 * @date 2016/5/31 17:29
 */
public class BorrowRecordReq extends BaseReq {

	private static final long serialVersionUID = 7604823133919366913L;

	/** 投资名称 */
	@NotNull
	private String bisName;

	/** 投资时间  yyyy-MM-dd HH:mm:ss    请按格式传入，方便对账	*/
	@NotNull
	private String bisTime;

	/** 产品代码  */
	@NotNull
	private String productCode;

	/** 产品名称  */
	@NotNull
	private String productName;
	/** 借款金额 */
	@NotNull
	private Long borrowAmount;
	/** 借款用途 */
	@NotNull
	private String borrowPurpose;
	/** 还款期数*/
	@NotNull
	private Integer repaymentTimes;
	/** 管理费 */
	@NotNull
	private Long manageFee;

	/** 担保费 */
	private Long guaranteeFee;

	/** 借款利率 */
	@NotNull
	private Long borrowRate;

	/** 借款开始时间  yyyy-MM-dd HH:mm:ss*/
	private String borrowBiginDate;

	/** 借款结束时间  yyyy-MM-dd HH:mm:ss*/
	private String borrowEndDate;

	/** 用户操作IP  */
	private String clientIp;

	/** 备注信息 */
	private String remark;

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
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

	public String getBisName() {
		return bisName;
	}

	public void setBisName(String bisName) {
		this.bisName = bisName;
	}

	public String getBisTime() {
		return bisTime;
	}

	public void setBisTime(String bisTime) {
		this.bisTime = bisTime;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getClientIp() {
		return clientIp;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
