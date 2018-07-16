package com.linkwee.web.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.annotation.JsonFormat;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2017年03月31日 10:34:09
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class CrmCfpLevelRecord implements Serializable {
	
	private static final long serialVersionUID = 2281125257233497392L;
	
    /**
     *编号
     */
	private Integer id;
	
    /**
     *用户编号
     */
	private String userId;
	
    /**
     *月份
     */
	private Integer month;
	
    /**
     *年化业绩
     */
	private BigDecimal yearpurAmount;
	
    /**
     *当前级别
     */
	private String curLevel;
	
    /**
     *当前级别权重
     */
	private Integer curLevelWeight;
	
    /**
     *之前级别
     */
	private String preLevel;
	
    /**
     *见习下级人数
     */
	private Integer taCount;
	
    /**
     *顾问下级人数
     */
	private Integer sm1Count;
	
    /**
     *经理下级人数
     */
	private Integer sm2Count;
	
    /**
     *总监下级人数
     */
	private Integer sm3Count;
	
    /**
     *操作类型 : 1=系统定级 | 2=人工设置
     */
	private Integer optType;
	
    /**
     *状态: 0=无效 | 1 =有效
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
     *操作人 默认 system | 人工设置定级 此处是设置人
     */
	private String operator;
	


	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return id;
	}
	
	public void setUserId(String userId){
		this.userId = userId;
	}
	
	public String getUserId(){
		return userId;
	}
	
	public void setMonth(Integer month){
		this.month = month;
	}
	
	public Integer getMonth(){
		return month;
	}
	
	public void setYearpurAmount(BigDecimal yearpurAmount){
		this.yearpurAmount = yearpurAmount;
	}
	
	public BigDecimal getYearpurAmount(){
		return yearpurAmount;
	}
	
	public void setCurLevel(String curLevel){
		this.curLevel = curLevel;
	}
	
	public String getCurLevel(){
		return curLevel;
	}
	
	public void setCurLevelWeight(Integer curLevelWeight){
		this.curLevelWeight = curLevelWeight;
	}
	
	public Integer getCurLevelWeight(){
		return curLevelWeight;
	}
	
	public void setPreLevel(String preLevel){
		this.preLevel = preLevel;
	}
	
	public String getPreLevel(){
		return preLevel;
	}
	
	public void setTaCount(Integer taCount){
		this.taCount = taCount;
	}
	
	public Integer getTaCount(){
		return taCount;
	}
	
	public void setSm1Count(Integer sm1Count){
		this.sm1Count = sm1Count;
	}
	
	public Integer getSm1Count(){
		return sm1Count;
	}
	
	public void setSm2Count(Integer sm2Count){
		this.sm2Count = sm2Count;
	}
	
	public Integer getSm2Count(){
		return sm2Count;
	}
	
	public void setSm3Count(Integer sm3Count){
		this.sm3Count = sm3Count;
	}
	
	public Integer getSm3Count(){
		return sm3Count;
	}
	
	public void setOptType(Integer optType){
		this.optType = optType;
	}
	
	public Integer getOptType(){
		return optType;
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
	

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}

