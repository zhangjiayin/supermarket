package com.linkwee.api.response.crm;


public class InsuranceInvestCalendarResponse extends InvestCalendarResponse{

	/**
	 * 保险审核佣金计算状态 0-待计算 1-计算成功 2-计算失败
	 */
	private Integer clearingStatus;

	public Integer getClearingStatus() {
		return clearingStatus;
	}

	public void setClearingStatus(Integer clearingStatus) {
		this.clearingStatus = clearingStatus;
	}
}
