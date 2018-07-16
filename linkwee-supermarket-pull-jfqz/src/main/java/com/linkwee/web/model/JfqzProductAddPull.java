package com.linkwee.web.model;

import java.io.Serializable;
import java.math.BigDecimal;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2017年12月01日 18:42:38
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class JfqzProductAddPull implements Serializable {
	
	private static final long serialVersionUID = -6019726327138839411L;
	
    /**
     *自增长主键
     */
	private Integer id;
	
    /**
     *最小投资金额（元）
     */
	private BigDecimal minInvest;
	
    /**
     *单笔最大投资金额（元）
     */
	private BigDecimal maxInvest;
	
    /**
     *单用户最大允许投资金额（元）
     */
	private Integer singleMaxInvest;
	
    /**
     *投资期限（天）
     */
	private Integer period;
	
    /**
     *所属产品目录
     */
	private String productCatCode;
	
	/**
     *产品名称
     */
	private String productName;
	
    /**
     *产品ID
     */
	private String productId;
	
    /**
     *期号
     */
	private String issuePeriod;
	
    /**
     *预期年化收益率（%）
     */
	private String profit;
	
    /**
     *售卖状态，2：可售，其它不可售卖
     */
	private Integer sellStatus;
	
    /**
     *已售金额
     */
	private BigDecimal soldAmount;
	
    /**
     *发售总金额
     */
	private BigDecimal totalAmount;
	
    /**
     *产品上线时间
     */
	private String upLineTime;
	
    /**
     *产品下线时间
     */
	private String downLineTime;
	
    /**
     *记录最后更新时间、时间戳
     */
	private String updateTime;
	
    /**
     *记录创建时间、时间戳
     */
	private String createTime;
	

	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public BigDecimal getMinInvest() {
		return minInvest;
	}


	public void setMinInvest(BigDecimal minInvest) {
		this.minInvest = minInvest;
	}


	public BigDecimal getMaxInvest() {
		return maxInvest;
	}


	public void setMaxInvest(BigDecimal maxInvest) {
		this.maxInvest = maxInvest;
	}


	public Integer getSingleMaxInvest() {
		return singleMaxInvest;
	}


	public void setSingleMaxInvest(Integer singleMaxInvest) {
		this.singleMaxInvest = singleMaxInvest;
	}


	public Integer getPeriod() {
		return period;
	}


	public void setPeriod(Integer period) {
		this.period = period;
	}


	public String getProductCatCode() {
		return productCatCode;
	}


	public void setProductCatCode(String productCatCode) {
		this.productCatCode = productCatCode;
	}


	public String getProductId() {
		return productId;
	}


	public void setProductId(String productId) {
		this.productId = productId;
	}


	public String getIssuePeriod() {
		return issuePeriod;
	}


	public void setIssuePeriod(String issuePeriod) {
		this.issuePeriod = issuePeriod;
	}


	public String getProfit() {
		return profit;
	}


	public void setProfit(String profit) {
		this.profit = profit;
	}


	public Integer getSellStatus() {
		return sellStatus;
	}


	public void setSellStatus(Integer sellStatus) {
		this.sellStatus = sellStatus;
	}


	public BigDecimal getSoldAmount() {
		return soldAmount;
	}


	public void setSoldAmount(BigDecimal soldAmount) {
		this.soldAmount = soldAmount;
	}


	public BigDecimal getTotalAmount() {
		return totalAmount;
	}


	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}


	public String getUpLineTime() {
		return upLineTime;
	}


	public void setUpLineTime(String upLineTime) {
		this.upLineTime = upLineTime;
	}


	public String getDownLineTime() {
		return downLineTime;
	}


	public void setDownLineTime(String downLineTime) {
		this.downLineTime = downLineTime;
	}


	public String getUpdateTime() {
		return updateTime;
	}


	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}


	public String getCreateTime() {
		return createTime;
	}


	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}


	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}

