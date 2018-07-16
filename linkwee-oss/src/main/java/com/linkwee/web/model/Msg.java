package com.linkwee.web.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： 陈佳良
 * 
 * @创建时间：2016年06月03日 17:34:00
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class Msg implements Serializable {
	
	private static final long serialVersionUID = -917626435223258619L;
	
    /**
     *流水号
     */
	private Integer msgId;
	
    /**
     *消息内容
     */
	private String content;
	
    /**
     *链接
     */
	private String link;
	
    /**
     *状态(0发布,1删除)
     */
	private Integer status;
	
    /**
     *消息类别(0系统消息;1理财师消息)
     */
	private Integer type;
	
    /**
     *用户编码
     */
	private String userNumber;

	 private String message;
	
    /**
     *生效时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" , timezone="GMT+8")
	private Date startTime;
	
    /**
     *创建时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")  
	private Date crtTime;
	
    /**
     *修改时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8") 
	private Date modifyTime;
	
    /**
     *应用类别0公共，1理财师，2投资者
     */
	private Integer appType;
	


	public void setMsgId(Integer msgId){
		this.msgId = msgId;
	}
	
	public Integer getMsgId(){
		return msgId;
	}
	
	public void setContent(String content){
		this.content = content;
	}
	
	public String getContent(){
		return content;
	}
	
	public void setLink(String link){
		this.link = link;
	}
	
	public String getLink(){
		return link;
	}
	
	public void setStatus(Integer status){
		this.status = status;
	}
	
	public Integer getStatus(){
		return status;
	}
	
	public void setType(Integer type){
		this.type = type;
	}
	
	public Integer getType(){
		return type;
	}
	
	public void setUserNumber(String userNumber){
		this.userNumber = userNumber;
	}
	
	public String getUserNumber(){
		return userNumber;
	}
	
	public void setStartTime(Date startTime){
		this.startTime = startTime;
	}
	
	public Date getStartTime(){
		return startTime;
	}
	
	public void setCrtTime(Date crtTime){
		this.crtTime = crtTime;
	}
	
	public Date getCrtTime(){
		return crtTime;
	}
	
	public void setModifyTime(Date modifyTime){
		this.modifyTime = modifyTime;
	}
	
	public Date getModifyTime(){
		return modifyTime;
	}
	
	public void setAppType(Integer appType){
		this.appType = appType;
	}
	
	public Integer getAppType(){
		return appType;
	}


	 public String getMessage() {
		 return message;
	 }

	 public void setMessage(String message) {
		 this.message = message;
	 }
 }

