package com.xiaoniu.account.domain;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 用户资产统计请求
 * @author 颜彩云
 *
 */
public class PartnerInvestReq implements Serializable {

	private static final long serialVersionUID = 121781158882363837L;
	
	private int currPage = 1;//当前页
	private int pageSize = 20;//每页显示的数量
	@NotNull
	private String partnerId;//交易/账户中心业务id
	@NotNull
	private String systemType;//用户中心的业务id
	
	private Integer day = 0;
	@NotNull
	private String startTime;// 开始时间，到天即可，yyyy-MM-dd
	@NotNull
	private String endTime;// 结束时间，到天即可，yyyy-MM-dd
	
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

	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	public String getSystemType() {
		return systemType;
	}

	public void setSystemType(String systemType) {
		this.systemType = systemType;
	}

	public Integer getDay() {
		return day;
	}

	public void setDay(Integer day) {
		this.day = day;
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

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
