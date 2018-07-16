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
public class CrmCfpPromotionCondition implements Serializable {
	
	private static final long serialVersionUID = 4968251054907534634L;
	
    /**
     *编号
     */
	private Integer id;
	
    /**
     *职级代码
     */
	private String levelCode;
	
    /**
     *最大年化业绩
     */
	private Integer maxYearpurAmount;
	
    /**
     *最小年化业绩
     */
	private Integer minYearpurAmount;
	
    /**
     *团队人数 (暂不使用)
     */
	private Integer teamCount;
	
    /**
     *下级级别代码
     */
	private String childLevelCode;
	
    /**
     *下级级别权重
     */
	private Integer childLevelWeight;
	
    /**
     *下级人数
     */
	private Integer childLevelCount;
	
    /**
     *描述
     */
	private String remark;
	
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
	
	public void setMaxYearpurAmount(Integer maxYearpurAmount){
		this.maxYearpurAmount = maxYearpurAmount;
	}
	
	public Integer getMaxYearpurAmount(){
		return maxYearpurAmount;
	}
	
	public void setMinYearpurAmount(Integer minYearpurAmount){
		this.minYearpurAmount = minYearpurAmount;
	}
	
	public Integer getMinYearpurAmount(){
		return minYearpurAmount;
	}
	
	public void setTeamCount(Integer teamCount){
		this.teamCount = teamCount;
	}
	
	public Integer getTeamCount(){
		return teamCount;
	}
	
	public void setChildLevelCode(String childLevelCode){
		this.childLevelCode = childLevelCode;
	}
	
	public String getChildLevelCode(){
		return childLevelCode;
	}
	
	public void setChildLevelWeight(Integer childLevelWeight){
		this.childLevelWeight = childLevelWeight;
	}
	
	public Integer getChildLevelWeight(){
		return childLevelWeight;
	}
	
	public void setChildLevelCount(Integer childLevelCount){
		this.childLevelCount = childLevelCount;
	}
	
	public Integer getChildLevelCount(){
		return childLevelCount;
	}
	
	public void setRemark(String remark){
		this.remark = remark;
	}
	
	public String getRemark(){
		return remark;
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

