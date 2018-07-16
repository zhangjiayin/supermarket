package com.linkwee.web.request;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.linkwee.core.datatable.DataTable;

public class LcsSaleRequest extends DataTable{
	
	/**
	 * 手机号码
	 */
	private String lcsMobile;
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
	 * 产品状态
	 */
	private int productStatus;
	/**
	 * 理财师UserID
	 */
	private String lcsUserId;
	
	private String teamSql;

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
	public int getProductStatus() {
		return productStatus;
	}
	public void setProductStatus(int productStatus) {
		this.productStatus = productStatus;
	}
	public String getTeamSql() {
		return teamSql;
	}
	public void setTeamSql(String teamSql) {
		this.teamSql = teamSql;
	}
	public String getLcsMobile() {
		return lcsMobile;
	}
	public void setLcsMobile(String lcsMobile) {
		this.lcsMobile = lcsMobile;
	}
	public String getLcsUserId() {
		return lcsUserId;
	}
	public void setLcsUserId(String lcsUserId) {
		this.lcsUserId = lcsUserId;
	}
	
}
