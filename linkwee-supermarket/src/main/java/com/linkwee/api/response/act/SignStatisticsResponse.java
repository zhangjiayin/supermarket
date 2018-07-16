package com.linkwee.api.response.act;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linkwee.core.base.BaseEntity;
import com.linkwee.core.jackson.MoneySerializer;

public class SignStatisticsResponse extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2234244174450750373L;

	/*
	 * 累计签到金额
	 */
	@JsonSerialize(using=MoneySerializer.class)
	private BigDecimal totalBouns;
	/**
	 * 剩余签到金额
	 */
	@JsonSerialize(using=MoneySerializer.class)
	private BigDecimal leftBouns;
	/**
	 * 可转金额
	 */
	@JsonSerialize(using=MoneySerializer.class)
	private BigDecimal transferBouns;
	/**
	 * 首次签到的时间
	 */
	private Date firstSignTime;
	/**
	 * 已转金额
	 */
	@JsonSerialize(using=MoneySerializer.class)
	private BigDecimal transferedBouns;
	/**
	 * 用户姓名
	 */
	private String userName;
	/**
	 * 不满足转出条件描述
	 */
	private String dissatisfyDescription;

	public BigDecimal getTotalBouns() {
		return totalBouns;
	}

	public void setTotalBouns(BigDecimal totalBouns) {
		this.totalBouns = totalBouns;
	}

	public BigDecimal getLeftBouns() {
		return leftBouns;
	}

	public void setLeftBouns(BigDecimal leftBouns) {
		this.leftBouns = leftBouns;
	}

	public BigDecimal getTransferBouns() {
		return transferBouns;
	}

	public void setTransferBouns(BigDecimal transferBouns) {
		this.transferBouns = transferBouns;
	}

	public Date getFirstSignTime() {
		return firstSignTime;
	}

	public void setFirstSignTime(Date firstSignTime) {
		this.firstSignTime = firstSignTime;
	}

	public BigDecimal getTransferedBouns() {
		return transferedBouns;
	}

	public void setTransferedBouns(BigDecimal transferedBouns) {
		this.transferedBouns = transferedBouns;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDissatisfyDescription() {
		return dissatisfyDescription;
	}

	public void setDissatisfyDescription(String dissatisfyDescription) {
		this.dissatisfyDescription = dissatisfyDescription;
	}
	
}
