package com.linkwee.web.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
 import java.lang.Integer;
 import java.lang.String;
 import java.math.BigDecimal;
 import java.util.Date;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2018年04月03日 14:20:40
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class ActSignTransferRecord implements Serializable {
	
	private static final long serialVersionUID = -3113722541211102989L;
	
    /**
     *自增id
     */
	private Integer id;
	
    /**
     *转账id
     */
	private String transferId;
	
    /**
     *1理财师，2投资者
     */
	private Integer userType;
	
    /**
     *客户id
     */
	private String userId;
	
    /**
     *转账金额
     */
	private BigDecimal transferAmount;
	
    /**
     *转出类型 1：猎财余额 2：抽奖
     */
	private Integer transferType;
	
    /**
     *转账时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date transferTime;
	
    /**
     *扩展字段1
     */
	private String extend1;
	
    /**
     *扩展字段2
     */
	private String extend2;
	


	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return id;
	}
	
	public void setTransferId(String transferId){
		this.transferId = transferId;
	}
	
	public String getTransferId(){
		return transferId;
	}
	
	public void setUserType(Integer userType){
		this.userType = userType;
	}
	
	public Integer getUserType(){
		return userType;
	}
	
	public void setUserId(String userId){
		this.userId = userId;
	}
	
	public String getUserId(){
		return userId;
	}
	
	public void setTransferAmount(BigDecimal transferAmount){
		this.transferAmount = transferAmount;
	}
	
	public BigDecimal getTransferAmount(){
		return transferAmount;
	}
	
	public void setTransferType(Integer transferType){
		this.transferType = transferType;
	}
	
	public Integer getTransferType(){
		return transferType;
	}
	
	public void setTransferTime(Date transferTime){
		this.transferTime = transferTime;
	}
	
	public Date getTransferTime(){
		return transferTime;
	}
	
	public void setExtend1(String extend1){
		this.extend1 = extend1;
	}
	
	public String getExtend1(){
		return extend1;
	}
	
	public void setExtend2(String extend2){
		this.extend2 = extend2;
	}
	
	public String getExtend2(){
		return extend2;
	}
	

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}

