package com.linkwee.activity.model;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.annotation.JsonFormat;

public class Redpaper implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4806272163654637018L;
	
	private Long id;// id
	private Double redpaperMoney;// 红包金额
	private String activityId;// 活动

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	private Date expireDate;// 过期时间
	private Integer status;// 1=未派发|2=未兑换|3=已兑换|4=已过期
	private String showName;//显示名称
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	private Date addTime; //添加时间
	private Integer redpaperCount; //红包数
	private String operatorPerson; //操作人
	private String redPaperType; //
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")  
	private Date sendTime; //发送时间
	private String remark; //红包描述
	
	
	
	
	
	public String getRemark() {
		return remark;
	}




	public void setRemark(String remark) {
		this.remark = remark;
	}




	public String getRedPaperType() {
		return redPaperType;
	}




	public void setRedPaperType(String redPaperType) {
		this.redPaperType = redPaperType;
	}




	public Long getId() {
		return id;
	}




	public void setId(Long id) {
		this.id = id;
	}




	public Double getRedpaperMoney() {
		return redpaperMoney;
	}




	public void setRedpaperMoney(Double redpaperMoney) {
		this.redpaperMoney = redpaperMoney;
	}




	public String getActivityId() {
		return activityId;
	}




	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}




	public Date getExpireDate() {
		return expireDate;
	}




	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}




	public Integer getStatus() {
		return status;
	}




	public void setStatus(Integer status) {
		this.status = status;
	}




	public String getShowName() {
		return showName;
	}




	public void setShowName(String showName) {
		this.showName = showName;
	}




	public Date getAddTime() {
		return addTime;
	}




	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}




	public Date getSendTime() {
		return sendTime;
	}




	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}




	public Integer getRedpaperCount() {
		return redpaperCount;
	}




	public void setRedpaperCount(Integer redpaperCount) {
		this.redpaperCount = redpaperCount;
	}




	public String getOperatorPerson() {
		return operatorPerson;
	}




	public void setOperatorPerson(String operatorPerson) {
		this.operatorPerson = operatorPerson;
	}




	@Override
	public String toString() {
		return  JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}
