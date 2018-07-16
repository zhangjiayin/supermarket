package com.linkwee.xoss.funds.sdk.ifast.model;

import java.util.List;

public class FundHoldings {

	/**
	 * 数据列表
	 */
	private List<FundHolding> data;
	
	/**
	 * 数据总数
	 */
	private Integer size;

	public List<FundHolding> getData() {
		return data;
	}

	public void setData(List<FundHolding> data) {
		this.data = data;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}
}
