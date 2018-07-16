package com.linkwee.api.response.acc;

import java.io.Serializable;
import java.util.List;
/**
* 
* @描述： 月份收益统计
* 
* @创建人： chenjl
* 
* @创建时间：2016年07月22日 17:10:52
* 
* Copyright (c) 深圳领会科技有限公司-版权所有
*/
public class MonthProfixStatisticsResponseNew implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	/**
     *账户总收益
     */
	private String totalProfix;
	
	/**
	 * 待发放金额
	 */
	private String waitGrantAmount;
	
	/**
     * 已发放金额
     */
	private String GrantedAmount;
	
	/**
	 * 收益
	 */
	private List<ProfixTypeListRespsone> profixList;

	public String getTotalProfix() {
		return totalProfix;
	}

	public void setTotalProfix(String totalProfix) {
		this.totalProfix = totalProfix;
	}

	public List<ProfixTypeListRespsone> getProfixList() {
		return profixList;
	}

	public void setProfixList(List<ProfixTypeListRespsone> profixList) {
		this.profixList = profixList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getWaitGrantAmount() {
		return waitGrantAmount;
	}

	public void setWaitGrantAmount(String waitGrantAmount) {
		this.waitGrantAmount = waitGrantAmount;
	}

	public String getGrantedAmount() {
		return GrantedAmount;
	}

	public void setGrantedAmount(String grantedAmount) {
		GrantedAmount = grantedAmount;
	}
	

}