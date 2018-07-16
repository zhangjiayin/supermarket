package com.linkwee.web.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
 import java.lang.Integer;
 import java.lang.String;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2018年01月07日 11:46:05
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class CimInsuranceRecommend implements Serializable {
	
	private static final long serialVersionUID = -4707222435354857817L;
	
    /**
     *主键，自增长
     */
	private Integer id;
	
    /**
     *产品编号
     */
	private String productId;
	
    /**
     *推荐种类
     */
	private Integer recommendType;
	


	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return id;
	}
	
	public void setProductId(String productId){
		this.productId = productId;
	}
	
	public String getProductId(){
		return productId;
	}
	
	public void setRecommendType(Integer recommendType){
		this.recommendType = recommendType;
	}
	
	public Integer getRecommendType(){
		return recommendType;
	}
	

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}

