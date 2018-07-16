package com.linkwee.web.service;

import com.linkwee.web.request.funds.ifast.GetOrderListRequest;


public interface IfastFundService {

	/**
	 * 拉取基金订单列表
	 * @param getOrderListRequest
	 */
	void pullOrderList(GetOrderListRequest getOrderListRequest);

	/**
	 * 拉取基金订单列表状态更新
	 */
	void updateOrderList();
		
}
