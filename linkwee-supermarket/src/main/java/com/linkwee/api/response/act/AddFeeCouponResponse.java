package com.linkwee.api.response.act;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linkwee.core.base.BaseEntity;
import com.linkwee.core.jackson.RateSerializer;

public class AddFeeCouponResponse extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3559044795358942706L;

	/**
     *加拥券编号
     */
	private String couponId;
	
    /**
     *加拥券名称
     */
	private String name;
	
    /**
     *来源
     */
	private String source;
	
    /**
     *比率
     */
	@JsonSerialize(using=RateSerializer.class)
	private BigDecimal rate;
	
    /**
     *加拥券类型 1=加拥券|2=奖励券
     */
	private Integer type;
	
    /**
     *平台限制 1=限制|0=不限制
     */
	private Boolean platformLimit;
	
    /**
     *平台编号 platform_limit=1时有效
     */
	private String platformId;
	
	/**
	 * 平台名称
	 */
	private String platformName;
	
    /**
     *用户投资限制 0=不限|1=用户首投|2=平台首投
     */
	private Integer investLimit;
	
    /**
     *生效时间
     */ 
	private Date validBeginTime;
	
    /**
     *过期时间
     */  
	private Date validEndTime;
	
	/**
	 * 状态 0：未过期 1：已过期
	 */
	private Integer status;
	
	/**
	 * 是否已使用
	 */
	private boolean hasUsed;
	
	/**
	 * 显示状态 1：未过期  2：已过期  3：已使用
	 */
	private Integer showStatus;

	public String getCouponId() {
		return couponId;
	}

	public void setCouponId(String couponId) {
		this.couponId = couponId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Boolean getPlatformLimit() {
		return platformLimit;
	}

	public void setPlatformLimit(Boolean platformLimit) {
		this.platformLimit = platformLimit;
	}

	public String getPlatformId() {
		return platformId;
	}

	public void setPlatformId(String platformId) {
		this.platformId = platformId;
	}

	public Integer getInvestLimit() {
		return investLimit;
	}

	public void setInvestLimit(Integer investLimit) {
		this.investLimit = investLimit;
	}

	public Date getValidBeginTime() {
		return validBeginTime;
	}

	public void setValidBeginTime(Date validBeginTime) {
		this.validBeginTime = validBeginTime;
	}

	public Date getValidEndTime() {
		return validEndTime;
	}

	public void setValidEndTime(Date validEndTime) {
		this.validEndTime = validEndTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public boolean isHasUsed() {
		return hasUsed;
	}

	public void setHasUsed(boolean hasUsed) {
		this.hasUsed = hasUsed;
	}

	public Integer getShowStatus() {
		if(hasUsed && status == 1){
			showStatus = 3;
		}else if(!hasUsed && status == 1){
			showStatus = 2;
		}else{
			showStatus = 1;
		}
		return showStatus;
	}

	public void setShowStatus(Integer showStatus) {
		this.showStatus = showStatus;
	}

	public String getPlatformName() {
		return platformName;
	}

	public void setPlatformName(String platformName) {
		this.platformName = platformName;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}
}
