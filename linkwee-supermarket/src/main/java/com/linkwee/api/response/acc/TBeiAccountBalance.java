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
public class TBeiAccountBalance implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	/**
     *T呗奖励余额
     */
	private String rewardBalance;
	/**
     *奖励收入
     */
    private String rewardIncome;
    /**
     *奖励支出
     */
    private String rewardOut;
   

	public String getRewardBalance() {
		return rewardBalance;
	}


	public void setRewardBalance(String rewardBalance) {
		this.rewardBalance = rewardBalance;
	}


	public String getRewardIncome() {
		return rewardIncome;
	}


	public void setRewardIncome(String rewardIncome) {
		this.rewardIncome = rewardIncome;
	}


	public String getRewardOut() {
		return rewardOut;
	}


	public void setRewardOut(String rewardOut) {
		this.rewardOut = rewardOut;
	}


	public String toString()
	{
	  return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}