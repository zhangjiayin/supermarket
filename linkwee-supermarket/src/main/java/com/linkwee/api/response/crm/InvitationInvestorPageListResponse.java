package com.linkwee.api.response.crm;

import java.io.Serializable;

import com.linkwee.core.util.DateUtils;
import com.linkwee.core.util.WebUtil;
import com.linkwee.web.model.CrmCfplanner;
 /**
 * 
 * @描述： V4.0邀请记录--推荐理财师
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2017年06月23日 
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class InvitationInvestorPageListResponse implements Serializable {
	
	private static final long serialVersionUID = -2979696234831682651L;
	
	public InvitationInvestorPageListResponse(){
		
	}
	
	public InvitationInvestorPageListResponse(CrmCfplanner obj){
		WebUtil.initObj(this,obj);
		this.setUserName(obj.getUserName());
		this.setIsInvest(obj.getIsInvest()==0?"0":"1");
		this.setRegisterTime(DateUtils.format(obj.getCfpRegTime(),DateUtils.FORMAT_SHORT_CN)+"日注册");
		this.setMobile(obj.getMobile());
	}
	
	/**
     *客户姓名
     */
	private String userName;
	
	/**
     *注册时间
     */
	private String registerTime;
	
	/**
     *手机
     */
	private String mobile;
	
	/**
     *是否投资
     */
	private String isInvest;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getIsInvest() {
		return isInvest;
	}

	public void setIsInvest(String isInvest) {
		this.isInvest = isInvest;
	}
	
	
}

