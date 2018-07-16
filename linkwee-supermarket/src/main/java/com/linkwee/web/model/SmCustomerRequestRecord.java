package com.linkwee.web.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import java.lang.Integer;
import java.lang.String;
import java.util.Date;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2018年05月23日 10:46:22
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class SmCustomerRequestRecord implements Serializable {
	
	private static final long serialVersionUID = 1692952157746333146L;
	
    /**
     *
     */
	private Integer id;
	
    /**
     *用户编码
     */
	private String userId;
	
    /**
     *请求路径
     */
	private String requestUrl;
	
    /**
     *请求路径说明
     */
	private String requestUrlRemark;
	
    /**
     *设备id
     */
	private String deviceId;
	
    /**
     *平台的类型   android,ios,wechat,web,wap
     */
	private String deviceType;
	
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
	
    /**
     *app类型(investor=T呗|channel=领会理财师|all=所有)
     */
	private String appType;
	
    /**
     *创建时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date createDate;
	


	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return id;
	}
	
	public void setUserId(String userId){
		this.userId = userId;
	}
	
	public String getUserId(){
		return userId;
	}
	
	public void setRequestUrl(String requestUrl){
		this.requestUrl = requestUrl;
	}
	
	public String getRequestUrl(){
		return requestUrl;
	}
	
	public void setRequestUrlRemark(String requestUrlRemark){
		this.requestUrlRemark = requestUrlRemark;
	}
	
	public String getRequestUrlRemark(){
		return requestUrlRemark;
	}
	
	public void setDeviceId(String deviceId){
		this.deviceId = deviceId;
	}
	
	public String getDeviceId(){
		return deviceId;
	}
	
	public void setDeviceType(String deviceType){
		this.deviceType = deviceType;
	}
	
	public String getDeviceType(){
		return deviceType;
	}
	
	public void setDeviceDetail(String deviceDetail){
		this.deviceDetail = deviceDetail;
	}
	
	public String getDeviceDetail(){
		return deviceDetail;
	}
	
	public void setDeviceResolution(String deviceResolution){
		this.deviceResolution = deviceResolution;
	}
	
	public String getDeviceResolution(){
		return deviceResolution;
	}
	
	public void setSystemVersion(String systemVersion){
		this.systemVersion = systemVersion;
	}
	
	public String getSystemVersion(){
		return systemVersion;
	}
	
	public void setAppversion(String appversion){
		this.appversion = appversion;
	}
	
	public String getAppversion(){
		return appversion;
	}
	
	public void setAppType(String appType){
		this.appType = appType;
	}
	
	public String getAppType(){
		return appType;
	}
	
	public void setCreateDate(Date createDate){
		this.createDate = createDate;
	}
	
	public Date getCreateDate(){
		return createDate;
	}
	

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}

