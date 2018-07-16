package com.linkwee.web.request.acc;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ActjobgradevoucherRequest implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1427104083669377997L;
	
   /**
    *
    */
	private Integer id;
	
   /**
    *职级体验券编号
    */
	private String voucherId;
	
   /**
    *所属用户ID
    */
	private String userId;
	
   /**
    *用户手机号码
    */
	private String mobile;
	
   /**
    *活动属性
    */
	private String activityAttr;
	
   /**
    *职级
    */
	private String jobGrade;
	
   /**
    *职级权重
    */
	private Integer jobGradeWeight;
	
   /**
    *使用时间
    */
	@NotNull(message="使用时间不能为空")   
	private Date useTime;
	
   /**
    *过期时间
    */
	@NotNull(message="过期时间不能为空")   
	private Date expiresTime;
	
   /**
    *1=未使用|2=已使用|3=已过期
    */
	private Integer status;
	
   /**
    *创建时间
    */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date createTime;
	
   /**
    *更新时间
    */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date updateTime;
	
   /**
    *操作人
    */
	private String operator;

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getVoucherId() {
		return voucherId;
	}
	
	public void setVoucherId(String voucherId) {
		this.voucherId = voucherId;
	}
	
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getMobile() {
		return mobile;
	}
	
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public String getActivityAttr() {
		return activityAttr;
	}
	
	public void setActivityAttr(String activityAttr) {
		this.activityAttr = activityAttr;
	}
	
	public String getJobGrade() {
		return jobGrade;
	}
	
	public void setJobGrade(String jobGrade) {
		this.jobGrade = jobGrade;
	}
	
	public Integer getJobGradeWeight() {
		return jobGradeWeight;
	}
	
	public void setJobGradeWeight(Integer jobGradeWeight) {
		this.jobGradeWeight = jobGradeWeight;
	}
	
	public Date getUseTime() {
		return useTime;
	}
	
	public void setUseTime(Date useTime) {
		this.useTime = useTime;
	}
	
	public Date getExpiresTime() {
		return expiresTime;
	}
	
	public void setExpiresTime(Date expiresTime) {
		this.expiresTime = expiresTime;
	}
	
	public Integer getStatus() {
		return status;
	}
	
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public Date getUpdateTime() {
		return updateTime;
	}
	
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	public String getOperator() {
		return operator;
	}
	
	public void setOperator(String operator) {
		this.operator = operator;
	}

}
