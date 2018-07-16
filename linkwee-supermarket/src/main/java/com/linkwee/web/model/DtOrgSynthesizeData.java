package com.linkwee.web.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linkwee.core.jackson.MoneySerializer;

import java.io.Serializable;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.lang.Integer;
import java.lang.String;
import java.math.BigDecimal;
import java.util.Date;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2018年06月06日 16:49:42
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class DtOrgSynthesizeData implements Serializable {
	
	private static final long serialVersionUID = 927166296332764533L;
	
    /**
     *主键
     */
	private Integer id;
	
    /**
     *机构编码
     */
	private String orgNumber;
	
    /**
     *机构名称
     */
	private String platformName;
	
    /**
     *日期
     */
	@JsonFormat(pattern = "yyyy-MM-dd", timezone="GMT+8")   
	private Date timeSequences;
	
    /**
     *综合年化利率
     */
	@JsonSerialize(using=MoneySerializer.class)
	private BigDecimal compositeApr;
	
    /**
     *平均投资期限(个月)
     */
	@JsonSerialize(using=MoneySerializer.class)
	private BigDecimal avgTime;
	
    /**
     *平均投资额(万元)
     */
	@JsonSerialize(using=MoneySerializer.class)
	private BigDecimal avgInvestment;
	
    /**
     *待还余额(万元)
     */
	@JsonSerialize(using=MoneySerializer.class)
	private BigDecimal remaining;
	
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
     *备注
     */
	private String remark;
	


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
	
	public void setPlatformName(String platformName){
		this.platformName = platformName;
	}
	
	public String getPlatformName(){
		return platformName;
	}
	
	public void setTimeSequences(Date timeSequences){
		this.timeSequences = timeSequences;
	}
	
	public Date getTimeSequences(){
		return timeSequences;
	}
	
	public void setCompositeApr(BigDecimal compositeApr){
		this.compositeApr = compositeApr;
	}
	
	public BigDecimal getCompositeApr(){
		return compositeApr;
	}
	
	public void setAvgTime(BigDecimal avgTime){
		this.avgTime = avgTime;
	}
	
	public BigDecimal getAvgTime(){
		return avgTime;
	}
	
	public void setAvgInvestment(BigDecimal avgInvestment){
		this.avgInvestment = avgInvestment;
	}
	
	public BigDecimal getAvgInvestment(){
		return avgInvestment;
	}
	
	public void setRemaining(BigDecimal remaining){
		this.remaining = remaining;
	}
	
	public BigDecimal getRemaining(){
		return remaining;
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
	
	public void setRemark(String remark){
		this.remark = remark;
	}
	
	public String getRemark(){
		return remark;
	}
	

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}

