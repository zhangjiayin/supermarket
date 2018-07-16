package com.linkwee.api.request.cim;


public class ProductPageListLimitRequest extends ProductPageListRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    /**
     *产品数量
     */
	private Integer limitSize;
	public Integer getLimitSize() {
		return limitSize;
	}
	public void setLimitSize(Integer limitSize) {
		this.limitSize = limitSize;
	}
}
