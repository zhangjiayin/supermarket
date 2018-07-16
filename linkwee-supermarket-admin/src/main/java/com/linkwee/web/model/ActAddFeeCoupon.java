package com.linkwee.web.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linkwee.core.jackson.MoneySerializer;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2017年10月20日 17:11:30
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class ActAddFeeCoupon implements Serializable {
	
	private static final long serialVersionUID = -5440894500367128754L;
	
    /**
     *
     */
	private Integer id;
	
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
	@JsonSerialize(using=MoneySerializer.class)
	private BigDecimal rate;
	
    /**
     *加拥券类型 1=加拥券|2=奖励券
     */
	private Integer type;
	
    /**
     *平台限制 1=限制|0=不限制
     */
	private Integer platformLimit;
	
    /**
     *平台编号 platform_limit=1时有效
     */
	private String platformId;
	
    /**
     *用户投资限制 0=不限|1=用户首投|2=平台首投
     */
	private Integer investLimit;
	
    /**
     *生效时间
     */  
	private String validBeginTime;
	
    /**
     *过期时间
     */  
	private String validEndTime;
	
    /**
     *创建时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date createTime;
	
    /**
     *更新时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date updateTime;
	
    /**
     *操作人
     */
	private String operator;
	
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
	
	public void setCouponId(String couponId){
		this.couponId = couponId;
	}
	
	public String getCouponId(){
		return couponId;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public void setSource(String source){
		this.source = source;
	}
	
	public String getSource(){
		return source;
	}
	
	public void setType(Integer type){
		this.type = type;
	}
	
	public Integer getType(){
		return type;
	}
	
	public void setPlatformId(String platformId){
		this.platformId = platformId;
	}
	
	public String getPlatformId(){
		return platformId;
	}
	
	public void setInvestLimit(Integer investLimit){
		this.investLimit = investLimit;
	}
	
	public Integer getInvestLimit(){
		return investLimit;
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
	
	public void setOperator(String operator){
		this.operator = operator;
	}
	
	public String getOperator(){
		return operator;
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

