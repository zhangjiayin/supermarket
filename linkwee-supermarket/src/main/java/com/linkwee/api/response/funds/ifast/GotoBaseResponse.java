package com.linkwee.api.response.funds.ifast;

public class GotoBaseResponse {

	/**
	 * 固定值，LHT， 第三方的编码
	 */
	private String referral = "LHT";
	/**
	 * 固定值  加密模式
	 */
	private String integrationMode = "encryption";
	/**
	 * 账户绑定加密数据
	 */
	private String data;
	/**
	 * 请求地址
	 */
	private String requestUrl;
	
	public String getReferral() {
		return referral;
	}
	public void setReferral(String referral) {
		this.referral = referral;
	}
	public String getIntegrationMode() {
		return integrationMode;
	}
	public void setIntegrationMode(String integrationMode) {
		this.integrationMode = integrationMode;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getRequestUrl() {
		return requestUrl;
	}
	public void setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl;
	}
}
