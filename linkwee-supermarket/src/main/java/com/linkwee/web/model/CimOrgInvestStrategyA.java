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
* @创建时间：2018年05月10日 16:37:26
*
* Copyright (c) 深圳领会科技有限公司-版权所有
*/
public class CimOrgInvestStrategyA implements Serializable {

   private static final long serialVersionUID = -135100049445809470L;

   /**
    *主键，自增长
    */
   private Integer id;

   /**
    *机构编码
    */
   private String orgNumber;

   /**
    *是否推荐 0-不推荐、1-推荐
    */
   private Integer recommend;

   /**
    *机构投资攻略排名
    */
   private Integer strategyIndex;

   /**
    *起投期限
    */
   private Integer minDeadLine;

   /**
    *起投金额(元)
    */
   private BigDecimal minInvestAmount;

   /**
    *官方红包(元)
    */
   private BigDecimal orgRedpacket;

   /**
    *机构理财收益率
    */
   private BigDecimal orgProductRatio;

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
    *操作人
    */
   private String operator;



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

   public void setRecommend(Integer recommend){
       this.recommend = recommend;
   }

   public Integer getRecommend(){
       return recommend;
   }

   public void setStrategyIndex(Integer strategyIndex){
       this.strategyIndex = strategyIndex;
   }

   public Integer getStrategyIndex(){
       return strategyIndex;
   }

   public void setMinDeadLine(Integer minDeadLine){
       this.minDeadLine = minDeadLine;
   }

   public Integer getMinDeadLine(){
       return minDeadLine;
   }

   public void setMinInvestAmount(BigDecimal minInvestAmount){
       this.minInvestAmount = minInvestAmount;
   }

   public BigDecimal getMinInvestAmount(){
       return minInvestAmount;
   }

   public void setOrgRedpacket(BigDecimal orgRedpacket){
       this.orgRedpacket = orgRedpacket;
   }

   public BigDecimal getOrgRedpacket(){
       return orgRedpacket;
   }

   public void setOrgProductRatio(BigDecimal orgProductRatio){
       this.orgProductRatio = orgProductRatio;
   }

   public BigDecimal getOrgProductRatio(){
       return orgProductRatio;
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

