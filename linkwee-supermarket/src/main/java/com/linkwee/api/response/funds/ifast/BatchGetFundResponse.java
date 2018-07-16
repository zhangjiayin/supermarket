package com.linkwee.api.response.funds.ifast;

import java.util.List;

import com.linkwee.xoss.funds.sdk.ifast.model.ProductFundInfoExtends;

public class BatchGetFundResponse {
	
	/**
	 * 基金信息
	 */
	private List<ProductFundInfoExtends> data;

	/**
	 * 数据总数
	 */
	private Integer size;

	public List<ProductFundInfoExtends> getData() {
		return data;
	}

	public void setData(List<ProductFundInfoExtends> data) {
		this.data = data;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}
}
