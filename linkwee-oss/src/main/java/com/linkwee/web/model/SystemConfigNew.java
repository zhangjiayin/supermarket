package com.linkwee.web.model;

import com.linkwee.core.base.BaseEntity;
 import java.lang.Integer;
 import java.lang.String;
 import java.util.Date;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年06月03日 10:05:19
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class SystemConfigNew extends BaseEntity {
	
	private static final long serialVersionUID = 1330169561569045241L;
	
    /**
     *流水号
     */
	private Integer id;
	
    /**
     *名称
     */
	private String name;
	
    /**
     *类别
     */
	private String type;
	
    /**
     *键
     */
	private String key;
	
    /**
     *值
     */
	private String value;
	
    /**
     *备注
     */
	private String remark;
	
    /**
     *创建时间
     */
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
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public void setType(String type){
		this.type = type;
	}
	
	public String getType(){
		return type;
	}
	
	public void setKey(String key){
		this.key = key;
	}
	
	public String getKey(){
		return key;
	}
	
	public void setValue(String value){
		this.value = value;
	}
	
	public String getValue(){
		return value;
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
	
}

