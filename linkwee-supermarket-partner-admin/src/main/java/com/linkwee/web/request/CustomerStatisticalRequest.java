package com.linkwee.web.request;

import org.hibernate.validator.constraints.NotBlank;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.linkwee.core.datatable.DataTable;

public class CustomerStatisticalRequest extends DataTable{

	@NotBlank(message="理财师手机号码不能为空")
	private String mobile;
	
	private String nameOrMobile;

	

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getNameOrMobile() {
		return nameOrMobile;
	}

	public void setNameOrMobile(String nameOrMobile) {
		this.nameOrMobile = nameOrMobile;
	}

	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}
