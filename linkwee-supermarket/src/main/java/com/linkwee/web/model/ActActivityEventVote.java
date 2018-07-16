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
* @创建时间：2018年05月23日 10:56:23
*
* Copyright (c) 深圳领会科技有限公司-版权所有
*/
public class ActActivityEventVote implements Serializable {

   private static final long serialVersionUID = 2316537277903410887L;

   /**
    *自增长主键
    */
   private Integer id;

   /**
    *投票id
    */
   private String voteId;

   /**
    *竞猜id
    */
   private Integer guessId;

   /**
    *用户编码
    */
   private String userId;

   /**
    *手机号码
    */
   private String mobile;

   /**
    *支持投票方
    */
   private String supportVote;

   /**
    *消耗的投票次数
    */
   private Integer consumeTimes;

   /**
    *是否虚拟数据 1：是 0：否
    */
   private Integer isVirtual;

   /**
    *票数
    */
   private Integer voteNumber;

   /**
    *投票时间
    */
   @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
   private Date voteTime;

   /**
    *奖励
    */
   private BigDecimal reward;

   /**
    *是否发放（0：未发放，1：已发放）
    */
   private Integer issued;

   /**
    *发放者
    */
   private String sendOperator;

   /**
    *扩展字段1
    */
   private String extends1;

   /**
    *扩展字段2
    */
   private String extends2;

   /**
    *扩展字段3
    */
   private String extends3;



   public void setId(Integer id){
       this.id = id;
   }

   public Integer getId(){
       return id;
   }

   public void setVoteId(String voteId){
       this.voteId = voteId;
   }

   public String getVoteId(){
       return voteId;
   }

   public void setGuessId(Integer guessId){
       this.guessId = guessId;
   }

   public Integer getGuessId(){
       return guessId;
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

   public void setSupportVote(String supportVote){
       this.supportVote = supportVote;
   }

   public String getSupportVote(){
       return supportVote;
   }

   public void setConsumeTimes(Integer consumeTimes){
       this.consumeTimes = consumeTimes;
   }

   public Integer getConsumeTimes(){
       return consumeTimes;
   }

   public void setIsVirtual(Integer isVirtual){
       this.isVirtual = isVirtual;
   }

   public Integer getIsVirtual(){
       return isVirtual;
   }

   public void setVoteNumber(Integer voteNumber){
       this.voteNumber = voteNumber;
   }

   public Integer getVoteNumber(){
       return voteNumber;
   }

   public void setVoteTime(Date voteTime){
       this.voteTime = voteTime;
   }

   public Date getVoteTime(){
       return voteTime;
   }

   public void setReward(BigDecimal reward){
       this.reward = reward;
   }

   public BigDecimal getReward(){
       return reward;
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

