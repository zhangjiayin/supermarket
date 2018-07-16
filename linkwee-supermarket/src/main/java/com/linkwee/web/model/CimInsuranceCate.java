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
 * @创建时间：2018年01月18日 15:49:59
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class CimInsuranceCate implements Serializable {
	
	private static final long serialVersionUID = -6362204296932837071L;
	
    /**
     *分类id
     */
	private Integer cateId;
	
    /**
     *分类名称
     */
	private String cateName;
	
    /**
     *排序
     */
	private Integer orderNum;
	
    /**
     *分类logo 投资者端 
     */
	private String cateLogoInvestor;
	
    /**
     *分类logo 猎才大师
     */
	private String cateLogoChannel;
	
    /**
     *是否可用：0-可用 1-不可用
     */
	private Integer disabled;
	
    /**
     *最近修改时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date modifyTime;
	
    /**
     *分类描述
     */
	private String description;
	
    /**
     *分类图片跳转链接
     */
	private String urlLink;
	
    /**
     *分类说明
     */
	private String cateDeclare;
	


	public void setCateId(Integer cateId){
		this.cateId = cateId;
	}
	
	public Integer getCateId(){
		return cateId;
	}
	
	public void setCateName(String cateName){
		this.cateName = cateName;
	}
	
	public String getCateName(){
		return cateName;
	}
	
	public void setOrderNum(Integer orderNum){
		this.orderNum = orderNum;
	}
	
	public Integer getOrderNum(){
		return orderNum;
	}
	
	public void setCateLogoInvestor(String cateLogoInvestor){
		this.cateLogoInvestor = cateLogoInvestor;
	}
	
	public String getCateLogoInvestor(){
		return cateLogoInvestor;
	}
	
	public void setCateLogoChannel(String cateLogoChannel){
		this.cateLogoChannel = cateLogoChannel;
	}
	
	public String getCateLogoChannel(){
		return cateLogoChannel;
	}
	
	public void setDisabled(Integer disabled){
		this.disabled = disabled;
	}
	
	public Integer getDisabled(){
		return disabled;
	}
	
	public void setModifyTime(Date modifyTime){
		this.modifyTime = modifyTime;
	}
	
	public Date getModifyTime(){
		return modifyTime;
	}
	
	public void setDescription(String description){
		this.description = description;
	}
	
	public String getDescription(){
		return description;
	}
	
	public void setUrlLink(String urlLink){
		this.urlLink = urlLink;
	}
	
	public String getUrlLink(){
		return urlLink;
	}
	
	public void setCateDeclare(String cateDeclare){
		this.cateDeclare = cateDeclare;
	}
	
	public String getCateDeclare(){
		return cateDeclare;
	}
	

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}

