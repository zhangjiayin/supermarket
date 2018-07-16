package com.linkwee.api.request.funds.ifast;

public class IfRegisterRequest {
	
    /**
     *机构编码-固定8位编码，不重复字段
     */
	private String orgCode = "OPEN_IFAST_WEB";

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
}
