/**   
 * 文件名：TRedPaperAccount.java   
 *    
 * 日期：2016年03月21日23时40分08秒  
 * 
 * Copyright (c) 深圳市前海领会科技有限公司     
 */
package com.linkwee.activity.model;

import java.io.Serializable;
import java.util.Date;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;


public class RedpacketAccount implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8611188625198781523L;
	private Long id;//id
	private String fid;// fid
	private Date initDate;// 初始时间
	private Date updateDate;// 更新时间
	private String activityId;// 活动ID
	private String fatherId;// 父节点
	private Double sendMoney;// 已派发金额
	private Double redeemMoney;// 已兑换金额
	private Double money;// 总金额
	private String remark;// 备注
	private Integer status;// 1=数据|2=记录
	
	/**   
	 * 创建一个新的实例 TRedPaperAccount.   
	 *      
	 */
	public RedpacketAccount() {
		super();
	}
	
	public RedpacketAccount(Long id) {
		this.id=id;
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

	/**
	 * activityId
	 * 
	 * @return the activityId
	 */
	public String getActivityId() {
		return activityId;
	}

	/**
	 * @param activityId
	 *            the activityId to set
	 */
	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}
	
	/**
	 * fatherId
	 * 
	 * @return the fatherId
	 */
	public String getFatherId() {
		return fatherId;
	}

	/**
	 * @param fatherId
	 *            the fatherId to set
	 */
	public void setFatherId(String fatherId) {
		this.fatherId = fatherId;
	}
	
	/**
	 * sendMoney
	 * 
	 * @return the sendMoney
	 */
	public Double getSendMoney() {
		return sendMoney;
	}

	/**
	 * @param sendMoney
	 *            the sendMoney to set
	 */
	public void setSendMoney(Double sendMoney) {
		this.sendMoney = sendMoney;
	}
	
	/**
	 * redeemMoney
	 * 
	 * @return the redeemMoney
	 */
	public Double getRedeemMoney() {
		return redeemMoney;
	}
	
	
	/**
	 * money
	 * 
	 * @return the money
	 */
	public Double getMoney() {
		return money;
	}

	/**
	 * @param money
	 *            the money to set
	 */
	public void setMoney(Double money) {
		this.money = money;
	}

	/**
	 * @param redeemMoney
	 *            the redeemMoney to set
	 */
	public void setRedeemMoney(Double redeemMoney) {
		this.redeemMoney = redeemMoney;
	}
	
	/**
	 * remark
	 * 
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark
	 *            the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	/**
	 * status
	 * 
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
	@Override
	public String toString() {
		return  JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}
