package com.linkwee.test.enums;


public enum AppEnum{
	
	/**
	 * 金服-安卓
	 */
	INVESTOR_ANDROID("App_investor_android","120snkktkznlxczandr"),
	/**
	 * 金服-IOS
	 */
	INVESTOR_IOS("App_investor_ios","120ruyalkcnbfdaxios"),
	/**
	 * 金服-微信
	 */
	INVESTOR_WECHAT("App_investor_wechat","120ertkcbnjtaqdchat"),
	/**
	 * 理财师-安卓
	 */
	CHANNEL_ANDROID("App_channel_android","921snkktkznlxct12"),
	/**
	 * 理财师-IOS
	 */
	CHANNEL_IOS("App_channel_ios","921ruyalkcnbfda13"),
	/**
	 * 理财师-微信
	 */
	CHANNEL_WECHAT("App_channel_wechat","921ertkcbnjtaqd52");
	
	AppEnum(String key,String value){
		this.key = key;
		this.value = value;
	}

	private String key;
	private String value;
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	

}
