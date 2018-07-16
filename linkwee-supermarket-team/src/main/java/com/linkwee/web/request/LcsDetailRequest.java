package com.linkwee.web.request;


public class LcsDetailRequest{

	/**
	 * 电话号码或姓名
	 */
	private String mobileOrName;
	/**
	 * 合伙人
	 */
	private String salesOrgId;

	public String getMobileOrName() {
		return mobileOrName;
	}

	public void setMobileOrName(String mobileOrName) {
		this.mobileOrName = mobileOrName;
	}

	public String getSalesOrgId() {
		return salesOrgId;
	}

	public void setSalesOrgId(String salesOrgId) {
		this.salesOrgId = salesOrgId;
	}
}
