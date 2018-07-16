package com.linkwee.api.request;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.linkwee.core.base.api.PaginatorRequest;

public class GrowthHandbookClassifyRequest extends PaginatorRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5328639077883121455L;
	
	/**
	 * 成才手册类别
	 */
	private String typeCode;
	
	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}

