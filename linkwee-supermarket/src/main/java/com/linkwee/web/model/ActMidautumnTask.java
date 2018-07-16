package com.linkwee.web.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
 import java.lang.Integer;
 import java.lang.String;
 import java.util.Date;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2017年09月20日 10:12:11
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class ActMidautumnTask implements Serializable {
	
	private static final long serialVersionUID = -4740879212665651643L;
	
    /**
     *自增ID
     */
	private Integer id;
	
    /**
     *用户ID
     */
	private String userId;
	
    /**
     *出单完成状态： 0未完成，1已完成
     */
	private Integer investStatus;
	
    /**
     *基金注册完成状态： 0未完成，1已完成
     */
	private Integer fundRegStatus;
	
    /**
     *邀请理财师注册完成状态： 0未完成，1已完成
     */
	private Integer inviteCfpStatus;
	
    /**
     *全部完成状态： 0未完成，1已完成
     */
	private Integer taskAllStatus;
	
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
	
	public void setInvestStatus(Integer investStatus){
		this.investStatus = investStatus;
	}
	
	public Integer getInvestStatus(){
		return investStatus;
	}
	
	public void setFundRegStatus(Integer fundRegStatus){
		this.fundRegStatus = fundRegStatus;
	}
	
	public Integer getFundRegStatus(){
		return fundRegStatus;
	}
	
	public void setInviteCfpStatus(Integer inviteCfpStatus){
		this.inviteCfpStatus = inviteCfpStatus;
	}
	
	public Integer getInviteCfpStatus(){
		return inviteCfpStatus;
	}
	
	public void setTaskAllStatus(Integer taskAllStatus){
		this.taskAllStatus = taskAllStatus;
	}
	
	public Integer getTaskAllStatus(){
		return taskAllStatus;
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

