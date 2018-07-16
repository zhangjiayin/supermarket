package com.linkwee.web.response.funds.ifast;

import java.util.List;

import com.linkwee.xoss.funds.sdk.ifast.model.OrderListExtends;

public class OrderListExtendsResponse {

	/**
	 * 订单详情数据
	 */
	private List<OrderListExtends> data;
	/**
	 * 数据总数
	 */
	private Integer size;
	
	public List<OrderListExtends> getData() {
		return data;
	}
	public void setData(List<OrderListExtends> data) {
		this.data = data;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
}
