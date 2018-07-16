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
 * @创建时间：2017年09月12日 14:37:38
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class CimInsuranceInfo implements Serializable {
	
	private static final long serialVersionUID = 7277232626126056007L;
	
    /**
     *主键，自增长
     */
	private Integer id;
	
    /**
     *保险机构编码-不重复字段
     */
	private String orgNumber;
	
    /**
     *保险机构名称
     */
	private String name;
	
    /**
     *保险机构logo
     */
	private String platformIco;
	
    /**
     *产品详情页面跳转url
     */
	private String productDetailUrl;
	
    /**
     *机构首页跳转url
     */
	private String platformIndexUrl;
	
    /**
     *保单列表跳转url
     */
	private String insureListUrl;
	
    /**
     *保单详情跳转url
     */
	private String insureDetailUrl;
	
    /**
     *合作公司ID
     */
	private String partnerId;
	
    /**
     *合作公司私钥
     */
	private String partnerPrivateKey;
	
    /**
     *接口请求基础地址
     */
	private String partnerRequestBaseUrl;
	
    /**
     *创建时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date creatTime;
	
    /**
     *上线时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date upTime;
	


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
	
	public void setPlatformIco(String platformIco){
		this.platformIco = platformIco;
	}
	
	public String getPlatformIco(){
		return platformIco;
	}
	
	public void setProductDetailUrl(String productDetailUrl){
		this.productDetailUrl = productDetailUrl;
	}
	
	public String getProductDetailUrl(){
		return productDetailUrl;
	}
	
	public void setPlatformIndexUrl(String platformIndexUrl){
		this.platformIndexUrl = platformIndexUrl;
	}
	
	public String getPlatformIndexUrl(){
		return platformIndexUrl;
	}
	
	public void setInsureListUrl(String insureListUrl){
		this.insureListUrl = insureListUrl;
	}
	
	public String getInsureListUrl(){
		return insureListUrl;
	}
	
	public void setInsureDetailUrl(String insureDetailUrl){
		this.insureDetailUrl = insureDetailUrl;
	}
	
	public String getInsureDetailUrl(){
		return insureDetailUrl;
	}
	
	public void setPartnerId(String partnerId){
		this.partnerId = partnerId;
	}
	
	public String getPartnerId(){
		return partnerId;
	}
	
	public void setPartnerPrivateKey(String partnerPrivateKey){
		this.partnerPrivateKey = partnerPrivateKey;
	}
	
	public String getPartnerPrivateKey(){
		return partnerPrivateKey;
	}
	
	public void setPartnerRequestBaseUrl(String partnerRequestBaseUrl){
		this.partnerRequestBaseUrl = partnerRequestBaseUrl;
	}
	
	public String getPartnerRequestBaseUrl(){
		return partnerRequestBaseUrl;
	}
	
	public void setCreatTime(Date creatTime){
		this.creatTime = creatTime;
	}
	
	public Date getCreatTime(){
		return creatTime;
	}
	
	public void setUpTime(Date upTime){
		this.upTime = upTime;
	}
	
	public Date getUpTime(){
		return upTime;
	}
	

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}

