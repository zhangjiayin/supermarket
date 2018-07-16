package com.linkwee.api.response.insurance.qixin;

import java.io.Serializable;

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
public class InsuranceQuestionRecom implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	public InsuranceQuestionRecom(){
		
	}
	
	/**
     *推荐分类
     */
	private String recomCategory;

	/**
     *推荐图片
     */
	private String categoryImage;
	

	public String getRecomCategory() {
		return recomCategory;
	}

	public void setRecomCategory(String recomCategory) {
		this.recomCategory = recomCategory;
	}

	public String getCategoryImage() {
		return categoryImage;
	}

	public void setCategoryImage(String categoryImage) {
		this.categoryImage = categoryImage;
	}

	public String toString()
	{
	  return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}