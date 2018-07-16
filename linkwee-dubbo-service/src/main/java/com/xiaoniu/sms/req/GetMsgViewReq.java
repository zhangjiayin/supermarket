package com.xiaoniu.sms.req;

import java.io.Serializable;
import java.util.Date;

public class GetMsgViewReq implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7268495841988970779L;

	private String partnerId;
	private String moduleId;
	private Integer type=2;
	private Integer msgType;
	private Integer status;
	private Date startDate;
	private Date endDate;
	public String getPartnerId() {
		return partnerId;
	}
	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}
	public String getModuleId() {
		return moduleId;
	}
	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getMsgType() {
		return msgType;
	}
	public void setMsgType(Integer msgType) {
		this.msgType = msgType;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	boolean check(){
		if(partnerId == null) { 
			return false;
		}
		if(moduleId == null) {
			return false;
		}
		if(type == null) {
			return false;
		}
		return true;
	}
}
