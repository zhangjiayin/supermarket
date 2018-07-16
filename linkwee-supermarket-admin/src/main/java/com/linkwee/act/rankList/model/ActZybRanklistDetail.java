package com.linkwee.act.rankList.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.annotation.JsonFormat;
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
public class ActZybRanklistDetail implements Serializable,Comparable<ActZybRanklistDetail> {
	
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
	
	private String showMobile;
	
    /**
     *用户名称
     */
	private String userName;
	
	/**
	 * 身份证
	 */
	private String idCard;
	
    /**
     *总收益
     */
	private String totalProfit;
	
    /**
     *活动奖励
     */
	private BigDecimal activityProfit;
	
	/**
	 * leader奖励
	 */
	private BigDecimal leaderProfit;
	
	
    /**
     *佣金收益
     */
	private BigDecimal feeProfit;
	
    /**
     *排序
     */
	private Integer sort;
	
    /**
     *状态 1=有效|0=过期
     */
	private Integer status;
	
	private Integer isVirtual;
	
    /**
     *
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date createTime;
	
	private Date updateTime;
	


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
	
	
	public String getShowMobile() {
		return showMobile;
	}

	public void setShowMobile(String showMobile) {
		this.showMobile = showMobile;
	}

	public void setUserName(String userName){
		this.userName = userName;
	}
	
	public String getUserName(){
		return userName;
	}
	
	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public void setTotalProfit(String totalProfit){
		this.totalProfit = totalProfit;
	}
	
	public String getTotalProfit(){
		return totalProfit;
	}
	
	public BigDecimal getActivityProfit() {
		return activityProfit;
	}

	public void setActivityProfit(BigDecimal activityProfit) {
		this.activityProfit = activityProfit;
	}
	
	

	public BigDecimal getLeaderProfit() {
		return leaderProfit;
	}

	public void setLeaderProfit(BigDecimal leaderProfit) {
		this.leaderProfit = leaderProfit;
	}

	public BigDecimal getFeeProfit() {
		return feeProfit;
	}

	public void setFeeProfit(BigDecimal feeProfit) {
		this.feeProfit = feeProfit;
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
	
	public Integer getIsVirtual() {
		return isVirtual;
	}

	public void setIsVirtual(Integer isVirtual) {
		this.isVirtual = isVirtual;
	}

	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	
	public Date getCreateTime(){
		return createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}

	@Override
	public int compareTo(ActZybRanklistDetail o) {
		
		int compare = new BigDecimal(this.totalProfit).compareTo(new BigDecimal(o.totalProfit));
		if(compare==0){
			if(StringUtils.isEmpty(this.userName))return -1;
			else if(StringUtils.isEmpty(o.userName))return 1;
			else{
				compare = this.userName.compareTo(o.userName);
				if(compare==0){
					compare = 1;
				}
			}
		}
		return compare==1?-1:1;
	}
}

