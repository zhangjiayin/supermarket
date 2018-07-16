package com.linkwee.api.response.cim;

import com.linkwee.core.base.BaseEntity;
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
public class CimSunburnResponse extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	private String totalAmt;//累计返现金额
	
	public String getTotalAmt() {
		return totalAmt;
	}

	public void setTotalAmt(String totalAmt) {
		this.totalAmt = totalAmt;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}