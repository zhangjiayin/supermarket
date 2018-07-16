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
 * @创建时间：2017年09月13日 14:46:43
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class CrmCfpNewcomerWelfareTask implements Serializable {
	
	private static final long serialVersionUID = -2201804789814358086L;
	
    /**
     *自增ID
     */
	private Integer id;
	
    /**
     *用户ID
     */
	private String userId;
	
    /**
     *注册完成状态： 0未完成，1已完成
     */
	private Integer regStatus;
	
    /**
     *绑卡完成状态： 0未完成，1已完成
     */
	private Integer bindcardStatus;
	
    /**
     *自投完成状态： 0未完成，1已完成
     */
	private Integer investStatus;
	
    /**
     *邀请理财师完成状态： 0未完成，1已完成
     */
	private Integer inviteCfpStatus;
	
    /**
     *发红包完成状态： 0未完成，1已完成
     */
	private Integer inviteCfpInvestStatus;
	
    /**
     *终极大奖完成状态： 0未完成，1已完成
     */
	private Integer welfareAllStatus;
	
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
	
	public void setRegStatus(Integer regStatus){
		this.regStatus = regStatus;
	}
	
	public Integer getRegStatus(){
		return regStatus;
	}
	
	public void setBindcardStatus(Integer bindcardStatus){
		this.bindcardStatus = bindcardStatus;
	}
	
	public Integer getBindcardStatus(){
		return bindcardStatus;
	}
	
	public void setInvestStatus(Integer investStatus){
		this.investStatus = investStatus;
	}
	
	public Integer getInvestStatus(){
		return investStatus;
	}
	
	public void setInviteCfpStatus(Integer inviteCfpStatus){
		this.inviteCfpStatus = inviteCfpStatus;
	}
	
	public Integer getInviteCfpStatus(){
		return inviteCfpStatus;
	}
	
	public void setInviteCfpInvestStatus(Integer inviteCfpInvestStatus){
		this.inviteCfpInvestStatus = inviteCfpInvestStatus;
	}
	
	public Integer getInviteCfpInvestStatus(){
		return inviteCfpInvestStatus;
	}
	
	public void setWelfareAllStatus(Integer welfareAllStatus){
		this.welfareAllStatus = welfareAllStatus;
	}
	
	public Integer getWelfareAllStatus(){
		return welfareAllStatus;
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

