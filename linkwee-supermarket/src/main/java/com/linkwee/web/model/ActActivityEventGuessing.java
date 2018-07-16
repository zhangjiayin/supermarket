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
* @创建时间：2018年05月23日 11:32:44
*
* Copyright (c) 深圳领会科技有限公司-版权所有
*/
public class ActActivityEventGuessing implements Serializable {

   private static final long serialVersionUID = 2844452757522638288L;

   /**
    *自增长主键
    */
   private Integer id;

   /**
    *活动编码
    */
   private String activityCode;

   /**
    *奖励发放状态(0:未发放，1:已发放)
    */
   private Integer grantStatus;

   /**
    *发放奖励需要的得分
    */
   private Integer couldGrantScore;

   /**
    *奖池
    */
   private BigDecimal jackpot;

   /**
    *比赛方A
    */
   private String competitionPartyA;

   /**
    *比赛方B
    */
   private String competitionPartyB;

   /**
    *A方得分
    */
   private Integer scoreA;

   /**
    *B方得分
    */
   private Integer scoreB;

   /**
    *A方支持票数
    */
   private Integer supportVotesA;

   /**
    *B方支持票数
    */
   private Integer supportVotesB;

   /**
    *A方支持目标票数
    */
   private Integer supportVotesTargetA;

   /**
    *B方支持目标票数
    */
   private Integer supportVotesTargetB;

   /**
    *A方支持票数增长率
    */
   private Integer growthRateA;

   /**
    *B方支持票数增长率
    */
   private Integer growthRateB;

   /**
    *下一场开赛时间
    */
   @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
   private Date nextStartTime;

   /**
    *投票开始时间
    */
   @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
   private Date voteStartTime;

   /**
    *投票结束时间
    */
   @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
   private Date voteEndTime;

   /**
    *创建时间
    */
   @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
   private Date createTime;

   /**
    *更新日期
    */
   @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
   private Date updateTime;

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

   public void setActivityCode(String activityCode){
       this.activityCode = activityCode;
   }

   public String getActivityCode(){
       return activityCode;
   }

   public void setGrantStatus(Integer grantStatus){
       this.grantStatus = grantStatus;
   }

   public Integer getGrantStatus(){
       return grantStatus;
   }

   public void setCouldGrantScore(Integer couldGrantScore){
       this.couldGrantScore = couldGrantScore;
   }

   public Integer getCouldGrantScore(){
       return couldGrantScore;
   }

   public void setJackpot(BigDecimal jackpot){
       this.jackpot = jackpot;
   }

   public BigDecimal getJackpot(){
       return jackpot;
   }

   public void setCompetitionPartyA(String competitionPartyA){
       this.competitionPartyA = competitionPartyA;
   }

   public String getCompetitionPartyA(){
       return competitionPartyA;
   }

   public void setCompetitionPartyB(String competitionPartyB){
       this.competitionPartyB = competitionPartyB;
   }

   public String getCompetitionPartyB(){
       return competitionPartyB;
   }

   public void setScoreA(Integer scoreA){
       this.scoreA = scoreA;
   }

   public Integer getScoreA(){
       return scoreA;
   }

   public void setScoreB(Integer scoreB){
       this.scoreB = scoreB;
   }

   public Integer getScoreB(){
       return scoreB;
   }

   public void setSupportVotesA(Integer supportVotesA){
       this.supportVotesA = supportVotesA;
   }

   public Integer getSupportVotesA(){
       return supportVotesA;
   }

   public void setSupportVotesB(Integer supportVotesB){
       this.supportVotesB = supportVotesB;
   }

   public Integer getSupportVotesB(){
       return supportVotesB;
   }

   public void setSupportVotesTargetA(Integer supportVotesTargetA){
       this.supportVotesTargetA = supportVotesTargetA;
   }

   public Integer getSupportVotesTargetA(){
       return supportVotesTargetA;
   }

   public void setSupportVotesTargetB(Integer supportVotesTargetB){
       this.supportVotesTargetB = supportVotesTargetB;
   }

   public Integer getSupportVotesTargetB(){
       return supportVotesTargetB;
   }

   public void setGrowthRateA(Integer growthRateA){
       this.growthRateA = growthRateA;
   }

   public Integer getGrowthRateA(){
       return growthRateA;
   }

   public void setGrowthRateB(Integer growthRateB){
       this.growthRateB = growthRateB;
   }

   public Integer getGrowthRateB(){
       return growthRateB;
   }

   public void setNextStartTime(Date nextStartTime){
       this.nextStartTime = nextStartTime;
   }

   public Date getNextStartTime(){
       return nextStartTime;
   }

   public void setVoteStartTime(Date voteStartTime){
       this.voteStartTime = voteStartTime;
   }

   public Date getVoteStartTime(){
       return voteStartTime;
   }

   public void setVoteEndTime(Date voteEndTime){
       this.voteEndTime = voteEndTime;
   }

   public Date getVoteEndTime(){
       return voteEndTime;
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

