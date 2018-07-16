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
public class MobileForHelpRequest {
	
	/**
	 * 手机号码
	 */
	@NotNull(message="手机号码不能为空")
	private String mobile;
	
	/**
	 * 头像
	 */
	@NotNull(message="头像url不能为空")
	private String weixinIcoUrl;
	
	/**
	 * 昵称
	 */
	@NotNull(message="昵称不能为空")
	private String weixinNickname;
	
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

