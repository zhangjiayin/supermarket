package com.linkwee.api.request.insurance.qixin;

public class QixinGotoBaseRequest {
	
    /**
     *机构编码-固定8位编码，不重复字段
     */
	private String orgCode = "OPEN_QIXIN_WEB";
	/**
	 * 用户id
	 */
	private String userId;
	/**
	 * 平台类型
	 */
	private String platform;

	
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
	public String getPlatform() {
		return platform;
	}
	public void setPlatform(String platform) {
		this.platform = platform;
	}
}
