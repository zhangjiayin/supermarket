package com.linkwee.web.request;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.linkwee.core.datatable.DataTable;

public class LcsStatisticalRequest extends DataTable{
	
	private String city;
	private String nameormobile;
	
	
	public String getNameormobile() {
		return nameormobile;
	}
	public void setNameormobile(String nameormobile) {
		this.nameormobile = nameormobile;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}

	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
	
}
