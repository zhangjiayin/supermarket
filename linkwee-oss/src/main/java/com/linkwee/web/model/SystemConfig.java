package com.linkwee.web.model;

import java.util.Date;

import com.alibaba.dubbo.common.json.JSON;
import com.linkwee.core.base.BaseEntity;
 /**
 * 
 * 描述： 实体Bean
 * 
 * @创建人： Bob
 * 
 * @创建时间：2015年12月17日 16:59:16
 * 
 * @Copyright (c) 深圳市小牛科技有限公司-版权所有
 */
public class SystemConfig extends BaseEntity {
	
	private static final long serialVersionUID = 7890811780131254076L;
	
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
     *
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
     *应用类别
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

