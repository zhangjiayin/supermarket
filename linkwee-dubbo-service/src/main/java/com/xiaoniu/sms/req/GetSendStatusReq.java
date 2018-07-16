package com.xiaoniu.sms.req;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

public class GetSendStatusReq extends BaseReq implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String mobile;
	private int currPage = 1;
	private int pageSize = 20;
	private String startTime;//开始时间，请以yyyy-MM-dd HH:mm:ss格式传入
	private String endTime;//结束时间，请以yyyy-MM-dd HH:mm:ss格式传入
	private int status = -1;//发送短信结果
	
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public int getCurrPage() {
		return currPage;
	}
	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public boolean check(){
		if (StringUtils.isBlank(super.getPartnerId())) {
			return false;
		}
		if (StringUtils.isBlank(super.getModuleId())) {
			return false;
		}
		return true;
	}
}
