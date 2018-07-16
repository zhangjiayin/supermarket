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
import java.util.Date;

/**
 * 放款请求参数.
 *
 * @author lipw
 * @version 1.0
 * @date 2016/9/13 16:36
 */
public class LendingRecordReq extends BaseReq {

	private static final long serialVersionUID = -8842395054935080327L;

	//借款单号
	@NotNull
	private String partnerBorrowNo;

	//业务名称
	@NotNull
	private String bisName;

	//业务时间
	@NotNull
	private String bisTime;

	//产品编号
	@NotNull
	private String productCode;

	//产品名称
	@NotNull
	private String productName;

	//放款金额
	@NotNull
	private Long lendAmount;

	//管理费
	@NotNull
	private Long mamageFee;

	//备注
	private String remark;

	//客户端IP
	private String clientIp;

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	public String getPartnerBorrowNo() {
		return partnerBorrowNo;
	}

	public void setPartnerBorrowNo(String partnerBorrowNo) {
		this.partnerBorrowNo = partnerBorrowNo;
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

	public Long getLendAmount() {
		return lendAmount;
	}

	public void setLendAmount(Long lendAmount) {
		this.lendAmount = lendAmount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getClientIp() {
		return clientIp;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}

	public Long getMamageFee() {
		return mamageFee;
	}

	public void setMamageFee(Long mamageFee) {
		this.mamageFee = mamageFee;
	}
}
