package com.linkwee.web.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linkwee.core.jackson.MoneySerializer;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
*
* @描述： 实体Bean
*
* @创建人： Mignet
*
* @创建时间：2018年04月03日 14:21:21
*
* 用作返回类型了，不能直接替换 @JsonSerialize(using=MoneySerializer.class)
*
* Copyright (c) 深圳领会科技有限公司-版权所有
*/
public class ActSignRecord implements Serializable {

   private static final long serialVersionUID = -5753235073528662585L;

   /**
    *自增id
    */
   private Integer id;

   /**
    *1理财师，2投资者
    */
   private Integer userType;

   /**
    *客户id
    */
   private String userId;

   /**
    *红包编号
    */
   private String redpacketId;

   /**
    *签到金额
    */
   @JsonSerialize(using=MoneySerializer.class)
   private BigDecimal signAmount;

   /**
    *翻倍金额
    */
   @JsonSerialize(using=MoneySerializer.class)
   private BigDecimal timesAmount;

   /**
    *1分享翻倍，2连续签到翻倍
    */
   private Integer timesType;

   /**
    *
    */
   @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
   private Date signDate;

   /**
    *签到时间
    */
   @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
   private Date signTime;

   /**
    *1已点击领取分享，0未点击领取分享
    */
   private Integer shareStatus;

   /**
    *奖励金类型 1:签到奖励 2:抽奖奖励 3:发帖奖励
    */
   private Integer bountyType;

   /**
    *更新时间
    */
   @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
   private Date updateTime;

   /**
    *扩展字段1
    */
   private String extend1;

   /**
    *扩展字段2
    */
   private String extend2;



   public void setId(Integer id){
       this.id = id;
   }

   public Integer getId(){
       return id;
   }

   public void setUserType(Integer userType){
       this.userType = userType;
   }

   public Integer getUserType(){
       return userType;
   }

   public void setUserId(String userId){
       this.userId = userId;
   }

   public String getUserId(){
       return userId;
   }

   public void setRedpacketId(String redpacketId){
       this.redpacketId = redpacketId;
   }

   public String getRedpacketId(){
       return redpacketId;
   }

   public void setSignAmount(BigDecimal signAmount){
       this.signAmount = signAmount;
   }

   public BigDecimal getSignAmount(){
       return signAmount;
   }

   public void setTimesAmount(BigDecimal timesAmount){
       this.timesAmount = timesAmount;
   }

   public BigDecimal getTimesAmount(){
       return timesAmount;
   }

   public void setTimesType(Integer timesType){
       this.timesType = timesType;
   }

   public Integer getTimesType(){
       return timesType;
   }

   public void setSignDate(Date signDate){
       this.signDate = signDate;
   }

   public Date getSignDate(){
       return signDate;
   }

   public void setSignTime(Date signTime){
       this.signTime = signTime;
   }

   public Date getSignTime(){
       return signTime;
   }

   public void setShareStatus(Integer shareStatus){
       this.shareStatus = shareStatus;
   }

   public Integer getShareStatus(){
       return shareStatus;
   }

   public void setBountyType(Integer bountyType){
       this.bountyType = bountyType;
   }

   public Integer getBountyType(){
       return bountyType;
   }

   public void setUpdateTime(Date updateTime){
       this.updateTime = updateTime;
   }

   public Date getUpdateTime(){
       return updateTime;
   }

   public void setExtend1(String extend1){
       this.extend1 = extend1;
   }

   public String getExtend1(){
       return extend1;
   }

   public void setExtend2(String extend2){
       this.extend2 = extend2;
   }

   public String getExtend2(){
       return extend2;
   }


   @Override
   public String toString() {
       return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
   }
}

