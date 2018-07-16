package com.linkwee.web.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
 import java.lang.Integer;
 import java.lang.String;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2017年07月27日 18:31:28
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class CimOrgRechargeLimit implements Serializable {
	
	private static final long serialVersionUID = -232723400283031016L;
	
    /**
     *流水号
     */
	private Integer id;
	
    /**
     *机构编码
     */
	private String orgNumber;
	
    /**
     *支持银行
     */
	private String bank;
	
    /**
     *单笔限额（元）
     */
	private String orderLimit;
	
    /**
     *单卡每日限额（元）
     */
	private String dayLimit;
	
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
	
	public void setOrgNumber(String orgNumber){
		this.orgNumber = orgNumber;
	}
	
	public String getOrgNumber(){
		return orgNumber;
	}
	
	public void setBank(String bank){
		this.bank = bank;
	}
	
	public String getBank(){
		return bank;
	}
	
	public void setOrderLimit(String orderLimit){
		this.orderLimit = orderLimit;
	}
	
	public String getOrderLimit(){
		return orderLimit;
	}
	
	public void setDayLimit(String dayLimit){
		this.dayLimit = dayLimit;
	}
	
	public String getDayLimit(){
		return dayLimit;
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

