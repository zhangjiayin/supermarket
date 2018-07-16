package com.linkwee.api.response.activity;

import com.linkwee.core.base.BaseEntity;

/**
 * 
 * @描述：助力加息
 *
 *
 */
public class HelpRaiseRateDetailRespspne extends BaseEntity {
	private static final long serialVersionUID = 3405963387464644389L;

	/**
	 * 用户名
	 */
	private String weixinIcoUrl;
	/**
	 * 手机号码
	 */
	private String weixinNickname;
	
	/**
	 * 手机号码
	 */
	private String decription;
	
	/**
	 * 已加息
	 */
	private String helpRate;
	
	/**
	 * 助力时间
	 */
	private String helpTime;

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

	public String getDecription() {
		return decription;
	}

	public void setDecription(String decription) {
		this.decription = decription;
	}

	public String getHelpRate() {
		return helpRate;
	}

	public void setHelpRate(String helpRate) {
		this.helpRate = helpRate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getHelpTime() {
		return helpTime;
	}

	public void setHelpTime(String helpTime) {
		this.helpTime = helpTime;
	}
	
	
	

	
}
