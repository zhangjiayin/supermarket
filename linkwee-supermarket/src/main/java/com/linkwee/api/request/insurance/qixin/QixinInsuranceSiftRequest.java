package com.linkwee.api.request.insurance.qixin;


public class QixinInsuranceSiftRequest{
	
    /**
     *机构编码-固定8位编码，不重复字段
     */
	private String orgCode = "OPEN_QIXIN_WEB";
	/**
	 * 保险方案代码
	 */
	private String caseCode;

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getCaseCode() {
		return caseCode;
	}

	public void setCaseCode(String caseCode) {
		this.caseCode = caseCode;
	}
}
