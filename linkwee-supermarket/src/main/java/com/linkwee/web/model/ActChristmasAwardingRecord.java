package com.linkwee.web.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
 import java.lang.Integer;
 import java.lang.Long;
 import java.lang.String;
 import java.util.Date;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2017年11月20日 13:55:08
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class ActChristmasAwardingRecord implements Serializable {
	
	private static final long serialVersionUID = 3309119038862194416L;
	
    /**
     *自增ID
     */
	private Integer id;
	
    /**
     *用户ID
     */
	private String userId;
	
    /**
     *袜子数量
     */
	private Long socksNum;
	
    /**
     *奖品ID
     */
	private Integer prizeId;
	
    /**
     *奖品描述
     */
	private String prizeDescription;
	
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
	
	public void setSocksNum(Long socksNum){
		this.socksNum = socksNum;
	}
	
	public Long getSocksNum(){
		return socksNum;
	}
	
	public void setPrizeId(Integer prizeId){
		this.prizeId = prizeId;
	}
	
	public Integer getPrizeId(){
		return prizeId;
	}
	
	public void setPrizeDescription(String prizeDescription){
		this.prizeDescription = prizeDescription;
	}
	
	public String getPrizeDescription(){
		return prizeDescription;
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

