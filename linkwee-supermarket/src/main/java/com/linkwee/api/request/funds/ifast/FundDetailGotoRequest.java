package com.linkwee.api.request.funds.ifast;

public class FundDetailGotoRequest extends GotoBaseRequest {

	/**
	 * 基金code
	 */
	private String productCode;
	/**
	 * 用户id
	 */
	private String userId;

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
