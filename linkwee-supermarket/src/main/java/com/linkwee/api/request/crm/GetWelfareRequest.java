package com.linkwee.api.request.crm;

import javax.validation.constraints.NotNull;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class GetWelfareRequest {

	/**
	 * 用户微信openId
	 */
	@NotNull(message="用户微信openId不能为空")
	private String openId;
	/**
	 * 用户微信昵称
	 */
	@NotNull(message="用户微信昵称不能为空")
	private String nickName;
	/**
	 * 用户微信头像url
	 */
//	@NotNull(message="用户微信头像url不能为空")
	private String headImgUrl;
	/**
	 * 领取福利的手机号(被邀请者)
	 */
	@NotNull(message="领取福利的手机号不能为空")
	private String mobile;
	/**
	 * 邀请理财师手机号码
	 */
	@NotNull(message="邀请理财师手机号码不能为空")
	private String cfpMobile;
	/**
	 * 邀请理财师姓名
	 */
	private String cfpName;
	/**
	 * 邀请理财师userId
	 */
	private String cfpUserId;
	
	public String getCfpUserId() {
		return cfpUserId;
	}

	public void setCfpUserId(String cfpUserId) {
		this.cfpUserId = cfpUserId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getHeadImgUrl() {
		return headImgUrl;
	}

	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCfpMobile() {
		return cfpMobile;
	}

	public void setCfpMobile(String cfpMobile) {
		this.cfpMobile = cfpMobile;
	}

	public String getCfpName() {
		return cfpName;
	}

	public void setCfpName(String cfpName) {
		this.cfpName = cfpName;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}
