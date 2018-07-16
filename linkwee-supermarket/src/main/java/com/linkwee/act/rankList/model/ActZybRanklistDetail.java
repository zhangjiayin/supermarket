package com.linkwee.act.rankList.model;

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
 * @创建人： ch
 * 
 * @创建时间：2017年02月13日 16:50:01
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class ActZybRanklistDetail implements Serializable {
	
	private static final long serialVersionUID = -3720507918086640223L;
	
    /**
     *
     */
	private Integer id;
	
    /**
     *头像url
     */
	private String headImg;
	
    /**
     *用户手机
     */
	private String userMobile;
	
    /**
     *用户名称
     */
	private String userName;
	
    /**
     *总收益
     */
	private String totalProfit;
	
    /**
     *活动奖励
     */
	private String activityProfit;
	
    /**
     *佣金收益
     */
	private String feeProfit;
	
    /**
     *排序
     */
	private Integer sort;
	
    /**
     *状态 0=有效|1=过期
     */
	private Integer status;
	
    /**
     *
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date createTime;
	


	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return id;
	}
	
	public void setHeadImg(String headImg){
		this.headImg = headImg;
	}
	
	public String getHeadImg(){
		return headImg;
	}
	
	public void setUserMobile(String userMobile){
		this.userMobile = userMobile;
	}
	
	public String getUserMobile(){
		return userMobile;
	}
	
	public void setUserName(String userName){
		this.userName = userName;
	}
	
	public String getUserName(){
		return userName;
	}
	
	public void setTotalProfit(String totalProfit){
		this.totalProfit = totalProfit;
	}
	
	public String getTotalProfit(){
		return totalProfit;
	}
	
	public void setActivityProfit(String activityProfit){
		this.activityProfit = activityProfit;
	}
	
	public String getActivityProfit(){
		return activityProfit;
	}
	
	public void setFeeProfit(String feeProfit){
		this.feeProfit = feeProfit;
	}
	
	public String getFeeProfit(){
		return feeProfit;
	}
	
	public void setSort(Integer sort){
		this.sort = sort;
	}
	
	public Integer getSort(){
		return sort;
	}
	
	public void setStatus(Integer status){
		this.status = status;
	}
	
	public Integer getStatus(){
		return status;
	}
	
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	
	public Date getCreateTime(){
		return createTime;
	}
	

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}

