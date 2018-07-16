package com.linkwee.web.model.crm;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 import java.lang.Integer;
 import java.lang.String;
 import java.util.Date;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2017年10月27日 18:00:28
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class ActJobGradeVoucher implements Serializable {
	
	private static final long serialVersionUID = 2924472262962385193L;
	
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
     *当前职级权重
     */
	private String curJobGrade;
	
    /**
     *使用时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date useTime;
	
    /**
     *过期时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
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

	public String getCurJobGrade() {
		return curJobGrade;
	}

	public void setCurJobGrade(String curJobGrade) {
		this.curJobGrade = curJobGrade;
	}

	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return id;
	}
	
	public void setVoucherId(String voucherId){
		this.voucherId = voucherId;
	}
	
	public String getVoucherId(){
		return voucherId;
	}
	
	public void setUserId(String userId){
		this.userId = userId;
	}
	
	public String getUserId(){
		return userId;
	}
	
	public void setMobile(String mobile){
		this.mobile = mobile;
	}
	
	public String getMobile(){
		return mobile;
	}
	
	public void setActivityAttr(String activityAttr){
		this.activityAttr = activityAttr;
	}
	
	public String getActivityAttr(){
		return activityAttr;
	}
	
	public void setJobGrade(String jobGrade){
		this.jobGrade = jobGrade;
	}
	
	public String getJobGrade(){
		return jobGrade;
	}
	
	public void setJobGradeWeight(Integer jobGradeWeight){
		this.jobGradeWeight = jobGradeWeight;
	}
	
	public Integer getJobGradeWeight(){
		return jobGradeWeight;
	}
	
	public void setUseTime(Date useTime){
		this.useTime = useTime;
	}
	
	public Date getUseTime(){
		return useTime;
	}
	
	public void setExpiresTime(Date expiresTime){
		this.expiresTime = expiresTime;
	}
	
	public Date getExpiresTime(){
		return expiresTime;
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
	
	public void setUpdateTime(Date updateTime){
		this.updateTime = updateTime;
	}
	
	public Date getUpdateTime(){
		return updateTime;
	}
	
	public void setOperator(String operator){
		this.operator = operator;
	}
	
	public String getOperator(){
		return operator;
	}
	
}

