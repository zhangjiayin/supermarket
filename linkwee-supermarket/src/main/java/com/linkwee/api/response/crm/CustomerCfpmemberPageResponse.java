package com.linkwee.api.response.crm;

import java.io.Serializable;

import com.linkwee.core.util.WebUtil;
import com.linkwee.web.enums.CfpJobGradeEnum;
 /**
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2017年10月17日 15:33:01
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class CustomerCfpmemberPageResponse implements Serializable {
	
	private static final long serialVersionUID = -2979696234831682651L;
	
	public CustomerCfpmemberPageResponse(){
		
	}
	
	public CustomerCfpmemberPageResponse(CustomerCfpmember obj){
		WebUtil.initObj(this,obj);
		this.setGrade(obj.getGrade());
		this.setHeadImage(obj.getHeadImage()==null?"7187525842a640ca36e48a5ce366894d":obj.getHeadImage());
		this.setRecentTranDate(obj.getRecentTranDate());
		this.setGrade(obj.getGrade()!=null?CfpJobGradeEnum.getCfpJobGradeEnumByKey(obj.getGrade()).getMsg():"");
		this.setUserId(obj.getUserId());
		this.setMobile(obj.getMobile());
		this.setRegistTime(obj.getRegistTime());
		this.setUserName(obj.getUserName()==null?obj.getMobile():obj.getUserName());
	}
	
	 /**
     *	职级
     */
	private String grade;
	
    /**
     *	头像
     */
	private String headImage;
	
    /**
     *最近一笔投资
     */
	private String recentTranDate;
	
    /**
     *姓名
     */
	private String userName;

    /**
      *用户id
      */
	private String userId;
	
   /**
    *手机号码
    */
	private String mobile;
	
	/**
    *注册时间
    */
	private String registTime;
	
	
	public String getRegistTime() {
		return registTime;
	}

	public void setRegistTime(String registTime) {
		this.registTime = registTime;
	}

	public String getMobile() {
		return mobile;
	}
	
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getHeadImage() {
		return headImage;
	}

	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}

	public String getRecentTranDate() {
		return recentTranDate;
	}

	public void setRecentTranDate(String recentTranDate) {
		this.recentTranDate = recentTranDate;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}

