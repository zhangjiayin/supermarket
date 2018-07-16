package com.linkwee.web.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linkwee.core.jackson.MoneySerializer;

public class OrgSkipData {

	/**
	 * 名称
	 */
	private String name;
	/**
	 * 数值
	 */
	@JsonSerialize(using=MoneySerializer.class)
	private BigDecimal data;
	/**
	 * 单位
	 */
	private String unit;
	/**
	 * 链接
	 */
	private String skipUrl;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getData() {
		return data;
	}
	public void setData(BigDecimal data) {
		this.data = data;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getSkipUrl() {
		return skipUrl;
	}
	public void setSkipUrl(String skipUrl) {
		this.skipUrl = skipUrl;
	}	
}
