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
public class CrmCfpLevelRewardRate implements Serializable {
	
	private static final long serialVersionUID = -6055761489867115834L;
	
    /**
     *编号
     */
	private Integer id;
	
    /**
     *职级代码
     */
	private String levelCode;
	
    /**
     *基础佣金百分比
     */
	private Integer baseFeeRate;
	
    /**
     *推荐佣金佣金百分比
     */
	private Integer recommendRate;
	
    /**
     *子级津贴百分比
     */
	private Integer childAllowanceRate;
	
    /**
     *团队津贴百分比
     */
	private Integer teamAllowanceRate;
	
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
	
	public void setBaseFeeRate(Integer baseFeeRate){
		this.baseFeeRate = baseFeeRate;
	}
	
	public Integer getBaseFeeRate(){
		return baseFeeRate;
	}
	
	public void setRecommendRate(Integer recommendRate){
		this.recommendRate = recommendRate;
	}
	
	public Integer getRecommendRate(){
		return recommendRate;
	}
	
	public void setChildAllowanceRate(Integer childAllowanceRate){
		this.childAllowanceRate = childAllowanceRate;
	}
	
	public Integer getChildAllowanceRate(){
		return childAllowanceRate;
	}
	
	public void setTeamAllowanceRate(Integer teamAllowanceRate){
		this.teamAllowanceRate = teamAllowanceRate;
	}
	
	public Integer getTeamAllowanceRate(){
		return teamAllowanceRate;
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

