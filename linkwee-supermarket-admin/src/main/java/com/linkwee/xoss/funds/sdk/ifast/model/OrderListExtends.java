package com.linkwee.xoss.funds.sdk.ifast.model;

import com.linkwee.core.util.EnumUtils;
import com.linkwee.xoss.funds.sdk.ifast.enums.TransactionStatusEnums;
import com.linkwee.xoss.funds.sdk.ifast.enums.TransactionTypeEnums;

public class OrderListExtends extends OrderList {

	/**
	 * 交易类型信息
	 */
	private String transactionTypeMsg;

	/**
	 * 交易状态信息
	 */
	private String transactionStatusMsg;

	public String getTransactionTypeMsg() {
		transactionTypeMsg = EnumUtils.getMsgByKvmEnumValue(getTransactionType(), TransactionTypeEnums.values());
		return transactionTypeMsg;
	}

	public void setTransactionTypeMsg(String transactionTypeMsg) {
		this.transactionTypeMsg = transactionTypeMsg;
	}

	public String getTransactionStatusMsg() {
		transactionStatusMsg = EnumUtils.getMsgByKvmEnumValue(getTransactionStatus(), TransactionStatusEnums.values());
		return transactionStatusMsg;
	}

	public void setTransactionStatusMsg(String transactionStatusMsg) {
		this.transactionStatusMsg = transactionStatusMsg;
	}
}
