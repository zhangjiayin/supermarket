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
 * @创建时间：2017年08月25日 11:21:40
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class CimFundInfo implements Serializable {
	
	private static final long serialVersionUID = -5638607209129226800L;
	
    /**
     *主键，自增长
     */
	private Integer id;
	
    /**
     *基金机构编码-不重复字段
     */
	private String orgNumber;
	
    /**
     *基金名称
     */
	private String name;
	
    /**
     *上线时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date upTime;
	
    /**
     *基金平台logo
     */
	private String platformIco;
	
    /**
     *第三方基金平台秘钥
     */
	private String platformSelfSecret;
	
    /**
     *基金首页url
     */
	private String platformIndexUrl;
	
    /**
     *基金详情页url
     */
	private String fundDetailUrl;
	
    /**
     *个人资产页url
     */
	private String personAccountUrl;
	
    /**
     *合作公司代码
     */
	private String apiKey;
	
    /**
     *合作公司私钥
     */
	private String privateKey;
	
    /**
     *请求域名
     */
	private String requestHost;
	
    /**
     *API版本
     */
	private String apiVersion;
	


	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return id;
	}
	
	public void setOrgNumber(String orgNumber){
		this.orgNumber = orgNumber;
	}
	
	public String getOrgNumber(){
		return orgNumber;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public void setUpTime(Date upTime){
		this.upTime = upTime;
	}
	
	public Date getUpTime(){
		return upTime;
	}
	
	public void setPlatformIco(String platformIco){
		this.platformIco = platformIco;
	}
	
	public String getPlatformIco(){
		return platformIco;
	}
	
	public void setPlatformSelfSecret(String platformSelfSecret){
		this.platformSelfSecret = platformSelfSecret;
	}
	
	public String getPlatformSelfSecret(){
		return platformSelfSecret;
	}
	
	public void setPlatformIndexUrl(String platformIndexUrl){
		this.platformIndexUrl = platformIndexUrl;
	}
	
	public String getPlatformIndexUrl(){
		return platformIndexUrl;
	}
	
	public void setFundDetailUrl(String fundDetailUrl){
		this.fundDetailUrl = fundDetailUrl;
	}
	
	public String getFundDetailUrl(){
		return fundDetailUrl;
	}
	
	public void setPersonAccountUrl(String personAccountUrl){
		this.personAccountUrl = personAccountUrl;
	}
	
	public String getPersonAccountUrl(){
		return personAccountUrl;
	}
	
	public void setApiKey(String apiKey){
		this.apiKey = apiKey;
	}
	
	public String getApiKey(){
		return apiKey;
	}
	
	public void setPrivateKey(String privateKey){
		this.privateKey = privateKey;
	}
	
	public String getPrivateKey(){
		return privateKey;
	}
	
	public void setRequestHost(String requestHost){
		this.requestHost = requestHost;
	}
	
	public String getRequestHost(){
		return requestHost;
	}
	
	public void setApiVersion(String apiVersion){
		this.apiVersion = apiVersion;
	}
	
	public String getApiVersion(){
		return apiVersion;
	}
	

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}

