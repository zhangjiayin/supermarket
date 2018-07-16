package com.linkwee.web.request.tc;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.linkwee.core.datatable.DataTable;

public class UnrecordInvestRequest extends DataTable{
	
	private Integer status;
	private String mobile;
	private String investorsMobiel;
	private String investorsUserName;
	private String startTime;
	private String endTime;
	
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	
	public String getInvestorsMobiel() {
		return investorsMobiel;
	}

	public void setInvestorsMobiel(String investorsMobiel) {
		this.investorsMobiel = investorsMobiel;
	}

	public String getInvestorsUserName() {
		return investorsUserName;
	}

	public void setInvestorsUserName(String investorsUserName) {
		this.investorsUserName = investorsUserName;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}
