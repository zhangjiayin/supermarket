package com.linkwee.web.response.acc;

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2018年2月06日 10:35:00
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class SmBrandPosterResponse implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/**
     *海报类型
     */
	private Integer typeId;
	
    /**
     *海报名字
     */
	private String posterName;

	
	public Integer getTypeId() {
		return typeId;
	}


	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}


	public String getPosterName() {
		return posterName;
	}


	public void setPosterName(String posterName) {
		this.posterName = posterName;
	}


	public String toString()
	{
	  return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}

