package com.linkwee.web.model;

import java.io.Serializable;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2017年07月26日 09:38:45
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class SmGrowthHandbookCfplevelRelation implements Serializable {
	
	private static final long serialVersionUID = 704583774636807122L;
	
    /**
     *流水号
     */
	private Integer id;
	
    /**
     *成长手册编号
     */
	private Integer growthHandbookId;
	
    /**
     *职级代码：TA=见习理财师 | SM1=顾问理财师 | SM2=经理理财师 | SM3=总监理财师
     */
	private String cfpLevelCode;
	


	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return id;
	}
	
	public void setGrowthHandbookId(Integer growthHandbookId){
		this.growthHandbookId = growthHandbookId;
	}
	
	public Integer getGrowthHandbookId(){
		return growthHandbookId;
	}
	
	public void setCfpLevelCode(String cfpLevelCode){
		this.cfpLevelCode = cfpLevelCode;
	}
	
	public String getCfpLevelCode(){
		return cfpLevelCode;
	}
	

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}

