package com.linkwee.api.request.acc;

import javax.validation.constraints.NotNull;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class InputVcodeRequest {

	/**
	 *  验证码
	 */
	@NotNull(message = "验证码不能为空")
	private String vcode;
	
	private String mobile;
	
	private Integer type;

	public Integer getType() {
		return type;
	}


	public void setType(Integer type) {
		this.type = type;
	}


	public String getMobile() {
		return mobile;
	}


	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


	public String getVcode() {
		return vcode;
	}


	public void setVcode(String vcode) {
		this.vcode = vcode;
	}


	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}
