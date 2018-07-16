package com.linkwee.openApi.request;

import java.io.Serializable;
import java.math.BigDecimal;

import org.hibernate.validator.constraints.NotBlank;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
/**
 * 投资记录
 * @author ch
 * @serial 2018-03-20 17:32:02
 *
 */
public class JfqzInvestRecord implements Serializable{
	private static final long serialVersionUID = 2985070032905812928L;

	/**
	 * 订单金额（元）
	 */
	@NotBlank(message="订单金额不能为空")
	private String amount;
	
	/**
	 * 到期处理方式，1：到期退出2:本金续投3：本息续投
	 */
	@NotBlank(message="到期处理方式不能为空")
	private String exprieProcessMode;
	
	/**
	 * 订单号
	 */
	@NotBlank(message="订单号不能为空")
	private String orderNo;
	
	/**
	 * 封闭期（天）
	 */
	private String period;
	
	/**
	 * 利率
	 */
	@NotBlank(message="利率不能为空")
	private String profit;
	
	
	
	/**
	 * 产品ID
	 */
	@NotBlank(message="产品ID不能为空")
	private String productId;
	
	/**
	 * 产品期号
	 */
	@NotBlank(message="产品期号不能为空")
	private String issuePeriod;
	
	/**
	 * 到期时间、时间戳
	 */
	private Long redemptionTime;
	
	
	/**
	 * 用户下单时间
	 */
	private Long spendTime;

	/**
	 * 用户id
	 */
	private String userId;
	
	/**
	 * 支付金额
	 */
	private BigDecimal payAmount;
	
	/**
	 * 产品期望收益
	 */
	private BigDecimal expctedEarning;
	
	/**
	 * 计息时间
	 */
	private Long interestTime;
	
	/**
	 * 支付银行名称
	 */
	private String bankName;
	/**
	 * 支付银行卡号
	 */
	private String bankCardNo;
	
	/**
	 * 银行卡尾号
	 */
	private String subBankCardNo;
	
	/**
	 * 兑付时间
	 */
	private Long cashTime;
	
	/**
	 * 兑付金额
	 */
	private BigDecimal earnings;
	
	/**
	 * 状态
	 */
	private String updateStatus;
	
	/**
	 * 投资记录ID
	 */
	private String investId;
	
	/**
	 * 
	 */
	private String channelOrderNo;
	
	
	public String getChannelOrderNo() {
		return channelOrderNo;
	}

	public void setChannelOrderNo(String channelOrderNo) {
		this.channelOrderNo = channelOrderNo;
	}

	public BigDecimal getExpctedEarning() {
		return expctedEarning;
	}

	public void setExpctedEarning(BigDecimal expctedEarning) {
		this.expctedEarning = expctedEarning;
	}

	public String getUpdateStatus() {
		return updateStatus;
	}

	public void setUpdateStatus(String updateStatus) {
		this.updateStatus = updateStatus;
	}

	public String getInvestId() {
		return investId;
	}

	public void setInvestId(String investId) {
		this.investId = investId;
	}

	public Long getCashTime() {
		return cashTime;
	}

	public void setCashTime(Long cashTime) {
		this.cashTime = cashTime;
	}

	public BigDecimal getEarnings() {
		return earnings;
	}

	public void setEarnings(BigDecimal earnings) {
		this.earnings = earnings;
	}

	public String getSubBankCardNo() {
		return subBankCardNo;
	}

	public void setSubBankCardNo(String subBankCardNo) {
		this.subBankCardNo = subBankCardNo;
	}

	public BigDecimal getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(BigDecimal payAmount) {
		this.payAmount = payAmount;
	}

	public Long getInterestTime() {
		return interestTime;
	}

	public void setInterestTime(Long interestTime) {
		this.interestTime = interestTime;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankCardNo() {
		return bankCardNo;
	}

	public void setBankCardNo(String bankCardNo) {
		this.bankCardNo = bankCardNo;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getExprieProcessMode() {
		return exprieProcessMode;
	}

	public void setExprieProcessMode(String exprieProcessMode) {
		this.exprieProcessMode = exprieProcessMode;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getProfit() {
		return profit;
	}

	public void setProfit(String profit) {
		this.profit = profit;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public Long getRedemptionTime() {
		return redemptionTime;
	}

	public void setRedemptionTime(Long redemptionTime) {
		this.redemptionTime = redemptionTime;
	}

	public Long getSpendTime() {
		return spendTime;
	}

	public void setSpendTime(Long spendTime) {
		this.spendTime = spendTime;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getIssuePeriod() {
		return issuePeriod;
	}

	public void setIssuePeriod(String issuePeriod) {
		this.issuePeriod = issuePeriod;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}

	
}
