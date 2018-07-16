package com.linkwee.api.response.acc;

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
/**
* 
* @描述： 实体Bean
* 
* @创建人： chenjl
* 
* @创建时间：2017年02月10日 11:33:01
* 
* Copyright (c) 深圳领会科技有限公司-版权所有
*/
public class LieCainAccountBalance implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	/**
     *T呗奖励余额
     */
	private String accountBalance;
	/**
     *累计收入
     */
    private String incomeSummary;
    /**
     *累计支出
     */
    private String outSummary;
   

	public String getAccountBalance() {
		return accountBalance;
	}


	public void setAccountBalance(String accountBalance) {
		this.accountBalance = accountBalance;
	}


	public String getIncomeSummary() {
		return incomeSummary;
	}


	public void setIncomeSummary(String incomeSummary) {
		this.incomeSummary = incomeSummary;
	}


	public String getOutSummary() {
		return outSummary;
	}


	public void setOutSummary(String outSummary) {
		this.outSummary = outSummary;
	}


	public String toString()
	{
	  return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}