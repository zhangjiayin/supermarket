package com.linkwee.api.response.crm;

import com.linkwee.core.base.BaseEntity;

/**
 * 我的 首页 基金
 * 
 * @Date 2017年10月17日 下午5:36:22
 */
public class CfplannerPersonFund extends BaseEntity {
	private static final long serialVersionUID = 7581132087714889997L;

	/**
	 * 基金
	 */
	private String fundAmount;

	public String getFundAmount() {
		return fundAmount;
	}

	public void setFundAmount(String fundAmount) {
		this.fundAmount = fundAmount;
	}
	

}
