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
import java.io.Serializable;

/**
 * 代扣成功请求参数.
 *
 * @author lipw
 * @version 1.0
 * @date 2016/9/1 10:11
 */
public class WithholdSuccessReq extends BaseReq {

	private static final long serialVersionUID = -6175057834742169149L;

	/** 代扣单号，多个以逗号隔开 */
	@NotNull
	private String withholdTradeNos;

	/** 代扣单号，多个以逗号隔开 */
	@NotNull
	private Long withholdTotalAmount;

	/** 金额还款方式 1-全额余额还款，2-部分余额还款 */
	@NotNull
	private Integer repaymentType;

	public String getWithholdTradeNos() {
		return withholdTradeNos;
	}

	public void setWithholdTradeNos(String withholdTradeNos) {
		this.withholdTradeNos = withholdTradeNos;
	}

	public Long getWithholdTotalAmount() {
		return withholdTotalAmount;
	}

	public void setWithholdTotalAmount(Long withholdTotalAmount) {
		this.withholdTotalAmount = withholdTotalAmount;
	}

	public Integer getRepaymentType() {
		return repaymentType;
	}

	public void setRepaymentType(Integer repaymentType) {
		this.repaymentType = repaymentType;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
