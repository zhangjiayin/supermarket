package com.linkwee.api.request.cim;

import javax.validation.constraints.NotNull;

public class AllAssessRequest {

	/**
	 * 平台编码
	 */
	@NotNull(message="平台编码不能为空")
	private String orgNo;

	public String getOrgNo() {
		return orgNo;
	}

	public void setOrgNo(String orgNo) {
		this.orgNo = orgNo;
	}
}
