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
 * 回款确认请求参数.
 *
 * @author lipw
 * @version 1.0
 * @date 2016/6/2 9:57
 */
public class ConfirmReturnRecordReq extends BaseReq {

	/** 产品代码  */
	@NotNull
	private String productCode;

	/** 产品名称  */
	@NotNull
	private String productName;

	/** 对应投资的流水  */
	@NotNull
	private Long investRecordNo;

	/** 回款的金额，本金的金额 */
	@NotNull
	private Long returnAmount;

	/** 收益金额 */
	@NotNull
	private Long profitAmount;

	/** 备注信息 */
	private String remark;

	/** 还款单号 */
	@NotNull
	private Long repaymentRecordNo;

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

	public Long getInvestRecordNo() {
		return investRecordNo;
	}

	public void setInvestRecordNo(Long investRecordNo) {
		this.investRecordNo = investRecordNo;
	}

	public Long getReturnAmount() {
		return returnAmount;
	}

	public void setReturnAmount(Long returnAmount) {
		this.returnAmount = returnAmount;
	}

	public Long getProfitAmount() {
		return profitAmount;
	}

	public void setProfitAmount(Long profitAmount) {
		this.profitAmount = profitAmount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getRepaymentRecordNo() {
		return repaymentRecordNo;
	}

	public void setRepaymentRecordNo(Long repaymentRecordNo) {
		this.repaymentRecordNo = repaymentRecordNo;
	}
}
