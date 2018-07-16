package com.linkwee.api.response.funds.ifast;

public class FundDetailGotoResponse extends GotoBaseResponse{

	/**
	 * 基金code
	 */
	private String productCode;

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
}
