package com.linkwee.web.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.io.Serializable;

/**
*
* @描述： 实体Bean
*
* @创建人： Hxb
*
* @创建时间：2018年06月07日 16:22:53
*
* Copyright (c) 深圳领会科技有限公司-版权所有
*/
public class CimOrgMemberinfoA implements Serializable {

   private static final long serialVersionUID = -7413707990938256752L;

   /**
    *主键，自增长
    */
   private Integer id;

   /**
    *机构编码
    */
   private String orgNumber;

   /**
    *团队成员头像
    */
   private String orgHeadImg;

   /**
    *团队成员级别描述
    */
   private String orgMemberGrade;

   /**
    *团队成员名称
    */
   private String orgMemberName;

   /**
    *团队成员描述
    */
   private String orgDescribe;

   /**
    *排序
    */
   private Integer sort;

   /**
    *是否有效,0-有效，1-失效
    */
   private Integer isshow;



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

   public void setOrgHeadImg(String orgHeadImg){
       this.orgHeadImg = orgHeadImg;
   }

   public String getOrgHeadImg(){
       return orgHeadImg;
   }

   public void setOrgMemberGrade(String orgMemberGrade){
       this.orgMemberGrade = orgMemberGrade;
   }

   public String getOrgMemberGrade(){
       return orgMemberGrade;
   }

   public void setOrgMemberName(String orgMemberName){
       this.orgMemberName = orgMemberName;
   }

   public String getOrgMemberName(){
       return orgMemberName;
   }

   public void setOrgDescribe(String orgDescribe){
       this.orgDescribe = orgDescribe;
   }

   public String getOrgDescribe(){
       return orgDescribe;
   }

   public void setSort(Integer sort){
       this.sort = sort;
   }

   public Integer getSort(){
       return sort;
   }

   public void setIsshow(Integer isshow){
       this.isshow = isshow;
   }

   public Integer getIsshow(){
       return isshow;
   }


   @Override
   public String toString() {
       return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
   }
}

