package com.linkwee.web.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 import java.lang.Integer;
 import java.lang.String;
 import java.math.BigDecimal;
 import java.util.Date;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2018年03月21日 18:23:57
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class CimJfqzPushRecord implements Serializable {
	
	private static final long serialVersionUID = -6877893151513322301L;
	
    /**
     *自增长主键
     */
	private Integer id;
	
    /**
     *用户id
     */
	private String userId;
	
    /**
     *下单金额(购买本金)
     */
	private BigDecimal amount;
	
	 /**
     *预期收益
     */
	private BigDecimal profit;
	
    /**
     *支付金额
     */
	private BigDecimal payAmount;
	
    /**
     *兑付金额(回款金额)
     */
	private BigDecimal earnings;
	
    /**
     *产品Id
     */
	private String productId;
	
    /**
     *产品期号
     */
	private String issuePreiod;
	
    /**
     *利率
     */
	private BigDecimal rate;
	
    /**
     *预期收益
     */
	private BigDecimal expctedEarning;
	
    /**
     *封闭期（天）
     */
	private String period;
	
    /**
     *订单号(交易编号)
     */
	private String orderNo;
	
    /**
     *续投订单号
     */
	private String subOrderNo;
	
    /**
     *支付银行名称
     */
	private String bankName;
	
    /**
     *银行卡号
     */
	private String bankCardNo;
	
    /**
     *到期处理方式(1到期退出2本金续投3本息续投)
     */
	private String exprieProcessMode;
	
    /**
     *计息时间(为空可能是掉单了)
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date interestTime;
	
    /**
     *到期时间(为空可能是掉单了)
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date redemptionTime;
	
    /**
     *用户下单时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date spendTime;
	
    /**
     *兑付时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date cashTime;
	
    /**
     *创建时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date createTime;
	
    /**
     *修改时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date updateTime;
	
    /**
     *编码(001订单创建002订单支付003订单兑付)
     */
	private String code;
	
	/**
	 * 状态
	 */
	private String updateStatus;
	
	/**
	 * 投资记录ID
	 */
	private String investId;
	
	/**
	 * 回款记录ID
	 */
	private String repaymentId;
	
	/**
	 * 备注
	 */
	private String remark;
	

	public String getRepaymentId() {
		return repaymentId;
	}

	public void setRepaymentId(String repaymentId) {
		this.repaymentId = repaymentId;
	}

	public BigDecimal getExpctedEarning() {
		return expctedEarning;
	}

	public void setExpctedEarning(BigDecimal expctedEarning) {
		this.expctedEarning = expctedEarning;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public BigDecimal getProfit() {
		return profit;
	}

	public void setProfit(BigDecimal profit) {
		this.profit = profit;
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

	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return id;
	}
	
	public void setUserId(String userId){
		this.userId = userId;
	}
	
	public String getUserId(){
		return userId;
	}
	
	public void setAmount(BigDecimal amount){
		this.amount = amount;
	}
	
	public BigDecimal getAmount(){
		return amount;
	}
	
	public void setPayAmount(BigDecimal payAmount){
		this.payAmount = payAmount;
	}
	
	public BigDecimal getPayAmount(){
		return payAmount;
	}
	
	public void setEarnings(BigDecimal earnings){
		this.earnings = earnings;
	}
	
	public BigDecimal getEarnings(){
		return earnings;
	}
	
	public void setProductId(String productId){
		this.productId = productId;
	}
	
	public String getProductId(){
		return productId;
	}
	
	public void setIssuePreiod(String issuePreiod){
		this.issuePreiod = issuePreiod;
	}
	
	public String getIssuePreiod(){
		return issuePreiod;
	}
	
	public void setRate(BigDecimal rate){
		this.rate = rate;
	}
	
	public BigDecimal getRate(){
		return rate;
	}
	
	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public void setOrderNo(String orderNo){
		this.orderNo = orderNo;
	}
	
	public String getOrderNo(){
		return orderNo;
	}
	
	public void setSubOrderNo(String subOrderNo){
		this.subOrderNo = subOrderNo;
	}
	
	public String getSubOrderNo(){
		return subOrderNo;
	}
	
	public void setBankName(String bankName){
		this.bankName = bankName;
	}
	
	public String getBankName(){
		return bankName;
	}
	
	public void setBankCardNo(String bankCardNo){
		this.bankCardNo = bankCardNo;
	}
	
	public String getBankCardNo(){
		return bankCardNo;
	}
	
	public String getExprieProcessMode() {
		return exprieProcessMode;
	}

	public void setExprieProcessMode(String exprieProcessMode) {
		this.exprieProcessMode = exprieProcessMode;
	}

	public void setInterestTime(Date interestTime){
		this.interestTime = interestTime;
	}
	
	public Date getInterestTime(){
		return interestTime;
	}
	
	public void setRedemptionTime(Date redemptionTime){
		this.redemptionTime = redemptionTime;
	}
	
	public Date getRedemptionTime(){
		return redemptionTime;
	}
	
	public void setSpendTime(Date spendTime){
		this.spendTime = spendTime;
	}
	
	public Date getSpendTime(){
		return spendTime;
	}
	
	public void setCashTime(Date cashTime){
		this.cashTime = cashTime;
	}
	
	public Date getCashTime(){
		return cashTime;
	}
	
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	
	public Date getCreateTime(){
		return createTime;
	}
	
	public void setUpdateTime(Date updateTime){
		this.updateTime = updateTime;
	}
	
	public Date getUpdateTime(){
		return updateTime;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	
}

