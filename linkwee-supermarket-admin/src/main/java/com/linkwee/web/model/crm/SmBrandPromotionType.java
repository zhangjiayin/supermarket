package com.linkwee.web.model.crm;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 import java.lang.Integer;
 import java.lang.String;
 import java.util.Date;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2017年06月28日 16:44:11
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class SmBrandPromotionType implements Serializable {
	
	private static final long serialVersionUID = 8325533875401953113L;
	
    /**
     *自增长主键
     */
	private Integer id;
	
    /**
     *海报类型名称:1=热点海报|2=精品推荐
     */
	private String typeName;
	
    /**
     *海报类型:1=热点海报|2=精品推荐
     */
	private Integer typeValue;
	
    /**
     *1=正常|2=已删除
     */
	private Integer status;
	
    /**
     *创建时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date createTime;
	
    /**
     *海报内容
     */
	private String content;
	
    /**
     *邀请/推荐类别:1-推荐理财师、2-邀请客户
     */
	private Integer appType;
	


	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return id;
	}
	
	public void setTypeName(String typeName){
		this.typeName = typeName;
	}
	
	public String getTypeName(){
		return typeName;
	}
	
	public void setTypeValue(Integer typeValue){
		this.typeValue = typeValue;
	}
	
	public Integer getTypeValue(){
		return typeValue;
	}
	
	public void setStatus(Integer status){
		this.status = status;
	}
	
	public Integer getStatus(){
		return status;
	}
	
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	
	public Date getCreateTime(){
		return createTime;
	}
	
	public void setContent(String content){
		this.content = content;
	}
	
	public String getContent(){
		return content;
	}
	
	public void setAppType(Integer appType){
		this.appType = appType;
	}
	
	public Integer getAppType(){
		return appType;
	}
	
}

