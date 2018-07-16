package com.linkwee.web.model;

import java.io.Serializable;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2018年01月11日 13:56:31
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class CimInsuranceRecommendType implements Serializable {
	
	private static final long serialVersionUID = -2576715388754719529L;
	
    /**
     *主键，自增长
     */
	private Integer id;
	
    /**
     *推荐种类
     */
	private Integer recommendType;
	
    /**
     *推荐种类名称
     */
	private String recommendTypeName;
	


	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return id;
	}
	
	public void setRecommendType(Integer recommendType){
		this.recommendType = recommendType;
	}
	
	public Integer getRecommendType(){
		return recommendType;
	}
	
	public void setRecommendTypeName(String recommendTypeName){
		this.recommendTypeName = recommendTypeName;
	}
	
	public String getRecommendTypeName(){
		return recommendTypeName;
	}
	

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}

