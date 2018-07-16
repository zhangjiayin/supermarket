package cn.xn.user.enums;

public enum SourceType {

	/**
	 * PCWeb
	 */
	WECHAT("wechat"),

	/**
	 * ANDROID
	 */
	ANDROID("android"),

	/**
	 * IOS
	 */
	IOS("ios"),
	
	/**
	 * WXWeb
	 */
	WAP("wap"),
	
	/**
	 * PhoneWeb
	 */
	WEB("web");

	private String text;

	private SourceType(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
