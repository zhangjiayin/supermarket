package com.linkwee.activity.model;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class RedpacketRule implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1960280117961091032L;
	
	private String redpaperTypeId;//红包类型编号
	private Double redpaperMoney;//红包金额
	private Date validDate;//有效时间
	private Integer busType;//业务类型
	
	public RedpacketRule() {}
	
	public RedpacketRule(Double redpacketMoney,Integer busType) {
		this.redpaperMoney=redpacketMoney;
		this.busType=busType;
	}
	
	public RedpacketRule(Double redpacketMoney,Integer busType,String redpacketTypeId) {
		this.redpaperMoney =redpacketMoney;
		this.redpaperTypeId=redpacketTypeId;
		this.busType=busType;
	}
	
	public RedpacketRule(Double redpacketMoney,String redpacketTypeId,Date valid,Integer busType) {
		this.redpaperMoney =redpacketMoney;
		this.redpaperTypeId=redpacketTypeId;
		this.validDate=valid;
		this.busType=busType;
		
	}

	
	
	public String getRedpaperTypeId() {
		return redpaperTypeId;
	}

	public void setRedpaperTypeId(String redpaperTypeId) {
		this.redpaperTypeId = redpaperTypeId;
	}

	public Double getRedpaperMoney() {
		return redpaperMoney;
	}

	public void setRedpaperMoney(Double redpaperMoney) {
		this.redpaperMoney = redpaperMoney;
	}

	

	public Date getValidDate() {
		return validDate;
	}

	public void setValidDate(Date validDate) {
		this.validDate = validDate;
	}

	public Integer getBusType() {
		return busType;
	}

	public void setBusType(Integer busType) {
		this.busType = busType;
	}
	
	@Override
	public String toString() {
		return  JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}

}
