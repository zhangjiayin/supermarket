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
 * @创建时间：2017年02月20日 10:42:39
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class SysConfig implements Serializable {
	
	private static final long serialVersionUID = -1108406973107943969L;
	
    /**
     *流水号
     */
	private Integer id;
	
    /**
     *名称
     */
	private String configName;
	
    /**
     *类别
     */
	private String configType;
	
    /**
     *键
     */
	private String configKey;
	
    /**
     *值
     */
	private String configValue;
	
    /**
     *备注
     */
	private String remark;
	
    /**
     *创建时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date crtTime;
	
    /**
     *应用类别:0全局，1理财师，2投资者
     */
	private Integer appType;
	


	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return id;
	}
	
	public void setConfigName(String configName){
		this.configName = configName;
	}
	
	public String getConfigName(){
		return configName;
	}
	
	public void setConfigType(String configType){
		this.configType = configType;
	}
	
	public String getConfigType(){
		return configType;
	}
	
	public void setConfigKey(String configKey){
		this.configKey = configKey;
	}
	
	public String getConfigKey(){
		return configKey;
	}
	
	public void setConfigValue(String configValue){
		this.configValue = configValue;
	}
	
	public String getConfigValue(){
		return configValue;
	}
	
	public void setRemark(String remark){
		this.remark = remark;
	}
	
	public String getRemark(){
		return remark;
	}
	
	public void setCrtTime(Date crtTime){
		this.crtTime = crtTime;
	}
	
	public Date getCrtTime(){
		return crtTime;
	}
	
	public void setAppType(Integer appType){
		this.appType = appType;
	}
	
	public Integer getAppType(){
		return appType;
	}
	

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}

