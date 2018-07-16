package com.linkwee.xoss.funds.sdk.ifast.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linkwee.core.jackson.DoubleSerializer;
import com.linkwee.core.jackson.TimestampStringSerializer;

public class OrderList {

	/*
	 * 账户号码
	 */
	private String accountNumber;

	/*
	 * 基金代码，若为转换基金交易，此处返回转出基金代码
	 */
	private String fundCode;

	/*
	 * 基金名称
	 */
	private String fundName;

	/*
	 * 商户订单流水号
	 */
	private String merchantNumber;

	/*
	 * 下单日期
	 */
	@JsonSerialize(using = TimestampStringSerializer.class)
	private String orderDate;

	/*
	 * 组合编码
	 */
	private Integer portfolioId;

	/*
	 * 如果订单为定投计划生成的订单，则返回关联的定投计划；其他则为空值
	 */
	private Integer rspId;

	/*
	 * 申购、认购、定投的订单的总购买金额（包含购买费用）；赎回订单的总赎回金额（包含赎回费用），如赎回订单未确认，返回空值
	 */
	@JsonSerialize(using = DoubleSerializer.class)
	private double transactionAmount;

	/*
	 * 未打折的交易费用。若订单状态尚未确认，返回的是估算费用；若已确认成功，则返回的是实际费用
	 */
	@JsonSerialize(using = DoubleSerializer.class)
	private double transactionCharge;

	/*
	 * 交易日期。为奕丰向基金公司下单的日期，如为1950-01-01或者空值，则订单尚未下单成功（向奕丰系统）
	 */
	@JsonSerialize(using = TimestampStringSerializer.class)
	private String transactionDate;

	/*
	 * 未打折的交易费率。单位为1%，2位小数，如1.50，即表示1.50%与订单状态尚未确认，返回的是估算费率；若已确认成功，则返回的是实际费率
	 */
	@JsonSerialize(using = DoubleSerializer.class)
	private double transactionRate;

	/*
	 * 交易状态(failure：支付失败； received：下单成功； priced：确认成功；
	 * completed：交易成功；ipo.processing：认购确认成功；
	 * pending.payment：等待付款；canceling：撤单中；canceled：已撤单；pending./*
	 * void：等待退款；void：交易失败；payment：支付过程中的过渡状态，前台请过滤这个状态)
	 */
	private String transactionStatus;

	/*
	 * buy：申购；ipo：认购；sell：赎回；rsp：定投；dividend：红利再投；intra.swtich：同公司转换；inter.switch
	 * 跨公司转换；rapid.sell：快速取现；sell.force：强制赎回；units.increment：份额强增；units.decrement
	 * ：份额强减
	 */
	private String transactionType;

	/*
	 * 交易份额；如申购、认购、定投的订单尚未确认，返回空值；如赎回订单，则返回赎回份额
	 */
	@JsonSerialize(using = DoubleSerializer.class)
	private double transactionUnit;

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getFundCode() {
		return fundCode;
	}

	public void setFundCode(String fundCode) {
		this.fundCode = fundCode;
	}

	public String getFundName() {
		return fundName;
	}

	public void setFundName(String fundName) {
		this.fundName = fundName;
	}

	public String getMerchantNumber() {
		return merchantNumber;
	}

	public void setMerchantNumber(String merchantNumber) {
		this.merchantNumber = merchantNumber;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public Integer getPortfolioId() {
		return portfolioId;
	}

	public void setPortfolioId(Integer portfolioId) {
		this.portfolioId = portfolioId;
	}

	public Integer getRspId() {
		return rspId;
	}

	public void setRspId(Integer rspId) {
		this.rspId = rspId;
	}

	public double getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public double getTransactionCharge() {
		return transactionCharge;
	}

	public void setTransactionCharge(double transactionCharge) {
		this.transactionCharge = transactionCharge;
	}

	public String getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}

	public double getTransactionRate() {
		return transactionRate;
	}

	public void setTransactionRate(double transactionRate) {
		this.transactionRate = transactionRate;
	}

	public String getTransactionStatus() {
		return transactionStatus;
	}

	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public double getTransactionUnit() {
		return transactionUnit;
	}

	public void setTransactionUnit(double transactionUnit) {
		this.transactionUnit = transactionUnit;
	}
}
