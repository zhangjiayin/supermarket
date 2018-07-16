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
 * 确认还款请求参数.
 *
 * @author lipw
 * @version 1.0
 * @date 2016/6/2 9:43
 */
public class ConfirmRepaymentRecordReq extends BaseReq {

	/** 产品代码  */
	@NotNull
	private String productCode;

	/** 产品名称  */
	@NotNull
	private String productName;

	/** 借款单号  */
	@NotNull
	private Long borrowRecordNo;

	/** 还款金额  */
	@NotNull
	private Long repaymentAmount;

	/** 还款本金  */
	@NotNull
	private Long repaymentCapitalAmount;

	/** 还款利息  */
	@NotNull
	private Long repaymentInterestAmount;

	/** 罚息金额  */
	@NotNull
	private Long lateInterestAmount;

	/** 金额还款方式 1-全额余额还款，2-部分余额还款 */
	@NotNull
	private Integer repaymentType;

	/** 违约金额  */
	private Long violateAmount;
	
	/** 备注信息 */
	private String remark;

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
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

	public Long getBorrowRecordNo() {
		return borrowRecordNo;
	}

	public void setBorrowRecordNo(Long borrowRecordNo) {
		this.borrowRecordNo = borrowRecordNo;
	}

	public Long getRepaymentAmount() {
		return repaymentAmount;
	}

	public void setRepaymentAmount(Long repaymentAmount) {
		this.repaymentAmount = repaymentAmount;
	}

	public Long getRepaymentCapitalAmount() {
		return repaymentCapitalAmount;
	}

	public void setRepaymentCapitalAmount(Long repaymentCapitalAmount) {
		this.repaymentCapitalAmount = repaymentCapitalAmount;
	}

	public Long getRepaymentInterestAmount() {
		return repaymentInterestAmount;
	}

	public void setRepaymentInterestAmount(Long repaymentInterestAmount) {
		this.repaymentInterestAmount = repaymentInterestAmount;
	}

	public Long getLateInterestAmount() {
		return lateInterestAmount;
	}

	public void setLateInterestAmount(Long lateInterestAmount) {
		this.lateInterestAmount = lateInterestAmount;
	}

	public Long getViolateAmount() {
		return violateAmount;
	}

	public void setViolateAmount(Long violateAmount) {
		this.violateAmount = violateAmount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getRepaymentType() {
		return repaymentType;
	}

	public void setRepaymentType(Integer repaymentType) {
		this.repaymentType = repaymentType;
	}
}
