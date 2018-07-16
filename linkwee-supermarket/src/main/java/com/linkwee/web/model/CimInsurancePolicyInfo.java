package com.linkwee.web.model;

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
 * @创建人： liqimoon
 * 
 * @创建时间：2017年09月18日 19:08:09
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class CimInsurancePolicyInfo implements Serializable {
	
	private static final long serialVersionUID = 4563662432887284148L;
	
    /**
     *主键，自增长
     */
	private Integer id;
	
    /**
     *保险机构编码-不重复字段
     */
	private String orgNumber;
	
    /**
     *渠道用户标识(领会userid)
     */
	private String userId;
	
    /**
     *保险产品编码
     */
	private String productId;
	
    /**
     *投保单号
     */
	private String insureNum;
	
    /**
     *产品名称
     */
	private String productName;
	
    /**
     *计划名称
     */
	private String planName;
	
    /**
     *投保人姓名
     */
	private String applicant;
	
    /**
     *被保人姓名（多人以逗号分隔）
     */
	private String insurant;
	
    /**
     *起保日期
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date startDate;
	
    /**
     *终保日期
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date endDate;
	
    /**
     *保险公司保单号
     */
	private String policyNum;
	
    /**
     *创建时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date creatTime;
	
    /**
     *更新时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date upTime;
	


	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return id;
	}
	
	public void setOrgNumber(String orgNumber){
		this.orgNumber = orgNumber;
	}
	
	public String getOrgNumber(){
		return orgNumber;
	}
	
	public void setUserId(String userId){
		this.userId = userId;
	}
	
	public String getUserId(){
		return userId;
	}
	
	public void setProductId(String productId){
		this.productId = productId;
	}
	
	public String getProductId(){
		return productId;
	}
	
	public void setInsureNum(String insureNum){
		this.insureNum = insureNum;
	}
	
	public String getInsureNum(){
		return insureNum;
	}
	
	public void setProductName(String productName){
		this.productName = productName;
	}
	
	public String getProductName(){
		return productName;
	}
	
	public void setPlanName(String planName){
		this.planName = planName;
	}
	
	public String getPlanName(){
		return planName;
	}
	
	public void setApplicant(String applicant){
		this.applicant = applicant;
	}
	
	public String getApplicant(){
		return applicant;
	}
	
	public void setInsurant(String insurant){
		this.insurant = insurant;
	}
	
	public String getInsurant(){
		return insurant;
	}
	
	public void setStartDate(Date startDate){
		this.startDate = startDate;
	}
	
	public Date getStartDate(){
		return startDate;
	}
	
	public void setEndDate(Date endDate){
		this.endDate = endDate;
	}
	
	public Date getEndDate(){
		return endDate;
	}
	
	public void setPolicyNum(String policyNum){
		this.policyNum = policyNum;
	}
	
	public String getPolicyNum(){
		return policyNum;
	}
	
	public void setCreatTime(Date creatTime){
		this.creatTime = creatTime;
	}
	
	public Date getCreatTime(){
		return creatTime;
	}
	
	public void setUpTime(Date upTime){
		this.upTime = upTime;
	}
	
	public Date getUpTime(){
		return upTime;
	}
	

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}

