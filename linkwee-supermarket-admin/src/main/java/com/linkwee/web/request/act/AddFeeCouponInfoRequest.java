package com.linkwee.web.request.act;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonFormat;

public class AddFeeCouponInfoRequest implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1427104083669377997L;
	
	private String couponId;

	/**
     *来源
     */
	@NotNull(message="来源不能为空")
	private String source;
	
    /**
     *比率
     */
	@NotNull(message="比率不能为空")
	private BigDecimal rate;
	
    /**
     *加拥券类型 1=加拥券|2=奖励券
     */
	@NotNull(message="加拥券类型不能为空")
	@Range(min=1,max=2,message="加拥券类型必须为1或者2")
	private Integer type;
	
    /**
     *平台限制 1=限制|0=不限制
     */
	@NotNull(message="平台限制不能为空")
	private Integer platformLimit;
	
    /**
     *平台编号 platform_limit=1时有效
     */
	private String platformId;
	
    /**
     *用户投资限制 0=不限|1=用户首投|2=平台首投
     */
	@NotNull(message="用户投资限制不能为空")
	@Range(min=0,max=2,message="用户投资值必须为0~2之间")
	private Integer investLimit;
	
    /**
     *生效时间
     */ 
	@NotNull(message="生效时间不能为空")
	private String validBeginTime;
	
    /**
     *过期时间
     */  
	@NotNull(message="过期时间不能为空")
	private String validEndTime;
	
    /**
     *操作人
     */
	private String operator;

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

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getCouponId() {
		return couponId;
	}

	public void setCouponId(String couponId) {
		this.couponId = couponId;
	}

	public Integer getPlatformLimit() {
		return platformLimit;
	}

	public void setPlatformLimit(Integer platformLimit) {
		this.platformLimit = platformLimit;
	}

	public String getValidBeginTime() {
		return validBeginTime;
	}

	public void setValidBeginTime(String validBeginTime) {
		this.validBeginTime = validBeginTime;
	}

	public String getValidEndTime() {
		return validEndTime;
	}

	public void setValidEndTime(String validEndTime) {
		this.validEndTime = validEndTime;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}
}
