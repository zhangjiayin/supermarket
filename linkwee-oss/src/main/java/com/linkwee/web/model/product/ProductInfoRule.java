package com.linkwee.web.model.product;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.linkwee.core.base.BaseEntity;
 /**
 * 
 * 描述： 实体Bean
 * 
 * @创建人： chenchy
 * 
 * @创建时间：2016年06月20日 10:29:59
 * 
 * @Copyright (c) 深圳市小牛科技有限公司-版权所有
 */
public class ProductInfoRule extends BaseEntity {
	
	private static final long serialVersionUID = 1273165627898595274L;
	
    /**
     *分类ID
     */
	private Integer id;
	
    /**
     *分类ID
     */
	private Integer tRuleId;
	
    /**
     *产品分类表主键（日益宝的产品）
     */
	private Integer typeId;
	
    /**
     *规则描述
     */
	private String description;
	


	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return id;
	}
	
	public void setTRuleId(Integer tRuleId){
		this.tRuleId = tRuleId;
	}
	
	public Integer getTRuleId(){
		return tRuleId;
	}
	
	public void setTypeId(Integer typeId){
		this.typeId = typeId;
	}
	
	public Integer getTypeId(){
		return typeId;
	}
	
	public void setDescription(String description){
		this.description = description;
	}
	
	public String getDescription(){
		return description;
	}
	

	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}

