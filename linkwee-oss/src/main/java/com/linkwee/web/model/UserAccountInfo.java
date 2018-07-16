package com.linkwee.web.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.linkwee.core.base.BaseEntity;
 /**
 * 
 * 描述： 实体Bean
 * 
 * @创建人： chenchy
 * 
 * @创建时间：2016年08月15日 11:01:51
 * 
 * @Copyright (c) 深圳市小牛科技有限公司-版权所有
 */
public class UserAccountInfo extends BaseEntity {
	
	private static final long serialVersionUID = 7323589850398590673L;
	
    /**
     *
     */
	private Integer id;
	
    /**
     *用户id
     */
	private String userId;
	
    /**
     *手机号码
     */
	private String mobile;
	
    /**
     *用户姓名
     */
	private String userName;
	
    /**
     *身份证号
     */
	private String idCard;
	
    /**
     *银行编码
     */
	private String bankCode;
	
    /**
     *银行名称
     */
	private String bankName;
	
    /**
     *银行卡号
     */
	private String bankCard;
	/**
	 * 备注
	 * @param id
	 */
	private String remark;


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
	
	public void setMobile(String mobile){
		this.mobile = mobile;
	}
	
	public String getMobile(){
		return mobile;
	}
	
	public void setUserName(String userName){
		this.userName = userName;
	}
	
	public String getUserName(){
		return userName;
	}
	
	public void setIdCard(String idCard){
		this.idCard = idCard;
	}
	
	public String getIdCard(){
		return idCard;
	}
	
	public void setBankCode(String bankCode){
		this.bankCode = bankCode;
	}
	
	public String getBankCode(){
		return bankCode;
	}
	
	public void setBankName(String bankName){
		this.bankName = bankName;
	}
	
	public String getBankName(){
		return bankName;
	}
	
	public void setBankCard(String bankCard){
		this.bankCard = bankCard;
	}
	
	public String getBankCard(){
		return bankCard;
	}
	
	

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}

