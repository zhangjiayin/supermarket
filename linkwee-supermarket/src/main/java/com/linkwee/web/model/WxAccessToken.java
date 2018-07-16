package com.linkwee.web.model;

import java.io.Serializable;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2017年03月08日 17:14:05
 * 
 * Copyright (c) 深圳米格网络科技有限公司-版权所有
 */
public class WxAccessToken implements Serializable {
	
	private static final long serialVersionUID = 3798367633678535365L;
	
    /**
     *主键
     */
	private Long id;
	
    /**
     *access_token凭证
     */
	private String accessToken;
	
    /**
     *凭证有效时间，单位：秒
     */
	private Long expiresIn;
	
    /**
     *创建时间距离当前时间毫秒数
     */
	private Long createTime;
	
    /**
     *userid
     */
	private String userid;
	
    /**
     *创建时间
     */
	private String createDate;
	
	/**
	 * app类型
	 */
	private int appType;

	public void setId(Long id){
		this.id = id;
	}
	
	public Long getId(){
		return id;
	}
	
	public void setAccessToken(String accessToken){
		this.accessToken = accessToken;
	}
	
	public String getAccessToken(){
		return accessToken;
	}
	
	public void setExpiresIn(Long expiresIn){
		this.expiresIn = expiresIn;
	}
	
	public Long getExpiresIn(){
		return expiresIn;
	}
	
	public void setCreateTime(Long createTime){
		this.createTime = createTime;
	}
	
	public Long getCreateTime(){
		return createTime;
	}
	
	public void setUserid(String userid){
		this.userid = userid;
	}
	
	public String getUserid(){
		return userid;
	}
	
	public void setCreateDate(String createDate){
		this.createDate = createDate;
	}
	
	public String getCreateDate(){
		return createDate;
	}
	

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}

	public int getAppType() {
		return appType;
	}

	public void setAppType(int appType) {
		this.appType = appType;
	}
}

