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
 * @创建人： Mignet
 * 
 * @创建时间：2018年04月08日 10:20:14
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class CrmUserActivityReceivingAddress implements Serializable {
	
	private static final long serialVersionUID = 5434471787655331514L;
	
    /**
     *自增长主键
     */
	private Integer id;
	
    /**
     *用户id
     */
	private String userId;
	
    /**
     *收货人姓名
     */
	private String receivingUserName;
	
    /**
     *手机号码
     */
	private String mobile;
	
    /**
     *省份名称
     */
	private String provinceName;
	
    /**
     *城市名字
     */
	private String cityName;
	
    /**
     *收货人详细地址
     */
	private String receivingAddress;
	
    /**
     *第三方账户
     */
	private String thirdAccount;
	
    /**
     *地址类型(1:邮寄地址|2:爱奇艺账户)
     */
	private Integer type;
	
    /**
     *地址类型名称
     */
	private String typeName;
	
    /**
     *创建时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date createTime;
	
    /**
     *更新日期
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date updateTime;
	
    /**
     *扩张字段1
     */
	private String extends1;
	
    /**
     *扩张字段2
     */
	private String extends2;
	
    /**
     *扩张字段3
     */
	private String extends3;
	


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
	
	public void setReceivingUserName(String receivingUserName){
		this.receivingUserName = receivingUserName;
	}
	
	public String getReceivingUserName(){
		return receivingUserName;
	}
	
	public void setMobile(String mobile){
		this.mobile = mobile;
	}
	
	public String getMobile(){
		return mobile;
	}
	
	public void setProvinceName(String provinceName){
		this.provinceName = provinceName;
	}
	
	public String getProvinceName(){
		return provinceName;
	}
	
	public void setCityName(String cityName){
		this.cityName = cityName;
	}
	
	public String getCityName(){
		return cityName;
	}
	
	public void setReceivingAddress(String receivingAddress){
		this.receivingAddress = receivingAddress;
	}
	
	public String getReceivingAddress(){
		return receivingAddress;
	}
	
	public void setThirdAccount(String thirdAccount){
		this.thirdAccount = thirdAccount;
	}
	
	public String getThirdAccount(){
		return thirdAccount;
	}
	
	public void setType(Integer type){
		this.type = type;
	}
	
	public Integer getType(){
		return type;
	}
	
	public void setTypeName(String typeName){
		this.typeName = typeName;
	}
	
	public String getTypeName(){
		return typeName;
	}
	
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	
	public Date getCreateTime(){
		return createTime;
	}
	
	public void setUpdateTime(Date updateTime){
		this.updateTime = updateTime;
	}
	
	public Date getUpdateTime(){
		return updateTime;
	}
	
	public void setExtends1(String extends1){
		this.extends1 = extends1;
	}
	
	public String getExtends1(){
		return extends1;
	}
	
	public void setExtends2(String extends2){
		this.extends2 = extends2;
	}
	
	public String getExtends2(){
		return extends2;
	}
	
	public void setExtends3(String extends3){
		this.extends3 = extends3;
	}
	
	public String getExtends3(){
		return extends3;
	}
	

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}

