package com.linkwee.api.response.funds.ifast;

import java.util.List;

import com.linkwee.xoss.funds.sdk.ifast.model.InvestorOrderInfoExtends;

public class InvestorOrderInfoExtendsResponse {
	
	/**
	 * 订单详情数据
	 */
	private List<InvestorOrderInfoExtends> data;
	/**
	 * 数据总数
	 */
	private Integer size;
	
	public List<InvestorOrderInfoExtends> getData() {
		return data;
	}
	public void setData(List<InvestorOrderInfoExtends> data) {
		this.data = data;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
}
