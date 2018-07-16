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
 * @创建人： liqimoon
 * 
 * @创建时间：2017年08月14日 10:15:42
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class CrmOrgAcctRel implements Serializable {
	
	private static final long serialVersionUID = -7479375716321333630L;
	
    /**
     *流水号
     */
	private Integer id;
	
    /**
     *用户编码
     */
	private String userId;
	
    /**
     *第三方账号类别: 1、微信账号 2、第三方web账户
     */
	private Integer orgAccountType;
	
    /**
     *第三方帐号
     */
	private String orgAccount;
	
    /**
     *机构编码-固定8位编码，不重复字段
     */
	private String orgNumber;
	
    /**
     *是否投资 0否，1是
     */
	private Integer isInvested;
	
    /**
     *是否新用户  0否，1是
     */
	private Integer isNewUser;
	
    /**
     *创建时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date creatTime;
	
    /**
     *修改时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date updateTime;
	
    /**
     *删除标识
     */
	private Integer delStatus;
	
    /**
     *0-p2p账户  1-基金账户
     */
	private Integer orgType;
	


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
	
	public void setOrgAccountType(Integer orgAccountType){
		this.orgAccountType = orgAccountType;
	}
	
	public Integer getOrgAccountType(){
		return orgAccountType;
	}
	
	public void setOrgAccount(String orgAccount){
		this.orgAccount = orgAccount;
	}
	
	public String getOrgAccount(){
		return orgAccount;
	}
	
	public void setOrgNumber(String orgNumber){
		this.orgNumber = orgNumber;
	}
	
	public String getOrgNumber(){
		return orgNumber;
	}
	
	public void setIsInvested(Integer isInvested){
		this.isInvested = isInvested;
	}
	
	public Integer getIsInvested(){
		return isInvested;
	}
	
	public void setIsNewUser(Integer isNewUser){
		this.isNewUser = isNewUser;
	}
	
	public Integer getIsNewUser(){
		return isNewUser;
	}
	
	public void setCreatTime(Date creatTime){
		this.creatTime = creatTime;
	}
	
	public Date getCreatTime(){
		return creatTime;
	}
	
	public void setUpdateTime(Date updateTime){
		this.updateTime = updateTime;
	}
	
	public Date getUpdateTime(){
		return updateTime;
	}
	
	public void setDelStatus(Integer delStatus){
		this.delStatus = delStatus;
	}
	
	public Integer getDelStatus(){
		return delStatus;
	}
	
	public void setOrgType(Integer orgType){
		this.orgType = orgType;
	}
	
	public Integer getOrgType(){
		return orgType;
	}
	

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}

