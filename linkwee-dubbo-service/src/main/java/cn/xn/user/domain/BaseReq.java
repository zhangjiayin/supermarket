package cn.xn.user.domain;

import java.io.Serializable;

import cn.xn.user.annotation.NotNullField;

public class BaseReq implements Serializable {

	private static final long serialVersionUID = 5274200739957691310L;

	/**
	 * app版本号
	 */
	@NotNullField
	private String appVersion;

	/**
	 * 来源类型
	 */
	@NotNullField
	private String sourceType;

	/**
	 * 系统类型
	 */
	@NotNullField
	private String systemType;
	
	
	/**
	 * 签名信息
	 */
	@NotNullField
	private String sign;
	

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getSourceType() {
		return sourceType;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}

	public String getSystemType() {
		return systemType;
	}

	public void setSystemType(String systemType) {
		this.systemType = systemType;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}
	
}
