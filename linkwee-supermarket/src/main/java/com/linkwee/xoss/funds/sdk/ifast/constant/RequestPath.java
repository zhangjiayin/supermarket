package com.linkwee.xoss.funds.sdk.ifast.constant;

public class RequestPath {
	
//	/**
//	 * 客户迁移(注册)
//	 */
//	public static final String QUICK_CUSTOMER_MIGRATION = "/public/investor/quick-customer-migration";
//	/**
//	 * 获取支持的银行
//	 */
//	public static final String FIND_BANK = "/public/investor/find-bank";
	/**
	 * 批量获取基金信息
	 */
	public static final String BATCH_GET_FUND_INFO = "/public/product/batch-get-fund-info";
//	/**
//	 * 获取用户的订单详情
//	 */
//	public static final String GET_INVESTOR_ORDER_INFO = "/public/transaction/get-investor-order-info";
//	/**
//	 * 获取用户的订单列表
//	 */
//	public static final String GET_ORDER_LIST = "/public/transaction/get-order-list";
	/**
	 * 获取账户持有总资产
	 */
	public static final String GET_HOLDINGS_STATISTIC = "/public/transaction/get-holdings-statistic";
//	/**
//	 * 获取账户持有资产
//	 */
//	public static final String GET_INVESTOR_HOLDINGS = "/public/transaction/get-investor-holdings";
//	/**
//	 * 获取银行代码
//	 */
//	public static final String GET_BANK_CODE = "/public/investor/get-bank-code";
	
	/**
	 * 校验用户提供的accountNumber是否注册
	 */
	public static final String VERIFY_INVESTOR_ACCOUNT_NUMBER = "/public/investor/verify-investor-account-number";
}
