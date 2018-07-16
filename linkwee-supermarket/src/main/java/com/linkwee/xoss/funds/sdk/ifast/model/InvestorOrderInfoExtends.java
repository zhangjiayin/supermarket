package com.linkwee.xoss.funds.sdk.ifast.model;

import com.linkwee.core.util.EnumUtils;
import com.linkwee.xoss.funds.sdk.ifast.enums.PayMethodEnums;
import com.linkwee.xoss.funds.sdk.ifast.enums.TransactionStatusEnums;
import com.linkwee.xoss.funds.sdk.ifast.enums.TransactionTypeEnums;

public class InvestorOrderInfoExtends extends InvestorOrderInfo {

	/**
	 * 交易方式信息
	 */
	private String payMethodMsg;

	/**
	 * 交易类型信息
	 */
	private String transactionTypeMsg;

	/**
	 * 交易状态信息
	 */
	private String transactionStatusMsg;

	public String getPayMethodMsg() {
		payMethodMsg = EnumUtils.getMsgByKvmEnumValue(String.valueOf(getPayMethod()), PayMethodEnums.values());
		return payMethodMsg;
	}

	public void setPayMethodMsg(String payMethodMsg) {
		this.payMethodMsg = payMethodMsg;
	}

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
