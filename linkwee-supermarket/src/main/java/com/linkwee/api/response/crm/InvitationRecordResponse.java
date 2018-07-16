package com.linkwee.api.response.crm;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import com.linkwee.core.base.BaseEntity;
import com.linkwee.core.util.WebUtil;
import com.linkwee.web.model.crm.CrmLineUserInfo;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2017年05月19日 19:36:07
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class InvitationRecordResponse extends BaseEntity {
	
	private static final long serialVersionUID = 7597903819948281544L;
	
	public InvitationRecordResponse(){
		
	}
	
	public InvitationRecordResponse(CrmLineUserInfo obj) throws UnsupportedEncodingException {
		WebUtil.initObj(this,obj);
		this.setHeadImgUrl(obj.getHeadImgUrl());
		this.setNickName(URLDecoder.decode(obj.getNickName(), "UTF-8"));
		this.setCfpName(obj.getCfpName());
		this.setMobile(obj.getMobile());
	}
	
	
    /**
     *用户微信openId
     */
	private String openId;
	
    /**
     *用户领取福利手机号码
     */
	private String mobile;
	
    /**
     *用户微信昵称
     */
	private String nickName;
	
    /**
     *用户微信头像url
     */
	private String headImgUrl;
	
    /**
     *邀请理财师手机号码
     */
	private String cfpMobile;
	
    /**
     *邀请理财师userId
     */
	private String cfpUserId;
	
    /**
     *邀请理财师姓名
     */
	private String cfpName;
	
    /**
     *1长沙活动
     */
	private Integer activityType;
	
    /**
     *备注
     */
	private String remark;
	
	/**
	 * 线下活动理财师邀请人数
	 */
	private Integer invitNum;
	
	public Integer getInvitNum() {
		return invitNum;
	}

	public void setInvitNum(Integer invitNum) {
		this.invitNum = invitNum;
	}

	public void setOpenId(String openId){
		this.openId = openId;
	}
	
	public String getOpenId(){
		return openId;
	}
	
	public void setMobile(String mobile){
		this.mobile = mobile;
	}
	
	public String getMobile(){
		return mobile;
	}
	
	public void setNickName(String nickName){
		this.nickName = nickName;
	}
	
	public String getNickName(){
		return nickName;
	}
	
	public void setHeadImgUrl(String headImgUrl){
		this.headImgUrl = headImgUrl;
	}
	
	public String getHeadImgUrl(){
		return headImgUrl;
	}
	
	public void setCfpMobile(String cfpMobile){
		this.cfpMobile = cfpMobile;
	}
	
	public String getCfpMobile(){
		return cfpMobile;
	}
	
	public void setCfpUserId(String cfpUserId){
		this.cfpUserId = cfpUserId;
	}
	
	public String getCfpUserId(){
		return cfpUserId;
	}
	
	public void setCfpName(String cfpName){
		this.cfpName = cfpName;
	}
	
	public String getCfpName(){
		return cfpName;
	}
	
	public void setActivityType(Integer activityType){
		this.activityType = activityType;
	}
	
	public Integer getActivityType(){
		return activityType;
	}
	
	public void setRemark(String remark){
		this.remark = remark;
	}
	
	public String getRemark(){
		return remark;
	}
	
}

