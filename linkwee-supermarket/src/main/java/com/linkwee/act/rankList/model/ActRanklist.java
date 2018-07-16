package com.linkwee.act.rankList.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
 import java.lang.Integer;
 import java.lang.String;
 import java.util.Date;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： ch
 * 
 * @创建时间：2017年02月13日 10:52:40
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class ActRanklist implements Serializable {
	
	private static final long serialVersionUID = 6857172905695974431L;
	
    /**
     *
     */
	private Integer id;
	
    /**
     *排行榜编号
     */
	private String ranklistId;
	
	private String ranklistCode;
	
    /**
     *排行榜名称
     */
	private String ranklistName;
	
    /**
     *创建时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date createTime;
	
    /**
     *更新时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date updateTime;
	
    /**
     *操作人
     */
	private String operator;
	


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
	
	
	
	public String getRanklistCode() {
		return ranklistCode;
	}

	public void setRanklistCode(String ranklistCode) {
		this.ranklistCode = ranklistCode;
	}

	public void setRanklistName(String ranklistName){
		this.ranklistName = ranklistName;
	}
	
	public String getRanklistName(){
		return ranklistName;
	}
	
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	
	public Date getCreateTime(){
		return createTime;
	}
	
	public void setUpdateTime(Date updateTime){
		this.updateTime = updateTime;
	}
	
	public Date getUpdateTime(){
		return updateTime;
	}
	
	public void setOperator(String operator){
		this.operator = operator;
	}
	
	public String getOperator(){
		return operator;
	}
	

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}

