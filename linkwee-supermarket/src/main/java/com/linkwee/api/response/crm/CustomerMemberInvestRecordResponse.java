package com.linkwee.api.response.crm;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linkwee.core.jackson.MoneySerializer;
import com.linkwee.core.util.NumberUtils;
import com.linkwee.core.util.WebUtil;
 /**
 * 
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2017年10月17日 15:33:01
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class CustomerMemberInvestRecordResponse implements Serializable {
	
	private static final long serialVersionUID = 4705570091076552752L;
	
	public CustomerMemberInvestRecordResponse(){
	}
	
	public CustomerMemberInvestRecordResponse(CustomerMemberInvestRecordResponse obj){
		WebUtil.initObj(this,obj);
		this.setUserName(obj.getUserName());
		this.setHeadImage(obj.getHeadImage());
		this.setOrgName(obj.getOrgName());
//		this.setFeeAmount(NumberUtils.getFormat(Double.parseDouble(obj.getFeeAmount()), "0.00"));
		this.setFeeAmount(obj.getFeeAmount());
		this.setInvestAmt(NumberUtils.getFormat(Double.parseDouble(obj.getInvestAmt()), "0.00"));
		this.setInvestTime(obj.getInvestTime()!=null?
				obj.getInvestTime().substring(0, 4)+"年"+obj.getInvestTime().substring(5, 7)+"月"+obj.getInvestTime().substring(8, 10)+"日":obj.getInvestTime());
		this.setInvestId(obj.getInvestId());
	}

	/**
     *	头像
     */
	private String headImage;
	
    /**
     *姓名
     */
	private String userName;
	
	/**
     *投资平台
     */
	private String orgName;
	
	/**
     *我的佣金(元)
     */
	@JsonSerialize(using = MoneySerializer.class)
	private BigDecimal  feeAmount;
	
	/**
     *投资金额(元)
     */
	private String investAmt;
	
	/**
     *投资时间
     */
	private String investTime;
	
	/**
     *用户类型（用户理财师投资页判断职级）0- 客户 1-直推 2-二级 3-三级
     */
	private String userType;
	
	/**
     *投资id 
     */
	private String investId;
	
	public String getInvestId() {
		return investId;
	}

	public void setInvestId(String investId) {
		this.investId = investId;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public BigDecimal getFeeAmount() {
		return feeAmount;
	}

	public void setFeeAmount(BigDecimal feeAmount) {
		this.feeAmount = feeAmount;
	}

	public String getInvestAmt() {
		return investAmt;
	}

	public void setInvestAmt(String investAmt) {
		this.investAmt = investAmt;
	}

	public String getInvestTime() {
		return investTime;
	}

	public void setInvestTime(String investTime) {
		this.investTime = investTime;
	}

	public String getHeadImage() {
		return headImage;
	}

	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
		
}

