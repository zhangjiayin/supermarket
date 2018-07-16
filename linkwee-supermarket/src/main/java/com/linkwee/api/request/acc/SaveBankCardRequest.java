package com.linkwee.api.request.acc;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class SaveBankCardRequest {
	
	  private String mobile;
	  
	  private String userName;
	  
	  private String idCard;
	  
	  private String bankCard;
	  
	  private String kaihuhang;
	  
	  private String region;//地区

	  private String reserveMobile;
	
	  private String tranPwd;
	  
	  private String consignee;//收货人
	  
	  private String conMobile;
	  
	  private String address;
	  
	public String getMobile() {
		return mobile;
	}



	public void setMobile(String mobile) {
		this.mobile = mobile;
	}



	public String getUserName() {
		return userName;
	}



	public void setUserName(String userName) {
		this.userName = userName;
	}



	public String getIdCard() {
		return idCard;
	}



	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}



	public String getBankCard() {
		return bankCard;
	}



	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
	}



	public String getKaihuhang() {
		return kaihuhang;
	}



	public void setKaihuhang(String kaihuhang) {
		this.kaihuhang = kaihuhang;
	}



	public String getRegion() {
		return region;
	}



	public void setRegion(String region) {
		this.region = region;
	}



	public String getReserveMobile() {
		return reserveMobile;
	}



	public void setReserveMobile(String reserveMobile) {
		this.reserveMobile = reserveMobile;
	}



	public String getTranPwd() {
		return tranPwd;
	}



	public void setTranPwd(String tranPwd) {
		this.tranPwd = tranPwd;
	}



	public String getConsignee() {
		return consignee;
	}



	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}



	public String getConMobile() {
		return conMobile;
	}



	public void setConMobile(String conMobile) {
		this.conMobile = conMobile;
	}



	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}



	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}

	
}
