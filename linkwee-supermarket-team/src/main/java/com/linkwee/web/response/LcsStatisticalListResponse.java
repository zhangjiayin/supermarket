package com.linkwee.web.response;

import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.annotation.JsonFormat;

public class LcsStatisticalListResponse {
	
	private String name;
	private String mobile;
	private String city;
	private String depth;
	private String customers;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	private Date time;
	private BigDecimal totalAmount;
	private BigDecimal totalFee;
	private BigDecimal monthFee;
	private BigDecimal monthAmount;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDepth() {
		return depth;
	}
	public void setDepth(String depth) {
		this.depth = depth;
	}
	public String getCustomers() {
		return customers;
	}
	public void setCustomers(String customers) {
		this.customers = customers;
	}
	
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public BigDecimal getTotalAmount() {
		return totalAmount.setScale(2,BigDecimal.ROUND_DOWN);
	}
	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
	public BigDecimal getTotalFee() {
		return totalFee.setScale(2,BigDecimal.ROUND_DOWN);
	}
	public void setTotalFee(BigDecimal totalFee) {
		this.totalFee = totalFee;
	}
	public BigDecimal getMonthFee() {
		return monthFee.setScale(2,BigDecimal.ROUND_DOWN);
	}
	public void setMonthFee(BigDecimal monthFee) {
		this.monthFee = monthFee;
	}
	public BigDecimal getMonthAmount() {
		return monthAmount.setScale(2,BigDecimal.ROUND_DOWN);
	}
	public void setMonthAmount(BigDecimal monthAmount) {
		this.monthAmount = monthAmount;
	}
	
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}

}
