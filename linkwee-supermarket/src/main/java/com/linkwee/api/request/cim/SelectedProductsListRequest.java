package com.linkwee.api.request.cim;

import com.linkwee.core.base.api.PaginatorRequest;

public class SelectedProductsListRequest  extends PaginatorRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 1-日进斗金 2-年年有余
	 */
	private Integer cateId;
	/**
	 * 是否拥有灰度权限 true=拥有灰度权限  false=没有灰度权限
	 */
	private Boolean ifHaveGray;	
	/**
	 * APP类型  investor,channel
	 */
	private String appKind;
	
	public Integer getCateId() {
		return cateId;
	}
	public void setCateId(Integer cateId) {
		this.cateId = cateId;
	}
	public Boolean getIfHaveGray() {
		return ifHaveGray;
	}
	public void setIfHaveGray(Boolean ifHaveGray) {
		this.ifHaveGray = ifHaveGray;
	}
	public String getAppKind() {
		return appKind;
	}
	public void setAppKind(String appKind) {
		this.appKind = appKind;
	}
}
