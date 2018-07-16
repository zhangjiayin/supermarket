package com.linkwee.web.model.activity;

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
 * @创建时间：2017年03月02日 16:56:08
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class ActHelpRaiseRateDetail implements Serializable {
	
	private static final long serialVersionUID = -2559540756679927361L;
	
    /**
     *自增ID
     */
	private Integer id;
	
    /**
     *用户ID
     */
	private String userId;
	
    /**
     *助力利率
     */
	private Double helpRate;
	
    /**
     *助力人微信头像
     */
	private String weixinIcoUrl;
	
    /**
     *助力人微信昵称
     */
	private String weixinNickname;
	
    /**
     *描述
     */
	private String decription;
	
    /**
     *openid
     */
	private String openid;
	
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
	
	public void setHelpRate(Double helpRate){
		this.helpRate = helpRate;
	}
	
	public Double getHelpRate(){
		return helpRate;
	}
	
	public void setWeixinIcoUrl(String weixinIcoUrl){
		this.weixinIcoUrl = weixinIcoUrl;
	}
	
	public String getWeixinIcoUrl(){
		return weixinIcoUrl;
	}
	
	public void setWeixinNickname(String weixinNickname){
		this.weixinNickname = weixinNickname;
	}
	
	public String getWeixinNickname(){
		return weixinNickname;
	}
	
	public void setDecription(String decription){
		this.decription = decription;
	}
	
	public String getDecription(){
		return decription;
	}
	
	public void setOpenid(String openid){
		this.openid = openid;
	}
	
	public String getOpenid(){
		return openid;
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

