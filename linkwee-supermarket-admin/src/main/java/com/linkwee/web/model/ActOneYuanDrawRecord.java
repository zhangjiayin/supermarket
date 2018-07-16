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
 * @创建人： Mignet
 * 
 * @创建时间：2018年04月09日 13:52:08
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class ActOneYuanDrawRecord implements Serializable {
	
	private static final long serialVersionUID = 3902465787766840537L;
	
    /**
     *中奖记录流水号
     */
	private Integer id;
	
    /**
     *序列号
     */
	private String wheelId;
	
    /**
     *业务ID
     */
	private String bizId;
	
    /**
     *用户编码
     */
	private String userId;
	
    /**
     *中奖投资人手机号码
     */
	private String mobile;
	
    /**
     *中奖等级
     */
	private Integer winningOrder;
	
    /**
     *等级描述
     */
	private String orderDesc;
	
    /**
     *消耗的抽奖次数
     */
	private Integer drawTimes;
	
    /**
     *抽奖方式 1：抽一次 10：抽十次
     */
	private Integer drawType;
	
    /**
     *创建时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date crtTime;
	
    /**
     *更新时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date updateTime;
	
    /**
     *是否发放（0：未发放，1：已发放）
     */
	private Integer issued;
	
    /**
     *发放者
     */
	private String sendOperator;
	
    /**
     *收件地址类型 0：不需要收件地址 1：邮寄地址 2：爱奇艺会员
     */
	private Integer addressType;
	
    /**
     *是否虚拟抽奖数据 1：是 0：否
     */
	private Integer isVirtual;
	
    /**
     *
     */
	private String extends1;
	
    /**
     *
     */
	private String extends2;
	
    /**
     *
     */
	private String extends3;
	


	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return id;
	}
	
	public void setWheelId(String wheelId){
		this.wheelId = wheelId;
	}
	
	public String getWheelId(){
		return wheelId;
	}
	
	public void setBizId(String bizId){
		this.bizId = bizId;
	}
	
	public String getBizId(){
		return bizId;
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
	
	public void setWinningOrder(Integer winningOrder){
		this.winningOrder = winningOrder;
	}
	
	public Integer getWinningOrder(){
		return winningOrder;
	}
	
	public void setOrderDesc(String orderDesc){
		this.orderDesc = orderDesc;
	}
	
	public String getOrderDesc(){
		return orderDesc;
	}
	
	public void setDrawTimes(Integer drawTimes){
		this.drawTimes = drawTimes;
	}
	
	public Integer getDrawTimes(){
		return drawTimes;
	}
	
	public void setDrawType(Integer drawType){
		this.drawType = drawType;
	}
	
	public Integer getDrawType(){
		return drawType;
	}
	
	public void setCrtTime(Date crtTime){
		this.crtTime = crtTime;
	}
	
	public Date getCrtTime(){
		return crtTime;
	}
	
	public void setUpdateTime(Date updateTime){
		this.updateTime = updateTime;
	}
	
	public Date getUpdateTime(){
		return updateTime;
	}
	
	public void setIssued(Integer issued){
		this.issued = issued;
	}
	
	public Integer getIssued(){
		return issued;
	}
	
	public void setSendOperator(String sendOperator){
		this.sendOperator = sendOperator;
	}
	
	public String getSendOperator(){
		return sendOperator;
	}
	
	public void setAddressType(Integer addressType){
		this.addressType = addressType;
	}
	
	public Integer getAddressType(){
		return addressType;
	}
	
	public void setIsVirtual(Integer isVirtual){
		this.isVirtual = isVirtual;
	}
	
	public Integer getIsVirtual(){
		return isVirtual;
	}
	
	public void setExtends1(String extends1){
		this.extends1 = extends1;
	}
	
	public String getExtends1(){
		return extends1;
	}
	
	public void setExtends2(String extends2){
		this.extends2 = extends2;
	}
	
	public String getExtends2(){
		return extends2;
	}
	
	public void setExtends3(String extends3){
		this.extends3 = extends3;
	}
	
	public String getExtends3(){
		return extends3;
	}
	

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}

