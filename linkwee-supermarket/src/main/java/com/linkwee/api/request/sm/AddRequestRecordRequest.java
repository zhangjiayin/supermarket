package com.linkwee.api.request.sm;

import javax.validation.constraints.NotNull;

public class AddRequestRecordRequest {
	
    /**
     *用户编码
     */
	private String userId;
	
	/**
     *请求路径
     */
	@NotNull(message="请求路径不能为空")
	private String requestUrl;
	
    /**
     *请求路径说明
     */
	@NotNull(message="请求路径说明不能为空")
	private String requestUrlRemark;
	
    /**
     *设备id
     */
	private String deviceId;
	
    /**
     *设备详情
     */
	private String deviceDetail;
	
    /**
     *设备分辨率
     */
	private String deviceResolution;
	
    /**
     *手机系统版本号
     */
	private String systemVersion;
	
    /**
     *app版本号
     */
	private String appversion;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRequestUrl() {
		return requestUrl;
	}

	public void setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl;
	}

	public String getRequestUrlRemark() {
		return requestUrlRemark;
	}

	public void setRequestUrlRemark(String requestUrlRemark) {
		this.requestUrlRemark = requestUrlRemark;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getDeviceDetail() {
		return deviceDetail;
	}

	public void setDeviceDetail(String deviceDetail) {
		this.deviceDetail = deviceDetail;
	}

	public String getDeviceResolution() {
		return deviceResolution;
	}

	public void setDeviceResolution(String deviceResolution) {
		this.deviceResolution = deviceResolution;
	}

	public String getSystemVersion() {
		return systemVersion;
	}

	public void setSystemVersion(String systemVersion) {
		this.systemVersion = systemVersion;
	}

	public String getAppversion() {
		return appversion;
	}

	public void setAppversion(String appversion) {
		this.appversion = appversion;
	}
}
