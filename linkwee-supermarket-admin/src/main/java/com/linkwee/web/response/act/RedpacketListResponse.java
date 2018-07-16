package com.linkwee.web.response.act;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.linkwee.core.base.BaseEntity;

public class RedpacketListResponse extends BaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1321144167611526319L;
	
	private String redpacketId;
	private String name;
	private BigDecimal money;
	private Integer sendCount;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")  
	private Date sendTime;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")  
	private Date createTime;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone="GMT+8")  
	private Date maxExpiresTime;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone="GMT+8")  
	private Date minExpiresTime;
	private Integer sendNum;
	private String operator;
	
	/**
	 * 红包编辑权限
	 */
	private String redpacketEditPermission;
	/**
	 * 红包发放权限
	 */
	private String redpacketSendPermission;
	
	public String getRedpacketId() {
		return redpacketId;
	}
	public void setRedpacketId(String redpacketId) {
		this.redpacketId = redpacketId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMoney() {
		return money.setScale(2,BigDecimal.ROUND_HALF_UP).toString();
	}
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	
	public String getStatus(){
		return sendCount!=null && sendCount>0 ? "已发送":"未发送";
	}
	
	public Integer getSendCount() {
		return sendCount;
	}
	
	public void setSendCount(Integer sendCount) {
		this.sendCount = sendCount;
	}
	public Date getSendTime() {
		return sendTime;
	}
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getMaxExpiresTime() {
		return maxExpiresTime;
	}
	public void setMaxExpiresTime(Date maxExpiresTime) {
		this.maxExpiresTime = maxExpiresTime;
	}
	public Date getMinExpiresTime() {
		return minExpiresTime;
	}
	public void setMinExpiresTime(Date minExpiresTime) {
		this.minExpiresTime = minExpiresTime;
	}
	public Integer getSendNum() {
		return sendNum;
	}
	public void setSendNum(Integer sendNum) {
		this.sendNum = sendNum;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getRedpacketSendPermission() {
		return redpacketSendPermission;
	}
	public void setRedpacketSendPermission(String redpacketSendPermission) {
		this.redpacketSendPermission = redpacketSendPermission;
	}
	public String getRedpacketEditPermission() {
		return redpacketEditPermission;
	}
	public void setRedpacketEditPermission(String redpacketEditPermission) {
		this.redpacketEditPermission = redpacketEditPermission;
	}

	
}
