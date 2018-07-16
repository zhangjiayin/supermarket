package com.linkwee.web.model.cim;

import com.linkwee.core.datatable.DataTable;

public class CimProductDataTable extends DataTable {
	
	private String userName;
	private String userMobile;
	private String investTime;
	private String uploadTitle;
	private String payStatus;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserMobile() {
		return userMobile;
	}
	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}
	public String getInvestTime() {
		return investTime;
	}
	public void setInvestTime(String investTime) {
		this.investTime = investTime;
	}
	public String getUploadTitle() {
		return uploadTitle;
	}
	public void setUploadTitle(String uploadTitle) {
		this.uploadTitle = uploadTitle;
	}
	public String getPayStatus() {
		return payStatus;
	}
	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

	
	
	
}
