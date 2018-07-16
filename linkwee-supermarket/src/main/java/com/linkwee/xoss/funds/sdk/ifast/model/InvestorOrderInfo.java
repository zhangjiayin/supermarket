package com.linkwee.xoss.funds.sdk.ifast.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linkwee.core.jackson.DoubleSerializer;
import com.linkwee.core.jackson.TimestampStringSerializer;

public class InvestorOrderInfo {

	/**
	 * 账户
	 */
	private String accountNumber;

	/**
	 * 是否可以撤单(Y:是,N:否)
	 */
	private String cancelEnable;

	/**
	 * 撤单日期。如未撤单，则为1950-01-01或者空值
	 */
	@JsonSerialize(using = TimestampStringSerializer.class)
	private String canceledDate;

	/**
	 * 交易完成日期。一般为确认日期+1个自然日，标识交易已完成。如为1950-01-01或者空值，则订单尚未完成。
	 */
	@JsonSerialize(using = TimestampStringSerializer.class)
	private String completedDate;

	/**
	 * 奕丰订单流水号
	 */
	private String contractdouble;

	/**
	 * 折扣率，精确到0.0001，如0.2000，表示20.00%，即2折
	 */
	@JsonSerialize(using = DoubleSerializer.class)
	private double discountRate;

	/**
	 * 折扣后的交易费用。与订单状态尚未确认（priced、completed、ipo.processing），返回的是估算费用；若已确认成功，
	 * 则返回的是实际费用
	 */
	@JsonSerialize(using = DoubleSerializer.class)
	private double discountTransactionCharge;

	/**
	 * 基金代码
	 */
	private String fundCode;

	/**
	 * 基金名称
	 */
	private String fundName;

	/**
	 * 交易代码.当为空时代表现金钱包交易。
	 */
	private Integer investorPayId;

	/**
	 * 商户订单流水号
	 */
	private String merchantdouble;

	/**
	 * 下单日期
	 */
	@JsonSerialize(using = TimestampStringSerializer.class)
	private String orderDate;

	/**
	 * 交易方式.0=银行卡，1=现金钱包支付
	 */
	private Integer payMethod;

	/**
	 * 组合编码
	 */
	private Integer portfolioId;

	/**
	 * 确认日期。基金公司确认订单的日期，也是份额增加或减少的日期。如为1950-01-01或者空值，则订单尚未确认
	 */
	@JsonSerialize(using = TimestampStringSerializer.class)
	private String pricedDate;

	/**
	 * 如支付失败的情况，这里返回失败的原因。一般为空值
	 */
	private String reason;

	/**
	 * 如果订单为定投计划生成的订单，则返回关联的定投计划；其他则为空值
	 */
	private Integer rspId;

	/**
	 * 清算日期。为奕丰和基金公司清算资金的日期。如为1950-01-01或者空值，则订单尚未清算。
	 */
	@JsonSerialize(using = TimestampStringSerializer.class)
	private String settlementDate;

	/**
	 * 转入基金代码
	 */
	private String switchInFundCode;

	/**
	 * 转入基金名称
	 */
	private String switchInFundName;

	/**
	 * 申购、认购、定投的订单的总购买金额（包含购买费用）；赎回订单的总赎回金额（包含赎回费用），如赎回订单未确认，返回空值
	 */
	@JsonSerialize(using = DoubleSerializer.class)
	private double transactionAmount;

	/**
	 * 未打折的交易费用。与订单状态尚未确认（priced、completed、ipo.processing），返回的是估算费用；若已确认成功，
	 * 则返回的是实际费用
	 */
	@JsonSerialize(using = DoubleSerializer.class)
	private double transactionCharge;

	/**
	 * 交易日期。为奕丰向基金公司下单的日期，如为1950-01-01或者空值，则订单尚未下单成功（向奕丰系统）
	 */
	@JsonSerialize(using = TimestampStringSerializer.class)
	private String transactionDate;

	/**
	 * 未打折的交易费率。单位为1%，2位小数，如1.50，即表示1.50%与订单状态尚未确认（priced、completed、ipo.
	 * processing），返回的是估算费率；若已确认成功，则返回的是实际费率
	 */
	@JsonSerialize(using = DoubleSerializer.class)
	private double transactionRate;

	/**
	 * 交易状态(failure：支付失败； received：下单成功； priced：确认成功；
	 * completed：交易成功；ipo.processing：认购确认成功；
	 * pending.payment：等待付款；canceling：撤单中；canceled：已撤单；pending.//v**
	 * id：等待退款；void：交易失败；payment：支付过程中的过渡状态，前台请过滤这个状态)
	 */
	private String transactionStatus;

	/**
	 * 交易类型(buy：申购；ipo：认购；sell：赎回；rsp：定投；dividend：红利再投；intra.swtich：同公司转换；inter.
	 * switch：跨公司转换；rapid.sell：快速取现)
	 */
	private String transactionType;

	/**
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

	public String getCancelEnable() {
		return cancelEnable;
	}

	public void setCancelEnable(String cancelEnable) {
		this.cancelEnable = cancelEnable;
	}

	public String getCanceledDate() {
		return canceledDate;
	}

	public void setCanceledDate(String canceledDate) {
		this.canceledDate = canceledDate;
	}

	public String getCompletedDate() {
		return completedDate;
	}

	public void setCompletedDate(String completedDate) {
		this.completedDate = completedDate;
	}

	public String getContractdouble() {
		return contractdouble;
	}

	public void setContractdouble(String contractdouble) {
		this.contractdouble = contractdouble;
	}

	public double getDiscountRate() {
		return discountRate;
	}

	public void setDiscountRate(double discountRate) {
		this.discountRate = discountRate;
	}

	public double getDiscountTransactionCharge() {
		return discountTransactionCharge;
	}

	public void setDiscountTransactionCharge(double discountTransactionCharge) {
		this.discountTransactionCharge = discountTransactionCharge;
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

	public Integer getInvestorPayId() {
		return investorPayId;
	}

	public void setInvestorPayId(Integer investorPayId) {
		this.investorPayId = investorPayId;
	}

	public String getMerchantdouble() {
		return merchantdouble;
	}

	public void setMerchantdouble(String merchantdouble) {
		this.merchantdouble = merchantdouble;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public Integer getPayMethod() {
		return payMethod;
	}

	public void setPayMethod(Integer payMethod) {
		this.payMethod = payMethod;
	}

	public Integer getPortfolioId() {
		return portfolioId;
	}

	public void setPortfolioId(Integer portfolioId) {
		this.portfolioId = portfolioId;
	}

	public String getPricedDate() {
		return pricedDate;
	}

	public void setPricedDate(String pricedDate) {
		this.pricedDate = pricedDate;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Integer getRspId() {
		return rspId;
	}

	public void setRspId(Integer rspId) {
		this.rspId = rspId;
	}

	public String getSettlementDate() {
		return settlementDate;
	}

	public void setSettlementDate(String settlementDate) {
		this.settlementDate = settlementDate;
	}

	public String getSwitchInFundCode() {
		return switchInFundCode;
	}

	public void setSwitchInFundCode(String switchInFundCode) {
		this.switchInFundCode = switchInFundCode;
	}

	public String getSwitchInFundName() {
		return switchInFundName;
	}

	public void setSwitchInFundName(String switchInFundName) {
		this.switchInFundName = switchInFundName;
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
