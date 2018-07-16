package com.linkwee.api.response.crm;

import java.io.Serializable;

import com.linkwee.core.util.WebUtil;
import com.linkwee.web.model.crm.GoodTransResp;
 /**
 * 
 * @描述：V4.1.1往期喜报
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2017年07月26日 
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class GoodTransPageListResponse implements Serializable {
	
	private static final long serialVersionUID = -2979696234831682651L;
	
	public GoodTransPageListResponse(){
		
	}
	
	public GoodTransPageListResponse(GoodTransResp obj){
		WebUtil.initObj(this,obj);
		this.setUserName(obj.getUserName());
		this.setMobile(obj.getMobile()!=null?obj.getMobile().substring(0, 3)+"****"+obj.getMobile().substring(obj.getMobile().length()-4, obj.getMobile().length()):null);
	    this.setAmount(obj.getAmount());
	    this.setInvestTime(obj.getInvestTime()!=null?obj.getInvestTime().substring(0, 4)+"年"+obj.getInvestTime().substring(5, 7)+"月"+obj.getInvestTime().substring(8,10)+"日":null);
	    this.setBillId(obj.getBillId());
	}
	
	/**
	 * 	出单金额
	 */
	private String amount;

	/**
	 * 	投资订单ID
	 */
	private String billId;
	
	/**
	 * 	出单时间
	 */
	private String investTime;
	
	/**
	 * 	出单人
	 */
	private String userName;
	
	/**
	 * 	手机号码
	 */
	private String mobile;

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getBillId() {
		return billId;
	}

	public void setBillId(String billId) {
		this.billId = billId;
	}

	public String getInvestTime() {
		return investTime;
	}

	public void setInvestTime(String investTime) {
		this.investTime = investTime;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
}

