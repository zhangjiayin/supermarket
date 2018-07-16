package com.linkwee.web.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linkwee.core.jackson.RateSerializer;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2018年06月26日 16:34:44
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class CimOrgRiskFresGrade implements Serializable {
	
	private static final long serialVersionUID = 8690610369045692179L;
	
    /**
     *主键
     */
	private Integer id;
	
    /**
     *机构编码
     */
	private String orgNumber;
	
    /**
     *类型编码
     */
	private String fresNumber;
	
    /**
     *类型名称
     */
	private String fresName;
	
    /**
     *指标得分
     */
	@JsonSerialize(using=RateSerializer.class)
	private BigDecimal fresScore;
	
    /**
     *指标得分明细
     */
	private String scoreDetail;
	
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
	
	public void setFresNumber(String fresNumber){
		this.fresNumber = fresNumber;
	}
	
	public String getFresNumber(){
		return fresNumber;
	}
	
	public void setFresName(String fresName){
		this.fresName = fresName;
	}
	
	public String getFresName(){
		return fresName;
	}
	
	public void setFresScore(BigDecimal fresScore){
		this.fresScore = fresScore;
	}
	
	public BigDecimal getFresScore(){
		return fresScore;
	}
	
	public void setScoreDetail(String scoreDetail){
		this.scoreDetail = scoreDetail;
	}
	
	public String getScoreDetail(){
		return scoreDetail;
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

