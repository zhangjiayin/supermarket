package com.linkwee.api.response.insurance.qixin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
/**
* 
* @描述： 实体Bean
* 
* @创建人： chenjl
* 
* @创建时间：2018年01月05日 17:10:52
* 
* Copyright (c) 深圳领会科技有限公司-版权所有
*/
public class InsuranceQuestionResultReponse implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	public InsuranceQuestionResultReponse(){
		
	}
	
	/**
	 * 评测分值
	 */
	private String totalScore;
	
	/**
     *风险等级
     */
	private String riskGrade;
	
	/**
     *推荐分类
     */
	private List<InsuranceQuestionRecom> recomList = new ArrayList<InsuranceQuestionRecom>();


	public String getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(String totalScore) {
		this.totalScore = totalScore;
	}

	public String getRiskGrade() {
		return riskGrade;
	}

	public void setRiskGrade(String riskGrade) {
		this.riskGrade = riskGrade;
	}

	public List<InsuranceQuestionRecom> getRecomList() {
		return recomList;
	}

	public void setRecomList(List<InsuranceQuestionRecom> recomList) {
		this.recomList = recomList;
	}

	public String toString()
	{
	  return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}