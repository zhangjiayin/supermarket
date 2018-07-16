package com.linkwee.api.request.insurance.qixin;

import com.linkwee.core.base.api.PaginatorRequest;

public class QixinInsuranceListRequest extends PaginatorRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
    /**
     *机构编码-固定8位编码，不重复字段
     */
	private String orgCode = "OPEN_QIXIN_WEB";

	/**
	 * 保险分类
	 */
	private String insuranceCategory;
	
	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getInsuranceCategory() {
		return insuranceCategory;
	}

	public void setInsuranceCategory(String insuranceCategory) {
		this.insuranceCategory = insuranceCategory;
	}

}
