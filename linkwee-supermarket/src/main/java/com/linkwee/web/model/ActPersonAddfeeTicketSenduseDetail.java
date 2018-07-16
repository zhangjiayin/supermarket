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
* @创建人： Hxb
*
* @创建时间：2018年04月19日 09:33:40
*
* Copyright (c) 深圳领会科技有限公司-版权所有
*/
public class ActPersonAddfeeTicketSenduseDetail implements Serializable {

   private static final long serialVersionUID = -4886079845074011953L;

   /**
    *
    */
   private Integer id;

   /**
    *用户id
    */
   private String userId;

   /**
    *加拥券编号
    */
   private String ticketId;

   /**
    *加拥券发放编号
    */
   private String ticketSendId;

   /**
    *投资记录id
    */
   private String investId;

   /**
    *实际佣金
    */
   private BigDecimal feeAmount;

   /**
    *实际加佣天数
    */
   private Integer addFeeDay;

   /**
    *生效时间
    */
   @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
   private Date validBeginTime;

   /**
    *过期时间
    */
   @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
   private Date validEndTime;

   /**
    *发放时间
    */
   @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
   private Date sendTime;

   /**
    *操作人
    */
   private String operator;

   /**
    *投资使用时间
    */
   @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
   private Date investUseTime;

   /**
    *创建时间
    */
   @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
   private Date createTime;

   /**
    *备注
    */
   private String remark;

   /**
    *更新时间
    */
   @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
   private Date updateTime;



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

   public void setTicketId(String ticketId){
       this.ticketId = ticketId;
   }

   public String getTicketId(){
       return ticketId;
   }

   public void setTicketSendId(String ticketSendId){
       this.ticketSendId = ticketSendId;
   }

   public String getTicketSendId(){
       return ticketSendId;
   }

   public void setInvestId(String investId){
       this.investId = investId;
   }

   public String getInvestId(){
       return investId;
   }

   public void setFeeAmount(BigDecimal feeAmount){
       this.feeAmount = feeAmount;
   }

   public BigDecimal getFeeAmount(){
       return feeAmount;
   }

   public void setAddFeeDay(Integer addFeeDay){
       this.addFeeDay = addFeeDay;
   }

   public Integer getAddFeeDay(){
       return addFeeDay;
   }

   public void setValidBeginTime(Date validBeginTime){
       this.validBeginTime = validBeginTime;
   }

   public Date getValidBeginTime(){
       return validBeginTime;
   }

   public void setValidEndTime(Date validEndTime){
       this.validEndTime = validEndTime;
   }

   public Date getValidEndTime(){
       return validEndTime;
   }

   public void setSendTime(Date sendTime){
       this.sendTime = sendTime;
   }

   public Date getSendTime(){
       return sendTime;
   }

   public void setOperator(String operator){
       this.operator = operator;
   }

   public String getOperator(){
       return operator;
   }

   public void setInvestUseTime(Date investUseTime){
       this.investUseTime = investUseTime;
   }

   public Date getInvestUseTime(){
       return investUseTime;
   }

   public void setCreateTime(Date createTime){
       this.createTime = createTime;
   }

   public Date getCreateTime(){
       return createTime;
   }

   public void setRemark(String remark){
       this.remark = remark;
   }

   public String getRemark(){
       return remark;
   }

   public void setUpdateTime(Date updateTime){
       this.updateTime = updateTime;
   }

   public Date getUpdateTime(){
       return updateTime;
   }


   @Override
   public String toString() {
       return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
   }
}

