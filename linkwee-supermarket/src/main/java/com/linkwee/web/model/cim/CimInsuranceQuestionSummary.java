package com.linkwee.web.model.cim;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.linkwee.api.response.insurance.qixin.InsuranceQuestionRecom;

import java.io.Serializable;
 import java.lang.Integer;
 import java.lang.Long;
 import java.lang.String;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2017年12月29日 15:37:10
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class CimInsuranceQuestionSummary implements Serializable {
	
	private static final long serialVersionUID = -7058561060320087940L;
	
    /**
     *自增ID
     */
	private Long id;
	
    /**
     *题目ID
     */
	private String questionId;
	
    /**
     *用户ID
     */
	private String userId;
	
    /**
     *性别
     */
	private String sex;
	
    /**
     *年龄
     */
	private String age;
	
    /**
     *家庭成员
     */
	private String familyMember;
	
    /**
     *年收入
     */
	private String yearIncome;
	
    /**
     *家庭贷款
     */
	private String familyLoan;
	
    /**
     *家庭保障
     */
	private String familyEnsure;
	
    /**
     *风险等级
     */
	private String riskGrade;
	
    /**
     *总分值
     */
	private Integer totalScore;
	
    /**
     *备注
     */
	private String remark;
	
    /**
     *创建时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date createDate;
	
	/**
     *推荐分类
     */
	private List<InsuranceQuestionRecom> recomList = new ArrayList<InsuranceQuestionRecom>();
	
	public List<InsuranceQuestionRecom> getRecomList() {
		return recomList;
	}

	public void setRecomList(List<InsuranceQuestionRecom> recomList) {
		this.recomList = recomList;
	}

	public void setId(Long id){
		this.id = id;
	}
	
	public Long getId(){
		return id;
	}
	
	public void setQuestionId(String questionId){
		this.questionId = questionId;
	}
	
	public String getQuestionId(){
		return questionId;
	}
	
	public void setUserId(String userId){
		this.userId = userId;
	}
	
	public String getUserId(){
		return userId;
	}
	
	public void setSex(String sex){
		this.sex = sex;
	}
	
	public String getSex(){
		return sex;
	}
	
	public void setAge(String age){
		this.age = age;
	}
	
	public String getAge(){
		return age;
	}
	
	public void setFamilyMember(String familyMember){
		this.familyMember = familyMember;
	}
	
	public String getFamilyMember(){
		return familyMember;
	}
	
	public void setYearIncome(String yearIncome){
		this.yearIncome = yearIncome;
	}
	
	public String getYearIncome(){
		return yearIncome;
	}
	
	public void setFamilyLoan(String familyLoan){
		this.familyLoan = familyLoan;
	}
	
	public String getFamilyLoan(){
		return familyLoan;
	}
	
	public void setFamilyEnsure(String familyEnsure){
		this.familyEnsure = familyEnsure;
	}
	
	public String getFamilyEnsure(){
		return familyEnsure;
	}
	
	public void setRiskGrade(String riskGrade){
		this.riskGrade = riskGrade;
	}
	
	public String getRiskGrade(){
		return riskGrade;
	}
	
	public void setTotalScore(Integer totalScore){
		this.totalScore = totalScore;
	}
	
	public Integer getTotalScore(){
		return totalScore;
	}
	
	public void setRemark(String remark){
		this.remark = remark;
	}
	
	public String getRemark(){
		return remark;
	}
	
	public void setCreateDate(Date createDate){
		this.createDate = createDate;
	}
	
	public Date getCreateDate(){
		return createDate;
	}
	
}

