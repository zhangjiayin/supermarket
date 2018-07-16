package com.linkwee.api.response.funds.ifast;


public class CustomerMigrationResponse extends IfastAccountBaseResponse{

	/**
	 * 支付方式代码
	 */
	private Integer investorPayId;
	
	public Integer getInvestorPayId() {
		return investorPayId;
	}
	public void setInvestorPayId(Integer investorPayId) {
		this.investorPayId = investorPayId;
	}
}
