package com.linkwee.api.response.acc;

import java.io.Serializable;

import com.linkwee.web.model.acc.AccountBalanceListResp;
import com.linkwee.xoss.util.WebUtil;
/**
* 
* @描述： 实体Bean
* 
* @创建人： chenjl
* 
* @创建时间：2018年01月29日 17:10:52
* 
* Copyright (c) 深圳领会科技有限公司-版权所有
*/
public class AccountBalanceListResponse implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	
	public AccountBalanceListResponse() {
	}
	
	public AccountBalanceListResponse(AccountBalanceListResp obj) {
		WebUtil.initObj(this, obj);
	}
	
	/**
     *明细类型
     */
	private String typeName;
	/**
     *金额
     */
	private String amount;
	/**
     *描述
     */
	private String remark;
	/**
     *交易时间
     */
	private String transDate;
	
	/**
     *提现手续费 
     */
	private String fee;
	
	
	public String getFee() {
		return fee;
	}

	public void setFee(String fee) {
		this.fee = fee;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getTransDate() {
		return transDate;
	}

	public void setTransDate(String transDate) {
		this.transDate = transDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}