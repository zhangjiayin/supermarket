package com.linkwee.web.request;

import com.linkwee.core.base.BaseEntity;

public class ProductsListRequest extends BaseEntity{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 产品名称
     */
	private String productName;
	
    /**
     * 产品状态
     */
	private Integer status;
	/**
	 * 审核状态
	 */
	private Integer auditStatus;
    /**
     *机构编码-固定50位编码，不重复字段
     */
	private String orgNumber;
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getAuditStatus() {
		return auditStatus;
	}
	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
	}
	public String getOrgNumber() {
		return orgNumber;
	}
	public void setOrgNumber(String orgNumber) {
		this.orgNumber = orgNumber;
	}
}
