package com.linkwee.api.response.funds.ifast;

import java.util.List;

import com.linkwee.xoss.funds.sdk.ifast.model.InvestorOrderInfo;

public class InvestorOrderInfoResponse {
	
	/**
	 * 订单详情数据
	 */
	private List<InvestorOrderInfo> data;
	/**
	 * 数据总数
	 */
	private Integer size;
	
	public List<InvestorOrderInfo> getData() {
		return data;
	}
	public void setData(List<InvestorOrderInfo> data) {
		this.data = data;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
}
