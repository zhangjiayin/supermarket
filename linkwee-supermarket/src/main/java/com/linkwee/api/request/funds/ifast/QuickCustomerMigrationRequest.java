package com.linkwee.api.request.funds.ifast;

public class QuickCustomerMigrationRequest extends CustomerMigrationRequest {
	
    /**
     *机构编码-固定8位编码，不重复字段
     */
	private String orgCode = "OPEN_IFAST_WEB";
	/**
	 * 用户id
	 */
	private String userId;

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
