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
 * 还款冻结.
 *
 * @author lipw
 * @version 1.0
 * @date 2016/9/2 11:31
 */
public class UserPreRepaymentReq extends BaseReq {

	private static final long serialVersionUID = -1094642282329875017L;

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

	/** 业务借款单号  */
	@NotNull
	private String partnerBorrowNo;

	/** 还款总金额  */
	@NotNull
	private Long repayTotalAmount;

	/** 还款本金  */
	@NotNull
	private Long repayPrincipalAmount;

	/** 还款利息  */
	@NotNull
	private Long repayInterestAmount;

	/** 还款罚息 */
	@NotNull
	private Long repayLateInterestAmount;

	/** 违约金额  */
	@NotNull
	private Long repayViolateAmount;

	/** 冻结金额  */
	@NotNull
	private Long repayFreezeAmount;

	/** 金额还款方式 1-余额还款，2-代扣还款 */
	@NotNull
	private Integer repaymentType;

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
		if(this.repayTotalAmount < 0 || this.repayPrincipalAmount < 0
				|| repayLateInterestAmount < 0 || repayFreezeAmount < 0
				|| repayInterestAmount < 0 || repayViolateAmount < 0) {
			return false;
		}
		if (repayTotalAmount != (repayPrincipalAmount + repayInterestAmount
				+ repayLateInterestAmount + repayViolateAmount)) {
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

	public String getPartnerBorrowNo() {
		return partnerBorrowNo;
	}

	public void setPartnerBorrowNo(String partnerBorrowNo) {
		this.partnerBorrowNo = partnerBorrowNo;
	}

	public Long getRepayTotalAmount() {
		return repayTotalAmount;
	}

	public void setRepayTotalAmount(Long repayTotalAmount) {
		this.repayTotalAmount = repayTotalAmount;
	}

	public Long getRepayPrincipalAmount() {
		return repayPrincipalAmount;
	}

	public void setRepayPrincipalAmount(Long repayPrincipalAmount) {
		this.repayPrincipalAmount = repayPrincipalAmount;
	}

	public Long getRepayLateInterestAmount() {
		return repayLateInterestAmount;
	}

	public void setRepayLateInterestAmount(Long repayLateInterestAmount) {
		this.repayLateInterestAmount = repayLateInterestAmount;
	}

	public Long getRepayFreezeAmount() {
		return repayFreezeAmount;
	}

	public void setRepayFreezeAmount(Long repayFreezeAmount) {
		this.repayFreezeAmount = repayFreezeAmount;
	}

	public Integer getRepaymentType() {
		return repaymentType;
	}

	public void setRepaymentType(Integer repaymentType) {
		this.repaymentType = repaymentType;
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

	public Long getRepayInterestAmount() {
		return repayInterestAmount;
	}

	public void setRepayInterestAmount(Long repayInterestAmount) {
		this.repayInterestAmount = repayInterestAmount;
	}

	public Long getRepayViolateAmount() {
		return repayViolateAmount;
	}

	public void setRepayViolateAmount(Long repayViolateAmount) {
		this.repayViolateAmount = repayViolateAmount;
	}
}
