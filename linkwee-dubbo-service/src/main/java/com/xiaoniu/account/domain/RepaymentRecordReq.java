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
 * 还款请求参数.
 *
 * @author lipw
 * @version 1.0
 * @date 2016/5/24 9:36
 */
public class RepaymentRecordReq extends BaseReq {

	private static final long serialVersionUID = 4644953898833805080L;

	/** 还款名称 */
	@NotNull
	private String bisName;

	/** 还款时间  yyyy-MM-dd HH:mm:ss    请按格式传入，方便对账	*/
	@NotNull
	private String bisTime;

	/** 产品代码  */
	@NotNull
	private String productCode;

	/** 产品名称  */
	@NotNull
	private String productName;

	/** 借款单号  */
	@NotNull
	private Long borrowRecordNo;

	/** 实还金额，
	 *  repaymentType为1时该值与repaymentAmount相同
	 * 	repaymentType为2时该值为传入值（即：用户代扣前余额）
	 * */
	@NotNull
	private Long actualRepaymentAmount;

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

	/** 用户操作IP  */
	private String clientIp;

	/** 备注信息 */
	private String remark;

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
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

	public Integer getRepaymentType() {
		return repaymentType;
	}

	public void setRepaymentType(Integer repaymentType) {
		this.repaymentType = repaymentType;
	}

	public Long getActualRepaymentAmount() {
		return actualRepaymentAmount;
	}

	public void setActualRepaymentAmount(Long actualRepaymentAmount) {
		this.actualRepaymentAmount = actualRepaymentAmount;
	}
}
