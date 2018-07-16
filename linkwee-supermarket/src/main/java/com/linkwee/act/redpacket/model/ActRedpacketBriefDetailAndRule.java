package com.linkwee.act.redpacket.model;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ActRedpacketBriefDetailAndRule extends ActRedpacketRuleBrief implements Comparable<ActRedpacketBriefDetailAndRule>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	  /**
    *
    */
	private Integer id;
	
   /**
    *红包编号
    */
	private String redpacketId;
	
   /**
    *发放编号
    */
	private String sendId;
	
   /**
    *红包明细编号
    */
	private String redpacketDetailId;
	
   /**
    *红包名称
    */
	private String name;
	
   /**
    *红包金额
    */
	private BigDecimal money;
	
   /**
    *红包描述
    */
	private String remark;
	
   /**
    *红包类型 1=投资返现|2=现金红包|3=抵现红包
    */
	private Integer type;
	
   /**
    *1=未派发|2=未使用|3=已使用|4=已过期
    */
	private Integer status;
	
   /**
    *过期时间
    */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date expiresDate;
	
   /**
    *用户手机号码
    */
	private String userMobile;
	
   /**
    *用户名称
    */
	private String userName;
	
   /**
    *用户编号
    */
	private String userId;
	
   /**
    *理财师编号
    */
	private String cfplannerId;
	
   /**
    *理财师名称
    */
	private String cfplannerName;
	
   /**
    *理财师手机号码
    */
	private String cfplannerMobile;
	
   /**
    *创建时间
    */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date createTime;
	
   /**
    *更新时间
    */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date updateTime;
	
	/**
	 * 派发红包发送者Id
	 */
	private String senderUserId;

	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return id;
	}
	
	public void setRedpacketId(String redpacketId){
		this.redpacketId = redpacketId;
	}
	
	public String getRedpacketId(){
		return redpacketId;
	}
	
	public void setSendId(String sendId){
		this.sendId = sendId;
	}
	
	public String getSendId(){
		return sendId;
	}
	
	public void setRedpacketDetailId(String redpacketDetailId){
		this.redpacketDetailId = redpacketDetailId;
	}
	
	public String getRedpacketDetailId(){
		return redpacketDetailId;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public void setMoney(BigDecimal money){
		this.money = money;
	}
	
	public BigDecimal getMoney(){
		return money;
	}
	
	public void setRemark(String remark){
		this.remark = remark;
	}
	
	public String getRemark(){
		return remark;
	}
	
	public void setType(Integer type){
		this.type = type;
	}
	
	public Integer getType(){
		return type;
	}
	
	public void setStatus(Integer status){
		this.status = status;
	}
	
	public Integer getStatus(){
		return status;
	}
	
	public void setExpiresDate(Date expiresDate){
		this.expiresDate = expiresDate;
	}
	
	public Date getExpiresDate(){
		return expiresDate;
	}
	
	public void setUserMobile(String userMobile){
		this.userMobile = userMobile;
	}
	
	public String getUserMobile(){
		return userMobile;
	}
	
	public void setUserName(String userName){
		this.userName = userName;
	}
	
	public String getUserName(){
		return userName;
	}
	
	public void setUserId(String userId){
		this.userId = userId;
	}
	
	public String getUserId(){
		return userId;
	}
	
	public void setCfplannerId(String cfplannerId){
		this.cfplannerId = cfplannerId;
	}
	
	public String getCfplannerId(){
		return cfplannerId;
	}
	
	public void setCfplannerName(String cfplannerName){
		this.cfplannerName = cfplannerName;
	}
	
	public String getCfplannerName(){
		return cfplannerName;
	}
	
	public void setCfplannerMobile(String cfplannerMobile){
		this.cfplannerMobile = cfplannerMobile;
	}
	
	public String getCfplannerMobile(){
		return cfplannerMobile;
	}
	
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	
	public Date getCreateTime(){
		return createTime;
	}
	
	public void setUpdateTime(Date updateTime){
		this.updateTime = updateTime;
	}
	
	public Date getUpdateTime(){
		return updateTime;
	}

	@Override
	public int compareTo(ActRedpacketBriefDetailAndRule o) {
		//排序规则:大金额排序在前，小金额排序在后，金额相等比较过期日期，快过期红包的排序在前。
		if(this.money.compareTo(o.money) > 0){
			return -1;
		} else if(this.money.compareTo(o.money) == 0){
			if(this.expiresDate.compareTo(o.expiresDate) > 0){
				return 1;
			} else if(this.expiresDate.compareTo(o.expiresDate) == 0){
				return 0;
			} else {
				return -1;
			}
		} else {
			return 1;
		}
	}

	public String getSenderUserId() {
		return senderUserId;
	}

	public void setSenderUserId(String senderUserId) {
		this.senderUserId = senderUserId;
	}

}
