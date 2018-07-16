package com.linkwee.web.model;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.annotation.JsonFormat;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2017年10月23日 11:23:07
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class ActCfpDoubleElevenActivity implements Serializable {
	
	private static final long serialVersionUID = -5409680242084833146L;
	
    /**
     *自增ID
     */
	private Integer id;
	
    /**
     *用户ID
     */
	private String userId;
	
    /**
     *出单基金完成状态： 0未完成，1已完成
     */
	private Integer fundStatus;
	
    /**
     *出单保险完成状态： 0未完成，1已完成
     */
	private Integer insuranceStatus;
	
    /**
     *出单笔数
     */
	private Integer saleNum;
	
    /**
     *第十一笔出单的时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date elevenOrderTime;
	
    /**
     *创建时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date createTime;
	
    /**
     *最后修改时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date lastUpdateTime;
	


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
	
	public void setFundStatus(Integer fundStatus){
		this.fundStatus = fundStatus;
	}
	
	public Integer getFundStatus(){
		return fundStatus;
	}
	
	public void setInsuranceStatus(Integer insuranceStatus){
		this.insuranceStatus = insuranceStatus;
	}
	
	public Integer getInsuranceStatus(){
		return insuranceStatus;
	}
	
	public void setSaleNum(Integer saleNum){
		this.saleNum = saleNum;
	}
	
	public Integer getSaleNum(){
		return saleNum;
	}
	
	public void setElevenOrderTime(Date elevenOrderTime){
		this.elevenOrderTime = elevenOrderTime;
	}
	
	public Date getElevenOrderTime(){
		return elevenOrderTime;
	}
	
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	
	public Date getCreateTime(){
		return createTime;
	}
	
	public void setLastUpdateTime(Date lastUpdateTime){
		this.lastUpdateTime = lastUpdateTime;
	}
	
	public Date getLastUpdateTime(){
		return lastUpdateTime;
	}
	

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}

