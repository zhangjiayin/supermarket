package com.linkwee.web.request;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.linkwee.core.datatable.DataTable;

public class TeamSaleRequest extends DataTable{

	/**
     *开始时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" , timezone="GMT+8")
	private Date startTime;
	/**
     *截止时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" , timezone="GMT+8")
	private Date endTime;	
	/**
     *销售机构编码
     */
	private String salesOrgId;
	/**
	 * 是否猎财首投
	 */
	private int isFirstInvest;
	/**
	 * 团队层级
	 */
	private int teamLevel;
	/**
	 * 姓名或手机号
	 */
	private String nameOrMobile;
	
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getSalesOrgId() {
		return salesOrgId;
	}
	public void setSalesOrgId(String salesOrgId) {
		this.salesOrgId = salesOrgId;
	}
	public int getIsFirstInvest() {
		return isFirstInvest;
	}
	public void setIsFirstInvest(int isFirstInvest) {
		this.isFirstInvest = isFirstInvest;
	}
	public int getTeamLevel() {
		return teamLevel;
	}
	public void setTeamLevel(int teamLevel) {
		this.teamLevel = teamLevel;
	}
	public String getNameOrMobile() {
		return nameOrMobile;
	}
	public void setNameOrMobile(String nameOrMobile) {
		this.nameOrMobile = nameOrMobile;
	}
}
