package cn.xn.user.domain;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class DeviceInfoRlt implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3928350223301860801L;

	/**
	 * 用户编码
	 */
	private String memberNo;
	
	/**
	 * 设备ID
	 */
	private String deviceId;

	/**
	 * 设备详情
	 */
	private String deviceDetail;

	/**
	 * 设备类型
	 */
	private String deviceType;

	/**
	 * 设备分辨率
	 */
	private String deviceResolution;
	
	/**
	 * 手机系统版本
	 */
	private String systemVersion;	
	
	/**
	 * 消息推送token
	 */
	private String token;

	/**
	 * app版本号
	 */
	private String appVersion;
	
	/**
	 * 最新登录时间
	 */
	private Date loginLastTime;

	
	public String getSystemVersion() {
		return systemVersion;
	}

	public void setSystemVersion(String systemVersion) {
		this.systemVersion = systemVersion;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getLoginLastTime() {
		return loginLastTime;
	}

	public void setLoginLastTime(Date loginLastTime) {
		this.loginLastTime = loginLastTime;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	public String getDeviceDetail() {
		return deviceDetail;
	}

	public void setDeviceDetail(String deviceDetail) {
		this.deviceDetail = deviceDetail;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getDeviceResolution() {
		return deviceResolution;
	}

	public void setDeviceResolution(String deviceResolution) {
		this.deviceResolution = deviceResolution;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
