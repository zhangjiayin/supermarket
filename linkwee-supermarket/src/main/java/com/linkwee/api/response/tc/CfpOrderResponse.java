package com.linkwee.api.response.tc;

import com.linkwee.core.base.BaseEntity;
import com.linkwee.core.util.StringUtils;

public class CfpOrderResponse extends BaseEntity{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5274783103681428075L;

	/**
	 * 理财师手机号码
	 */
	private String mobile;
	
	/**
	 * 出单金额
	 */
	private String orderMoney;

	public String getMobile() {
		if(StringUtils.isNotBlank(mobile) && mobile.length() == 11){
			mobile = mobile.substring(0, 3) + "****" + mobile.substring(7);
		}
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getOrderMoney() {
		return orderMoney;
	}

	public void setOrderMoney(String orderMoney) {
		this.orderMoney = orderMoney;
	}
	
}
