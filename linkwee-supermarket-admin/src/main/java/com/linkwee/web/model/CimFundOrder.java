package com.linkwee.web.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.annotation.JsonFormat;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2017年10月24日 17:36:45
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class CimFundOrder implements Serializable {
	
	private static final long serialVersionUID = -5264688212922069152L;
	
    /**
     *
     */
	private Integer id;
	
    /**
     *账户号码
     */
	private String accountNumber;
	
    /**
     *基金代码，若为转换基金交易，此处返回转出基金代码
     */
	private String fundCode;
	
    /**
     *基金名称
     */
	private String fundName;
	
    /**
     *商户订单流水号
     */
	private String merchantNumber;
	
    /**
     *下单日期
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date orderDate;
	
    /**
     *组合编码
     */
	private Integer portfolioId;
	
    /**
     *如果订单为定投计划生成的订单，则返回关联的定投计划；其他则为空值
     */
	private Integer rspId;
	
    /**
     *申购、认购、定投的订单的总购买金额（包含购买费用）；赎回订单的总赎回金额（包含赎回费用），如赎回订单未确认，返回空值
     */
	private BigDecimal transactionAmount;
	
    /**
     *未打折的交易费用。若订单状态尚未确认，返回的是估算费用；若已确认成功，则返回的是实际费用
     */
	private BigDecimal transactionCharge;
	
    /**
     *交易日期。为奕丰向基金公司下单的日期，如为1950-01-01或者空值，则订单尚未下单成功（向奕丰系统）
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date transactionDate;
	
    /**
     *未打折的交易费率。单位为1%，2位小数，如1.50，即表示1.50%与订单状态尚未确认，返回的是估算费率；若已确认成功，则返回的是实际费率
     */
	private BigDecimal transactionRate;
	
    /**
     *交易状态(failure：支付失败； received：下单成功； priced：确认成功； completed：交易成功；ipo.processing：认购确认成功； pending.payment：等待付款；canceling：撤单中；canceled：已撤单；pending.void：等待退款；void：交易失败；payment：支付过程中的过渡状态，前台请过滤这个状态)
     */
	private String transactionStatus;
	
    /**
     *buy：申购；ipo：认购；sell：赎回；rsp：定投；dividend：红利再投；intra.swtich：同公司转换；inter.switch：跨公司转换；rapid.sell：快速取现；sell.force：强制赎回；units.increment：份额强增；units.decrement：份额强减
     */
	private String transactionType;
	
    /**
     *
     */
	private String transactionUnit;
	
    /**
     *业务日期（第一次拉取时间）
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date bizTime;
	
    /**
     *更新日期
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date updateTime;
	


	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return id;
	}
	
	public void setAccountNumber(String accountNumber){
		this.accountNumber = accountNumber;
	}
	
	public String getAccountNumber(){
		return accountNumber;
	}
	
	public void setFundCode(String fundCode){
		this.fundCode = fundCode;
	}
	
	public String getFundCode(){
		return fundCode;
	}
	
	public void setFundName(String fundName){
		this.fundName = fundName;
	}
	
	public String getFundName(){
		return fundName;
	}
	
	public void setMerchantNumber(String merchantNumber){
		this.merchantNumber = merchantNumber;
	}
	
	public String getMerchantNumber(){
		return merchantNumber;
	}
	
	public void setOrderDate(Date orderDate){
		this.orderDate = orderDate;
	}
	
	public Date getOrderDate(){
		return orderDate;
	}
	
	public void setPortfolioId(Integer portfolioId){
		this.portfolioId = portfolioId;
	}
	
	public Integer getPortfolioId(){
		return portfolioId;
	}
	
	public void setRspId(Integer rspId){
		this.rspId = rspId;
	}
	
	public Integer getRspId(){
		return rspId;
	}
	
	public void setTransactionAmount(BigDecimal transactionAmount){
		this.transactionAmount = transactionAmount;
	}
	
	public BigDecimal getTransactionAmount(){
		return transactionAmount;
	}
	
	public void setTransactionCharge(BigDecimal transactionCharge){
		this.transactionCharge = transactionCharge;
	}
	
	public BigDecimal getTransactionCharge(){
		return transactionCharge;
	}
	
	public void setTransactionDate(Date transactionDate){
		this.transactionDate = transactionDate;
	}
	
	public Date getTransactionDate(){
		return transactionDate;
	}
	
	public void setTransactionRate(BigDecimal transactionRate){
		this.transactionRate = transactionRate;
	}
	
	public BigDecimal getTransactionRate(){
		return transactionRate;
	}
	
	public void setTransactionStatus(String transactionStatus){
		this.transactionStatus = transactionStatus;
	}
	
	public String getTransactionStatus(){
		return transactionStatus;
	}
	
	public void setTransactionType(String transactionType){
		this.transactionType = transactionType;
	}
	
	public String getTransactionType(){
		return transactionType;
	}
	
	public void setTransactionUnit(String transactionUnit){
		this.transactionUnit = transactionUnit;
	}
	
	public String getTransactionUnit(){
		return transactionUnit;
	}
	
	public void setBizTime(Date bizTime){
		this.bizTime = bizTime;
	}
	
	public Date getBizTime(){
		return bizTime;
	}
	
	public void setUpdateTime(Date updateTime){
		this.updateTime = updateTime;
	}
	
	public Date getUpdateTime(){
		return updateTime;
	}
	

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}

