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
public class UserRepaymentReq extends BaseReq {

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

	/** 业务还款单号  */
	@NotNull
	private String partnerRepaymentNo;

	/** 业务借款单号  */
	@NotNull
	private String partnerBorrowNo;

	/** 还款金额  */
	@NotNull
	private Long repayAmount;

	/** 还款本金  */
	@NotNull
	private Long repayCapitalAmount;

	/** 还款利息  */
	@NotNull
	private Long repayInterestAmount;

	/** 罚息金额  */
	@NotNull
	private Long repayLateInterestAmount;

	/** 还款期数  */
	@NotNull
	private Integer repayTimes;

	/** 违约金额  */
	@NotNull
	private Long repayViolateAmount;

	/** 用户操作IP  */
	private String clientIp;

	/** 备注信息 */
	private String remark;

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	/**
	 * 检查请求参数金额
	 * @return
	 */
	public Boolean verifyAmount(){
		if(this.repayAmount < 0 || this.repayCapitalAmount < 0
				|| this.repayInterestAmount < 0 || this.repayLateInterestAmount < 0
				|| this.repayViolateAmount < 0 ) {
			return false;
		}
		if (this.repayAmount != (this.repayCapitalAmount + this.repayInterestAmount
				+ this.repayLateInterestAmount + this.repayViolateAmount)) {
			return false;
		}
		return true;
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

	public String getPartnerRepaymentNo() {
		return partnerRepaymentNo;
	}

	public void setPartnerRepaymentNo(String partnerRepaymentNo) {
		this.partnerRepaymentNo = partnerRepaymentNo;
	}

	public String getPartnerBorrowNo() {
		return partnerBorrowNo;
	}

	public void setPartnerBorrowNo(String partnerBorrowNo) {
		this.partnerBorrowNo = partnerBorrowNo;
	}

	public Integer getRepayTimes() {
		return repayTimes;
	}

	public void setRepayTimes(Integer repayTimes) {
		this.repayTimes = repayTimes;
	}

	public Long getRepayAmount() {
		return repayAmount;
	}

	public void setRepayAmount(Long repayAmount) {
		this.repayAmount = repayAmount;
	}

	public Long getRepayCapitalAmount() {
		return repayCapitalAmount;
	}

	public void setRepayCapitalAmount(Long repayCapitalAmount) {
		this.repayCapitalAmount = repayCapitalAmount;
	}

	public Long getRepayInterestAmount() {
		return repayInterestAmount;
	}

	public void setRepayInterestAmount(Long repayInterestAmount) {
		this.repayInterestAmount = repayInterestAmount;
	}

	public Long getRepayLateInterestAmount() {
		return repayLateInterestAmount;
	}

	public void setRepayLateInterestAmount(Long repayLateInterestAmount) {
		this.repayLateInterestAmount = repayLateInterestAmount;
	}

	public Long getRepayViolateAmount() {
		return repayViolateAmount;
	}

	public void setRepayViolateAmount(Long repayViolateAmount) {
		this.repayViolateAmount = repayViolateAmount;
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
