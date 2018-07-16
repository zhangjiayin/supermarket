package com.linkwee.api.response.insurance.qixin;

public class GotoProductDetailResponse {
	
	/**
	 * 请求地址
	 */
	private String  requestUrl;
	/**
	 * 渠道账号，由慧择为每个渠道分配（唯一）
	 */
	private String partnerId;
	/**
	 * 渠道子账号(当子账号投保时必传)
	 */
	private String subPartnerId;
	/**
	 * 为1 隐藏 cps 头部，0为不隐藏，不传默认为0
	 */
	private Integer hidenav = 0;
	/**
	 * 渠道会员唯一标识（渠道自己的会员的ID）
	 */
	private String partnerUniqKey;
	/**
	 * 平台类型(1H5、2PC、3iOS、4Android)
	 */
	private Integer platform;
	/**
	 * 产品方案代码（由慧择提供，必填）
	 */
	private String caseCode;
	/**
	 * 登录url（没传partnerUniqKey的情况下需要跳转的登录地址，base64加密，不必填）
	 */
	private String toLogin;
	/**
	 * 支付成功后跳转url(base64加密，不必填)
	 */
	private String paySuccess;
	/**
	 * md5(apiKey + partnerId )
	 */
	private String sign;
	
	public String getPartnerId() {
		return partnerId;
	}
	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}
	public String getSubPartnerId() {
		return subPartnerId;
	}
	public void setSubPartnerId(String subPartnerId) {
		this.subPartnerId = subPartnerId;
	}
	public Integer getHidenav() {
		return hidenav;
	}
	public void setHidenav(Integer hidenav) {
		this.hidenav = hidenav;
	}
	public String getPartnerUniqKey() {
		return partnerUniqKey;
	}
	public void setPartnerUniqKey(String partnerUniqKey) {
		this.partnerUniqKey = partnerUniqKey;
	}
	public Integer getPlatform() {
		return platform;
	}
	public void setPlatform(Integer platform) {
		this.platform = platform;
	}
	public String getCaseCode() {
		return caseCode;
	}
	public void setCaseCode(String caseCode) {
		this.caseCode = caseCode;
	}
	public String getToLogin() {
		return toLogin;
	}
	public void setToLogin(String toLogin) {
		this.toLogin = toLogin;
	}
	public String getPaySuccess() {
		return paySuccess;
	}
	public void setPaySuccess(String paySuccess) {
		this.paySuccess = paySuccess;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getRequestUrl() {
		return requestUrl;
	}
	public void setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl;
	}
}
