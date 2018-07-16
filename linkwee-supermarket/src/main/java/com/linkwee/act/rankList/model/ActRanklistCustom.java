package com.linkwee.act.rankList.model;

import java.io.Serializable;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： ch
 * 
 * @创建时间：2017年02月13日 10:54:15
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class ActRanklistCustom implements Serializable {
	
	private static final long serialVersionUID = 821797670624157409L;
	
    /**
     *
     */
	private Integer id;
	
    /**
     *排行榜编号
     */
	private String ranklistId;
	
    /**
     *排行榜属性key
     */
	private String key;
	
    /**
     *排行榜属性value
     */
	private String value;
	


	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return id;
	}
	
	public void setRanklistId(String ranklistId){
		this.ranklistId = ranklistId;
	}
	
	public String getRanklistId(){
		return ranklistId;
	}
	
	public void setKey(String key){
		this.key = key;
	}
	
	public String getKey(){
		return key;
	}
	
	public void setValue(String value){
		this.value = value;
	}
	
	public String getValue(){
		return value;
	}
	

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}

