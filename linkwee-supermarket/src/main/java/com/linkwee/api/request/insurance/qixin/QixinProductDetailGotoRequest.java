package com.linkwee.api.request.insurance.qixin;

public class QixinProductDetailGotoRequest extends QixinGotoBaseRequest {
	
	/**
	 * 产品方案代码
	 */
	private String caseCode;

	public String getCaseCode() {
		return caseCode;
	}

	public void setCaseCode(String caseCode) {
		this.caseCode = caseCode;
	}
}
