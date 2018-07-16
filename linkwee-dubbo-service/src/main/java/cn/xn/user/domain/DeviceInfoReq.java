package cn.xn.user.domain;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import cn.xn.user.annotation.NotNullField;

public class DeviceInfoReq extends BaseReq {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3918350223301860801L;

	/**
	 * 用户编码
	 */
	@NotNullField
	private String memberNo;
	
	/**
	 * 设备ID
	 */
	private String deviceId;

	/**
	 * 设备型号，如iPhone
	 */
	private String deviceDetail;

	/**
	 * 设备类型（还不确定,暂时不用填）
	 */
	private String deviceType;

	/**
	 * 设备分辨率 如1242*2208
	 */ 
	private String deviceResolution;
	
	/**
	 * 系统版本 如8.1
	 */
	private String systemVersion;
	
	/**
	 * 消息推送token
	 */
	private String token;

	/**
	 * 推送的token
	 */
	private String deviceToken;
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getSystemVersion() {
		return systemVersion;
	}

	public void setSystemVersion(String systemVersion) {
		this.systemVersion = systemVersion;
	}

	public String getDeviceToken() {
		return deviceToken;
	}

	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
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

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this,ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
