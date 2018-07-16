package com.linkwee.web.model;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.annotation.JsonFormat;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2017年03月31日 10:34:09
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class CrmCfpLevel implements Serializable {
	
	private static final long serialVersionUID = 2967948954063285716L;
	
    /**
     *编号
     */
	private Integer id;
	
    /**
     *职级代码
     */
	private String levelCode;
	
    /**
     *职级权重: 见习=10 | 顾问=20 | 经理=30 | 总监=40
     */
	private Integer levelWeight;
	
    /**
     *职级名称
     */
	private String levelName;
	
    /**
     *职级描述
     */
	private String levelRemark;
	
    /**
     *创建时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date createTime;
	


	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return id;
	}
	
	public void setLevelCode(String levelCode){
		this.levelCode = levelCode;
	}
	
	public String getLevelCode(){
		return levelCode;
	}
	
	public void setLevelWeight(Integer levelWeight){
		this.levelWeight = levelWeight;
	}
	
	public Integer getLevelWeight(){
		return levelWeight;
	}
	
	public void setLevelName(String levelName){
		this.levelName = levelName;
	}
	
	public String getLevelName(){
		return levelName;
	}
	
	public void setLevelRemark(String levelRemark){
		this.levelRemark = levelRemark;
	}
	
	public String getLevelRemark(){
		return levelRemark;
	}
	
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	
	public Date getCreateTime(){
		return createTime;
	}
	

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}

