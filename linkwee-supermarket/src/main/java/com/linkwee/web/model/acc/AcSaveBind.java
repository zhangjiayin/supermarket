package com.linkwee.web.model.acc;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.linkwee.core.base.BaseEntity;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2018年06月29日 09:51:57
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class AcSaveBind extends BaseEntity {
	
	private static final long serialVersionUID = -4414814889351568718L;
	
    /**
     *自增ID
     */
	private Long id;
	
    /**
     *手机号码
     */
	private String mobile;
	
    /**
     *预留手机号码
     */
	private String reserveMobile;
	
    /**
     *用户名
     */
	private String userName;
	
    /**
     *身份证号
     */
	private String idCard;
	
    /**
     *银行卡号
     */
	private String bankCard;
	
    /**
     *开户银行
     */
	private String kaihuhang;
	
    /**
     *地区
     */
	private String region;
	
    /**
     *交易密码
     */
	private String tranPwd;
	
    /**
     *收货人
     */
	private String consignee;
	
    /**
     *收货人手机号码
     */
	private String conMobile;
	
    /**
     *收货人地址
     */
	private String address;
	
    /**
     *创建时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date createDate;
	
    /**
     *更新时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date updateDate;
	


	public void setId(Long id){
		this.id = id;
	}
	
	public Long getId(){
		return id;
	}
	
	public void setMobile(String mobile){
		this.mobile = mobile;
	}
	
	public String getMobile(){
		return mobile;
	}
	
	public void setReserveMobile(String reserveMobile){
		this.reserveMobile = reserveMobile;
	}
	
	public String getReserveMobile(){
		return reserveMobile;
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
	
	public void setBankCard(String bankCard){
		this.bankCard = bankCard;
	}
	
	public String getBankCard(){
		return bankCard;
	}
	
	public void setKaihuhang(String kaihuhang){
		this.kaihuhang = kaihuhang;
	}
	
	public String getKaihuhang(){
		return kaihuhang;
	}
	
	public void setRegion(String region){
		this.region = region;
	}
	
	public String getRegion(){
		return region;
	}
	
	public void setTranPwd(String tranPwd){
		this.tranPwd = tranPwd;
	}
	
	public String getTranPwd(){
		return tranPwd;
	}
	
	public void setConsignee(String consignee){
		this.consignee = consignee;
	}
	
	public String getConsignee(){
		return consignee;
	}
	
	public void setConMobile(String conMobile){
		this.conMobile = conMobile;
	}
	
	public String getConMobile(){
		return conMobile;
	}
	
	public void setAddress(String address){
		this.address = address;
	}
	
	public String getAddress(){
		return address;
	}
	
	public void setCreateDate(Date createDate){
		this.createDate = createDate;
	}
	
	public Date getCreateDate(){
		return createDate;
	}
	
	public void setUpdateDate(Date updateDate){
		this.updateDate = updateDate;
	}
	
	public Date getUpdateDate(){
		return updateDate;
	}
	
}

