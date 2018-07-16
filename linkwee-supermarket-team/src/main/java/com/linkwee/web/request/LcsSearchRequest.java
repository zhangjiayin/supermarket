package com.linkwee.web.request;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.linkwee.core.datatable.DataTable;

public class LcsSearchRequest extends DataTable{

	/**
	 * 电话号码或姓名
	 */
	private String mobileOrName;
	/**
	 * 合伙人
	 */
	private String salesOrgId;
	/**
	 * 是否关注
	 */
	private int fouseFlag;	
	/**
     *开始时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" , timezone="GMT+8")
	private Date unSaleStartTime;	
	/**
     *截止时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" , timezone="GMT+8")
	private Date unSaleEndTime;
	
	private String teamSql;
	
	public String getMobileOrName() {
		return mobileOrName;
	}
	public void setMobileOrName(String mobileOrName) {
		this.mobileOrName = mobileOrName;
	}
	public String getSalesOrgId() {
		return salesOrgId;
	}
	public void setSalesOrgId(String salesOrgId) {
		this.salesOrgId = salesOrgId;
	}
	public int getFouseFlag() {
		return fouseFlag;
	}
	public void setFouseFlag(int fouseFlag) {
		this.fouseFlag = fouseFlag;
	}
	public Date getUnSaleStartTime() {
		return unSaleStartTime;
	}
	public void setUnSaleStartTime(Date unSaleStartTime) {
		this.unSaleStartTime = unSaleStartTime;
	}
	public Date getUnSaleEndTime() {
		return unSaleEndTime;
	}
	public void setUnSaleEndTime(Date unSaleEndTime) {
		this.unSaleEndTime = unSaleEndTime;
	}
	public String getTeamSql() {
		return teamSql;
	}
	public void setTeamSql(String teamSql) {
		this.teamSql = teamSql;
	}

}
