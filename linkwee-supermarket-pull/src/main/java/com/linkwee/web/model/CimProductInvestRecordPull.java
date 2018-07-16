package com.linkwee.web.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2017年02月14日 18:11:13
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class CimProductInvestRecordPull implements Serializable {
	
	private static final long serialVersionUID = -5923472813613269011L;
	
    /**
     *自增长主键
     */
	private Integer id;
	
    /**
     *投资记录id(系统产生的唯一标识)
     */
	private String investId;
	
    /**
     *交易编号
     */
	private String txId;
	
    /**
     *用户id
     */
	private String userId;
	
    /**
     *第三方机构产品编码
     */
	private String productId;
	
    /**
     *购买本金
     */
	private BigDecimal investAmt;
	
	/**
	 * 存量投资金额
	 */
	private BigDecimal stockInvestAmt;
	
    /**
     *收益
     */
	private BigDecimal profit;
	
    /**
     *产品购买日期
     */
	private Date investStartTime;
	
    /**
     *产品回款日期
     */
	private Date investEndTime;
	
    /**
     *机构编码
     */
	private String platfrom;
	
    /**
     *创建时间
     */
	private Date createTime;
	
    /**
     *更新日期
     */
	private Date updateTime;
	
    /**
     *投资记录执行添加状态(0-已执行添加|1-待执行添加)
     */
	private Integer updateStatus;
	
    /**
     *执行结果
     */
	private String message;
	


	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return id;
	}
	
	public void setInvestId(String investId){
		this.investId = investId;
	}
	
	public String getInvestId(){
		return investId;
	}
	
	public void setTxId(String txId){
		this.txId = txId;
	}
	
	public String getTxId(){
		return txId;
	}
	
	public void setUserId(String userId){
		this.userId = userId;
	}
	
	public String getUserId(){
		return userId;
	}
	
	public void setProductId(String productId){
		this.productId = productId;
	}
	
	public String getProductId(){
		return productId;
	}
	
	public void setInvestAmt(BigDecimal investAmt){
		this.investAmt = investAmt;
	}
	
	public BigDecimal getInvestAmt(){
		return investAmt;
	}
	
	
	public BigDecimal getStockInvestAmt() {
		return stockInvestAmt;
	}

	public void setStockInvestAmt(BigDecimal stockInvestAmt) {
		this.stockInvestAmt = stockInvestAmt;
	}

	public void setProfit(BigDecimal profit){
		this.profit = profit;
	}
	
	public BigDecimal getProfit(){
		return profit;
	}
	
	public Date getInvestStartTime() {
		return investStartTime;
	}

	public void setInvestStartTime(Date investStartTime) {
		this.investStartTime = investStartTime;
	}

	public Date getInvestEndTime() {
		return investEndTime;
	}

	public void setInvestEndTime(Date investEndTime) {
		this.investEndTime = investEndTime;
	}

	public void setPlatfrom(String platfrom){
		this.platfrom = platfrom;
	}
	
	public String getPlatfrom(){
		return platfrom;
	}
	
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public void setUpdateStatus(Integer updateStatus){
		this.updateStatus = updateStatus;
	}
	
	public Integer getUpdateStatus(){
		return updateStatus;
	}
	
	public void setMessage(String message){
		this.message = message;
	}
	
	public String getMessage(){
		return message;
	}
	

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}

