package com.linkwee.api.request.act;

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
public class MobileRequest {
	
	/**
	 * 手机号码
	 */
	private String mobile;
	
	/**
	 * 头像
	 */
	private String weixinIcoUrl;
	
	/**
	 * 昵称
	 */
	private String weixinNickname;
	
	/**
	 * openid
	 */
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
	public String getWeixinIcoUrl() {
		return weixinIcoUrl;
	}
	public void setWeixinIcoUrl(String weixinIcoUrl) {
		this.weixinIcoUrl = weixinIcoUrl;
	}
	public String getWeixinNickname() {
		return weixinNickname;
	}
	public void setWeixinNickname(String weixinNickname) {
		this.weixinNickname = weixinNickname;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
}

