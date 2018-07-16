package com.linkwee.activity.model;

import java.io.Serializable;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class ExcelModel implements Serializable{
    
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 19584341657913556L;
	/**
	 * 理财师/金服  手机号码
	 */
	private String mobile;

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Override
	public String toString() {
		return  JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}