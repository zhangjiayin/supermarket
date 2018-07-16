package com.linkwee.web.request.orgInfo;

import javax.validation.constraints.NotNull;

public class OrgUrlSkipParameterRequest {
	/**
	 * 平台编码
	 */
	@NotNull(message="平台编码不能为空")
	private String orgNo;
	
	/**
	 * 第三方产品id
	 */
	private String thirdProductId;
	
	/**
	 * 跳转类型 0-普通跳转（仅返回参数） 1-跳转到产品返回参数带url（兼容老版本）此时thirdProductId不能为空  2-跳转到个人中心返回参数带url（兼容老版本）
	 */
	private Integer urlSkipType = 0;

	public String getOrgNo() {
		return orgNo;
	}

	public void setOrgNo(String orgNo) {
		this.orgNo = orgNo;
	}

	public String getThirdProductId() {
		return thirdProductId;
	}

	public void setThirdProductId(String thirdProductId) {
		this.thirdProductId = thirdProductId;
	}

	public Integer getUrlSkipType() {
		return urlSkipType;
	}

	public void setUrlSkipType(Integer urlSkipType) {
		this.urlSkipType = urlSkipType;
	}
}
