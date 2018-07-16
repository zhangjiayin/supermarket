package com.linkwee.web.model;

import java.io.Serializable;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2017年08月16日 10:00:12
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class CimFundBaseDefined implements Serializable {
	
	private static final long serialVersionUID = -9037059501244036167L;
	
    /**
     *主键，自增长
     */
	private Integer id;
	
    /**
     *基金机构编码-不重复字段
     */
	private String orgNumber;
	
    /**
     *基金类型代码
     */
	private String fundType;
	
    /**
     *基金类型名称
     */
	private String fundTypeName;
	
    /**
     *基金类型键
     */
	private String fundTypeKey;
	
    /**
     *基金类型值
     */
	private String fundTypeValue;
	
    /**
     *删除标识 0-有效  1-无效
     */
	private Integer delStatus;
	


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
	
	public void setFundType(String fundType){
		this.fundType = fundType;
	}
	
	public String getFundType(){
		return fundType;
	}
	
	public void setFundTypeName(String fundTypeName){
		this.fundTypeName = fundTypeName;
	}
	
	public String getFundTypeName(){
		return fundTypeName;
	}
	
	public void setFundTypeKey(String fundTypeKey){
		this.fundTypeKey = fundTypeKey;
	}
	
	public String getFundTypeKey(){
		return fundTypeKey;
	}
	
	public void setFundTypeValue(String fundTypeValue){
		this.fundTypeValue = fundTypeValue;
	}
	
	public String getFundTypeValue(){
		return fundTypeValue;
	}
	
	public void setDelStatus(Integer delStatus){
		this.delStatus = delStatus;
	}
	
	public Integer getDelStatus(){
		return delStatus;
	}
	

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}

