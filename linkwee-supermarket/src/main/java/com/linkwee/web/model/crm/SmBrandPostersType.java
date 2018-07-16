package com.linkwee.web.model.crm;

import java.io.Serializable;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2018年01月06日 15:15:27
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class SmBrandPostersType implements Serializable {
	
	private static final long serialVersionUID = 5391091219903489122L;
	
    /**
     *海报类型:1:推荐2:正能量3:理念4:节日
     */
	private Integer typeValue;
	
    /**
     *名字
     */
	private String name;

	public Integer getTypeValue() {
		return typeValue;
	}

	public void setTypeValue(Integer typeValue) {
		this.typeValue = typeValue;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}

