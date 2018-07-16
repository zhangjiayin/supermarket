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
* @创建时间：2018年07月10日 11:51:55
*
* Copyright (c) 深圳领会科技有限公司-版权所有
*/
public class CimOrgInfoA implements Serializable {

   private static final long serialVersionUID = -1641530029606053335L;

   /**
    *主键，自增长
    */
   private Integer id;

   /**
    *机构编码-不重复字段
    */
   private String orgNumber;

   /**
    *机构名称
    */
   private String name;

   /**
    *主页平台logo
    */
   private String platformIco;

   /**
    *平台列表logo
    */
   private String platformlistIco;

   /**
    *列表推荐，是否列表推荐 0-不推荐、1-推荐
    */
   private Integer listRecommend;

   /**
    *安全评级 1-B,2-BB,3-BBB,4-A,5-AA,6-AAA
    */
   private String grade;

   /**
    *机构列表排名
    */
   private Integer top;

   /**
    *合作状态.0-合作结束，1-合作中
    */
   private Integer status;

   /**
    *备注
    */
   private String remark;

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
    *最小年化收益
    */
   private BigDecimal minProfit;

   /**
    *最大年化收益
    */
   private BigDecimal maxProfit;

   /**
    *机构跳转url
    */
   private String orgUrl;

   /**
    *机构返现率
    */
   private BigDecimal orgFeeRatio;

   /**
    *创建人
    */
   private String orgCreator;

   /**
    *修改人
    */
   private String orgUpdater;

   /**
    *机构亮点介绍(多个以英文逗号分隔)
    */
   private String orgAdvantage;

   /**
    *机构自定义标签(多个以英文逗号分隔)
    */
   private String orgTag;

   /**
    *机构灰度状态(0:否，1:是)
    */
   private Integer orgGrayStatus;

   /**
    *分享标题
    */
   private String shareTitle;

   /**
    *分享描述
    */
   private String shareDesc;

   /**
    *分享图标
    */
   private String shareIcon;

   /**
    *分享链接
    */
   private String shareLink;

   /**
    *奖励规则
    */
   private String rewardRule;

   /**
    *注册资本
    */
   private String capital;

   /**
    *上线时间
    */
   @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
   private Date upTime;

   /**
    *所在城市
    */
   private String city;

   /**
    *icp备案
    */
   private String icpFiling;

   /**
    *联系方式
    */
   private String contact;

   /**
    *机构简介
    */
   private String orgProfile;

   /**
    *一键注册开启状态(1：已开启 ,0：未开启)
    */
   private Integer orgRegisterStatus;

   /**
    *绑定用户地址
    */
   private String orgBindUserUrl;

   /**
    *机构用户是否存在接口
    */
   private String orgUserExistUrl;

   /**
    *风控简报图片
    */
   private String riskManagementImg;

   /**
    *pc端机构跳转url
    */
   private String pcOrgJumpUrl;



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

   public void setName(String name){
       this.name = name;
   }

   public String getName(){
       return name;
   }

   public void setPlatformIco(String platformIco){
       this.platformIco = platformIco;
   }

   public String getPlatformIco(){
       return platformIco;
   }

   public void setPlatformlistIco(String platformlistIco){
       this.platformlistIco = platformlistIco;
   }

   public String getPlatformlistIco(){
       return platformlistIco;
   }

   public void setListRecommend(Integer listRecommend){
       this.listRecommend = listRecommend;
   }

   public Integer getListRecommend(){
       return listRecommend;
   }

   public void setGrade(String grade){
       this.grade = grade;
   }

   public String getGrade(){
       return grade;
   }

   public void setTop(Integer top){
       this.top = top;
   }

   public Integer getTop(){
       return top;
   }

   public void setStatus(Integer status){
       this.status = status;
   }

   public Integer getStatus(){
       return status;
   }

   public void setRemark(String remark){
       this.remark = remark;
   }

   public String getRemark(){
       return remark;
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

   public void setMinProfit(BigDecimal minProfit){
       this.minProfit = minProfit;
   }

   public BigDecimal getMinProfit(){
       return minProfit;
   }

   public void setMaxProfit(BigDecimal maxProfit){
       this.maxProfit = maxProfit;
   }

   public BigDecimal getMaxProfit(){
       return maxProfit;
   }

   public void setOrgUrl(String orgUrl){
       this.orgUrl = orgUrl;
   }

   public String getOrgUrl(){
       return orgUrl;
   }

   public void setOrgFeeRatio(BigDecimal orgFeeRatio){
       this.orgFeeRatio = orgFeeRatio;
   }

   public BigDecimal getOrgFeeRatio(){
       return orgFeeRatio;
   }

   public void setOrgCreator(String orgCreator){
       this.orgCreator = orgCreator;
   }

   public String getOrgCreator(){
       return orgCreator;
   }

   public void setOrgUpdater(String orgUpdater){
       this.orgUpdater = orgUpdater;
   }

   public String getOrgUpdater(){
       return orgUpdater;
   }

   public void setOrgAdvantage(String orgAdvantage){
       this.orgAdvantage = orgAdvantage;
   }

   public String getOrgAdvantage(){
       return orgAdvantage;
   }

   public void setOrgTag(String orgTag){
       this.orgTag = orgTag;
   }

   public String getOrgTag(){
       return orgTag;
   }

   public void setOrgGrayStatus(Integer orgGrayStatus){
       this.orgGrayStatus = orgGrayStatus;
   }

   public Integer getOrgGrayStatus(){
       return orgGrayStatus;
   }

   public void setShareTitle(String shareTitle){
       this.shareTitle = shareTitle;
   }

   public String getShareTitle(){
       return shareTitle;
   }

   public void setShareDesc(String shareDesc){
       this.shareDesc = shareDesc;
   }

   public String getShareDesc(){
       return shareDesc;
   }

   public void setShareIcon(String shareIcon){
       this.shareIcon = shareIcon;
   }

   public String getShareIcon(){
       return shareIcon;
   }

   public void setShareLink(String shareLink){
       this.shareLink = shareLink;
   }

   public String getShareLink(){
       return shareLink;
   }

   public void setRewardRule(String rewardRule){
       this.rewardRule = rewardRule;
   }

   public String getRewardRule(){
       return rewardRule;
   }

   public void setCapital(String capital){
       this.capital = capital;
   }

   public String getCapital(){
       return capital;
   }

   public void setUpTime(Date upTime){
       this.upTime = upTime;
   }

   public Date getUpTime(){
       return upTime;
   }

   public void setCity(String city){
       this.city = city;
   }

   public String getCity(){
       return city;
   }

   public void setIcpFiling(String icpFiling){
       this.icpFiling = icpFiling;
   }

   public String getIcpFiling(){
       return icpFiling;
   }

   public void setContact(String contact){
       this.contact = contact;
   }

   public String getContact(){
       return contact;
   }

   public void setOrgProfile(String orgProfile){
       this.orgProfile = orgProfile;
   }

   public String getOrgProfile(){
       return orgProfile;
   }

   public void setOrgRegisterStatus(Integer orgRegisterStatus){
       this.orgRegisterStatus = orgRegisterStatus;
   }

   public Integer getOrgRegisterStatus(){
       return orgRegisterStatus;
   }

   public void setOrgBindUserUrl(String orgBindUserUrl){
       this.orgBindUserUrl = orgBindUserUrl;
   }

   public String getOrgBindUserUrl(){
       return orgBindUserUrl;
   }

   public void setOrgUserExistUrl(String orgUserExistUrl){
       this.orgUserExistUrl = orgUserExistUrl;
   }

   public String getOrgUserExistUrl(){
       return orgUserExistUrl;
   }

   public void setRiskManagementImg(String riskManagementImg){
       this.riskManagementImg = riskManagementImg;
   }

   public String getRiskManagementImg(){
       return riskManagementImg;
   }

   public void setPcOrgJumpUrl(String pcOrgJumpUrl){
       this.pcOrgJumpUrl = pcOrgJumpUrl;
   }

   public String getPcOrgJumpUrl(){
       return pcOrgJumpUrl;
   }


   @Override
   public String toString() {
       return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
   }
}

