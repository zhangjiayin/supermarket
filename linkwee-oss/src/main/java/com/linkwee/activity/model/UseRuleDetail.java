package com.linkwee.activity.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;


public class UseRuleDetail{
	
	private Double min;//最小金额
	private Double max;//最大金额
	private Double redPapers;//红包金额
	private String redPaperTypeId;
	private Integer busType;//业务类型
	private Integer investUser;//投资用户限制 0=所有用户 不限制|1=首次投资用户
	
	public Double getMin() {
		return min;
	}
	public void setMin(Double min) {
		this.min = min;
	}
	public Double getMax() {
		return max;
	}
	public void setMax(Double max) {
		this.max = max;
	}
	
	public Double getRedPapers() {
		return redPapers;
	}
	public void setRedPapers(Double redPapers) {
		this.redPapers = redPapers;
	}
	public String getRedPaperTypeId() {
		return redPaperTypeId;
	}
	public void setRedPaperTypeId(String redPaperTypeId) {
		this.redPaperTypeId = redPaperTypeId;
	}
	public Integer getBusType() {
		return busType;
	}
	public void setBusType(Integer busType) {
		this.busType = busType;
	}
	
	public Integer getInvestUser() {
		return investUser;
	}
	public void setInvestUser(Integer investUser) {
		this.investUser = investUser;
	}
	@Override
	public String toString() {
		return  JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}
