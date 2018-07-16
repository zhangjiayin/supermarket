package com.linkwee.api.request.act;

import javax.validation.constraints.NotNull;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * 
 * @描述：检测手机号码 入参
 *
 * @author Bob
 * @时间  2015年8月8日下午2:32:15
 *
 */
public class HelpResultRequest {
	
	/**
	 * 手机号码
	 */
	@NotNull(message="手机号码不能为空")
	private String mobile;
	
	/**
	 * openid
	 */
	@NotNull(message="openid不能为空")
	private String openid;
	
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
}

