package com.linkwee.web.model;

import com.fasterxml.jackson.annotation.JsonFormat;
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
 * @创建时间：2018年06月27日 09:43:00
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class CimOrgFresGradeType implements Serializable {
	
	private static final long serialVersionUID = 8599602272057751617L;
	
    /**
     *主键
     */
	private Integer id;
	
    /**
     *类型编码
     */
	private String fresNumber;
	
    /**
     *类型名称
     */
	private String fresName;
	
    /**
     *类型排名
     */
	private Integer fresSort;
	
    /**
     *类型满分
     */
	private BigDecimal fresFullScore;
	
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
	
	public void setFresSort(Integer fresSort){
		this.fresSort = fresSort;
	}
	
	public Integer getFresSort(){
		return fresSort;
	}
	
	public void setFresFullScore(BigDecimal fresFullScore){
		this.fresFullScore = fresFullScore;
	}
	
	public BigDecimal getFresFullScore(){
		return fresFullScore;
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

