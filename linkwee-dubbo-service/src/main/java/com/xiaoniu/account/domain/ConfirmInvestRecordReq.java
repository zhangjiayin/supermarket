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
 * 确认投资.
 *
 * @author lipw
 * @version 1.0
 * @date 2016/6/1 11:24
 */
public class ConfirmInvestRecordReq extends BaseReq {

	/** 产品代码  */
	@NotNull
	private String productCode;

	/** 产品名称  */
	@NotNull
	private String productName;

	/** 投资金额  */
	@NotNull
	private Long investAmount;

	/** 红包金额 */
	private Long redPaperAmount;

	/** 预期收益  */
	private Long expectProfit;


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

	public Long getInvestAmount() {
		return investAmount;
	}

	public void setInvestAmount(Long investAmount) {
		this.investAmount = investAmount;
	}

	public Long getRedPaperAmount() {
		return redPaperAmount;
	}

	public void setRedPaperAmount(Long redPaperAmount) {
		this.redPaperAmount = redPaperAmount;
	}

	public Long getExpectProfit() {
		return expectProfit;
	}

	public void setExpectProfit(Long expectProfit) {
		this.expectProfit = expectProfit;
	}
}
