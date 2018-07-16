package com.linkwee.web.service;

import com.linkwee.api.request.funds.ifast.BatchGetFundExtendsRequest;
import com.linkwee.api.request.funds.ifast.FundDetailGotoRequest;
import com.linkwee.api.request.funds.ifast.GotoBaseRequest;
import com.linkwee.api.request.funds.ifast.HoldingsStatisticRequest;
import com.linkwee.api.response.funds.ifast.BaseDefinedResponse;
import com.linkwee.api.response.funds.ifast.FundDetailGotoResponse;
import com.linkwee.api.response.funds.ifast.GotoBaseResponse;
import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.xoss.api.AppRequestHead;

public interface IfastFundService {

	/**
	 * 精选基金
	 * @param batchGetFundRequest
	 * @return
	 */
	BaseResponse fundSift(BatchGetFundExtendsRequest batchGetFundExtendsRequest);
	
	/**
	 * 批量获取基金信息
	 * @param batchGetFundRequest
	 */
	BaseResponse batchGetFundInfo(BatchGetFundExtendsRequest batchGetFundExtendsRequest);
	
	/**
	 * 获取账户持有总资产
	 * @param ifastBaseRequest
	 */
	BaseResponse getHoldingsStatistic(HoldingsStatisticRequest holdingsStatisticRequest);
	
//	/**
//	 * 获取账户持有资产
//	 * @param getInvestorHoldingsRequest
//	 * @return
//	 */
//	BaseResponse getInvestorHoldings(GetInvestorHoldingsRequest getInvestorHoldingsRequest);
	
//	/**
//	 * 获取账户持有资产-只反回持有list
//	 * @param getInvestorHoldingsRequest
//	 * @return
//	 */
//	BaseResponse getInvestorHoldingsNew(GetInvestorHoldingsRequest getInvestorHoldingsRequest);
//	
//	/**
//	 * 获取用户的订单详情
//	 * @param getInvestorOrderInfoRequest
//	 * @return
//	 */
//	BaseResponse getInvestorOrderInfo(GetInvestorOrderInfoRequest getInvestorOrderInfoRequest);
//
//	/**
//	 * 奕丰基金注册
//	 * @param quickCustomerMigrationRequest
//	 * @return
//	 */
//	BaseResponse quickCustomerMigration(QuickCustomerMigrationRequest quickCustomerMigrationRequest);

	/**
	 * 基金首页跳转
	 * @param gotoBaseRequest
	 * @return
	 */
	GotoBaseResponse gotoIndex(GotoBaseRequest gotoBaseRequest);

	/**
	 * 基金详情跳转
	 * @param fundDetailGotoRequest
	 * @return
	 */
	FundDetailGotoResponse gotoFundDetail(FundDetailGotoRequest fundDetailGotoRequest);

	/**
	 * 基金个人资产页跳转
	 * @param gotoBaseRequest
	 * @return
	 */
	GotoBaseResponse gotoAccount(GotoBaseRequest gotoBaseRequest);

	/**
	 * 获取基金基本配置信息
	 * @return
	 */
	BaseDefinedResponse baseDefined(AppRequestHead head);
//
//	/**
//	 * 获取用户的订单列表
//	 * @param getOrderListRequest
//	 * @return
//	 */
//	BaseResponse getOrderList(GetOrderListRequest getOrderListRequest);
//	
//	/**
//	 * 是否有订单
//	 * @param getOrderListRequest
//	 * @return
//	 */
//	boolean hasOrder(GetOrderListRequest getOrderListRequest);
}
