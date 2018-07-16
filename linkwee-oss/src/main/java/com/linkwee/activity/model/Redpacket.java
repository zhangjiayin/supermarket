package com.linkwee.activity.model;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class Redpacket implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3838952094231231640L;
	
	private Long id;// id
	private String fid;// fid
	private String redPaperType;// 红包类型
	private Double redpaperMoney;// 红包金额
	private Integer busType;// 1=投资返现|2=现金红包|3=抵现红包
	private String activityId;// 活动
	private String activityName;// 活动名称
	private String userMobile;// 用户手机号
	private String userName;// 用户姓名
	private String userId;// 用户编码
	private String saleUserMobile;// 理财师手机号
	private String saleUserName;// 理财师名称
	private String saleUserId;// 理财师编码
	private Date expireDate;// 过期时间
	private Date useDate;//使用时间
	private Integer status;// 1=未派发|2=未兑换|3=已兑换|4=已过期
	private String showName;//显示名称
	private String useRemark;//备注
	private Date initDate;// 初始时间
	private Date updateDate;// 更新时间
	private String operator; //后台发送红包操作人
	
	
	public Redpacket() {}
	
	
	
	
	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFid() {
		return fid;
	}
	public void setFid(String fid) {
		this.fid = fid;
	}
	public String getRedPaperType() {
		return redPaperType;
	}
	public void setRedPaperType(String redPaperType) {
		this.redPaperType = redPaperType;
	}
	public Double getRedpaperMoney() {
		return redpaperMoney;
	}
	public void setRedpaperMoney(Double redpaperMoney) {
		this.redpaperMoney = redpaperMoney;
	}
	public Integer getBusType() {
		return busType;
	}
	public void setBusType(Integer busType) {
		this.busType = busType;
	}
	public String getActivityId() {
		return activityId;
	}
	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}
	public String getActivityName() {
		return activityName;
	}
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	public String getUserMobile() {
		return userMobile;
	}
	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getSaleUserMobile() {
		return saleUserMobile;
	}
	public void setSaleUserMobile(String saleUserMobile) {
		this.saleUserMobile = saleUserMobile;
	}
	public String getSaleUserName() {
		return saleUserName;
	}
	public void setSaleUserName(String saleUserName) {
		this.saleUserName = saleUserName;
	}
	public String getSaleUserId() {
		return saleUserId;
	}
	public void setSaleUserId(String saleUserId) {
		this.saleUserId = saleUserId;
	}
	public Date getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}
	public Date getUseDate() {
		return useDate;
	}
	public void setUseDate(Date useDate) {
		this.useDate = useDate;
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
	public String getUseRemark() {
		return useRemark;
	}
	public void setUseRemark(String useRemark) {
		this.useRemark = useRemark;
	}
	public Date getInitDate() {
		return initDate;
	}
	public void setInitDate(Date initDate) {
		this.initDate = initDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
	@Override
	public String toString() {
		return  JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}
