package com.linkwee.web.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
*
* @描述： 实体Bean
*
* @创建人： Mignet
*
* @创建时间：2018年04月09日 19:31:02
*
* Copyright (c) 深圳领会科技有限公司-版权所有
*/
public class ActSignInfo implements Serializable {

   private static final long serialVersionUID = 910902619212093404L;

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
    *连续签到天数
    */
   private Integer consecutiveDays;

   /**
    *签到金额
    */
   private BigDecimal signAmount;

   /**
    *转账金额
    */
   private BigDecimal transferAmount;

   /**
    *红包数量
    */
   private Integer redpacketCount;

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
    *最后一次转出时间
    */
   @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
   private Date lastestTransferTime;

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

   public void setConsecutiveDays(Integer consecutiveDays){
       this.consecutiveDays = consecutiveDays;
   }

   public Integer getConsecutiveDays(){
       return consecutiveDays;
   }

   public void setSignAmount(BigDecimal signAmount){
       this.signAmount = signAmount;
   }

   public BigDecimal getSignAmount(){
       return signAmount;
   }

   public void setTransferAmount(BigDecimal transferAmount){
       this.transferAmount = transferAmount;
   }

   public BigDecimal getTransferAmount(){
       return transferAmount;
   }

   public void setRedpacketCount(Integer redpacketCount){
       this.redpacketCount = redpacketCount;
   }

   public Integer getRedpacketCount(){
       return redpacketCount;
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

   public void setLastestTransferTime(Date lastestTransferTime){
       this.lastestTransferTime = lastestTransferTime;
   }

   public Date getLastestTransferTime(){
       return lastestTransferTime;
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

