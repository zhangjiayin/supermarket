package com.linkwee.api.response.funds.ifast;

import java.util.List;

import com.linkwee.xoss.funds.sdk.ifast.model.Investorholdings;

public class InvestorholdingsResponse {

	/**
	 * 投资记录数据
	 */
	private List<Investorholdings> data;
	
	/**
	 * 数据总数
	 */
	private Integer size;

	public List<Investorholdings> getData() {
		return data;
	}

	public void setData(List<Investorholdings> data) {
		this.data = data;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}
}
