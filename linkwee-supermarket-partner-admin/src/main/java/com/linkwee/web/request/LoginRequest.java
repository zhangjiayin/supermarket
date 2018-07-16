package com.linkwee.web.request;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class LoginRequest {
	
	private String orgAccount;
	private String orgPassword;
	private String captcha;
	
	
	public String getOrgAccount() {
		return orgAccount;
	}
	public void setOrgAccount(String orgAccount) {
		this.orgAccount = orgAccount;
	}
	public String getOrgPassword() {
		return orgPassword;
	}
	public void setOrgPassword(String orgPassword) {
		this.orgPassword = orgPassword;
	}
	public String getCaptcha() {
		return captcha;
	}
	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}
	

	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}

}
