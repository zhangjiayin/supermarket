package com.linkwee.web.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linkwee.core.jackson.MoneySerializer;
import com.linkwee.core.jackson.RateSerializer;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2017年10月17日 18:42:45
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class ActAddFeeCouponUseDetail implements Serializable {
	
	private static final long serialVersionUID = 6861209873584219385L;
	
    /**
     *
     */
	private Integer id;
	
    /**
     *用户id
     */
	private String userId;
	
    /**
     *加拥券编号
     */
	private String couponId;
	
    /**
     *投资记录id
     */
	private String investId;
	
    /**
     *佣金
     */
	@JsonSerialize(using=MoneySerializer.class)
	private BigDecimal feeAmount;
	
    /**
     *加拥比率
     */
	@JsonSerialize(using=RateSerializer.class)
	private BigDecimal feeRate;
	
    /**
     *加拥券类型 1=加拥券|2=奖励券
     */
	private Integer couponType;
	
    /**
     *创建时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date createTime;
	
    /**
     *扩展字段1
     */
	private String extends1;
	
    /**
     *扩展字段2
     */
	private String extends2;
	
    /**
     *扩展字段3
     */
	private String extends3;
	


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
	
	public void setCouponId(String couponId){
		this.couponId = couponId;
	}
	
	public String getCouponId(){
		return couponId;
	}
	
	public void setInvestId(String investId){
		this.investId = investId;
	}
	
	public String getInvestId(){
		return investId;
	}
	
	public void setFeeAmount(BigDecimal feeAmount){
		this.feeAmount = feeAmount;
	}
	
	public BigDecimal getFeeAmount(){
		return feeAmount;
	}
	
	public void setCouponType(Integer couponType){
		this.couponType = couponType;
	}
	
	public Integer getCouponType(){
		return couponType;
	}
	
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	
	public Date getCreateTime(){
		return createTime;
	}
	
	public void setExtends1(String extends1){
		this.extends1 = extends1;
	}
	
	public String getExtends1(){
		return extends1;
	}
	
	public void setExtends2(String extends2){
		this.extends2 = extends2;
	}
	
	public String getExtends2(){
		return extends2;
	}
	
	public void setExtends3(String extends3){
		this.extends3 = extends3;
	}
	
	public String getExtends3(){
		return extends3;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}

	public BigDecimal getFeeRate() {
		return feeRate;
	}

	public void setFeeRate(BigDecimal feeRate) {
		this.feeRate = feeRate;
	}
}

