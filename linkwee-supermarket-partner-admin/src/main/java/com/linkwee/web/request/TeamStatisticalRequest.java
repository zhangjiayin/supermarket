package com.linkwee.web.request;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.linkwee.core.datatable.DataTable;

public class TeamStatisticalRequest extends DataTable{
	
	private String city;
	private String platfrom;
	private String nameOrMobile;
	private String startDate;
	private String endDate;
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPlatfrom() {
		return platfrom;
	}
	public void setPlatfrom(String platfrom) {
		this.platfrom = platfrom;
	}
	public String getNameOrMobile() {
		return nameOrMobile;
	}
	public void setNameOrMobile(String nameOrMobile) {
		this.nameOrMobile = nameOrMobile;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	

	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}

}
