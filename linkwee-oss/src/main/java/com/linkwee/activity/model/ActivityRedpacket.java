package com.linkwee.activity.model;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class ActivityRedpacket implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 740417214001657702L;
	
	private Long id;//id
	private String fid;// fid
	private Date initDate;// 初始时间
	private Date updateDate;// 更新时间
	private String activityId;// 活动
	private String redPaperId;// 红包
	private String showName;// 名称
	private String useRemark;// 备注
	
	public ActivityRedpacket() {}

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

	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	public String getRedPaperId() {
		return redPaperId;
	}

	public void setRedPaperId(String redPaperId) {
		this.redPaperId = redPaperId;
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
	
	@Override
	public String toString() {
		return  JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}

}
