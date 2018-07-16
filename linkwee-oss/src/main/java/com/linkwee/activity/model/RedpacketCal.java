package com.linkwee.activity.model;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.annotation.JsonFormat;

public class RedpacketCal implements Serializable{
    
	private static final long serialVersionUID = -4141279311355008348L;
	
	@JsonFormat(pattern = "yyyy-MM-dd", timezone="GMT+8")
	private Date calDate; // 日期
	private Integer sendRedpacketlcsCounts; //发放红包的理财师人数
	private Integer lcsSendRedpacketCounts; //理财师发放的红包数量  
	private Integer lcsSendRedpacketCustomerCounts; //理财师发放红包的客户数量  
	private Double lcsSendRedpacketAmount; //理财师发放红包金额  
	private Integer useRedpacketCustomerCounts; //使用红包的客户数量
	private Integer redpacketUseCounts; //红包使用数量
	private Double redpacketUseAmount; //红包使用金额   
	private Double redpacketYearAmount; //红包产生的年化投资金额  
	
	
	
	
	public Date getCalDate() {
		return calDate;
	}




	public void setCalDate(Date calDate) {
		this.calDate = calDate;
	}




	public Integer getSendRedpacketlcsCounts() {
		return sendRedpacketlcsCounts;
	}




	public void setSendRedpacketlcsCounts(Integer sendRedpacketlcsCounts) {
		this.sendRedpacketlcsCounts = sendRedpacketlcsCounts;
	}




	public Integer getLcsSendRedpacketCounts() {
		return lcsSendRedpacketCounts;
	}




	public void setLcsSendRedpacketCounts(Integer lcsSendRedpacketCounts) {
		this.lcsSendRedpacketCounts = lcsSendRedpacketCounts;
	}




	public Integer getLcsSendRedpacketCustomerCounts() {
		return lcsSendRedpacketCustomerCounts;
	}




	public void setLcsSendRedpacketCustomerCounts(
			Integer lcsSendRedpacketCustomerCounts) {
		this.lcsSendRedpacketCustomerCounts = lcsSendRedpacketCustomerCounts;
	}




	public Double getLcsSendRedpacketAmount() {
		return lcsSendRedpacketAmount;
	}




	public void setLcsSendRedpacketAmount(Double lcsSendRedpacketAmount) {
		this.lcsSendRedpacketAmount = lcsSendRedpacketAmount;
	}




	public Integer getUseRedpacketCustomerCounts() {
		return useRedpacketCustomerCounts;
	}




	public void setUseRedpacketCustomerCounts(Integer useRedpacketCustomerCounts) {
		this.useRedpacketCustomerCounts = useRedpacketCustomerCounts;
	}




	public Integer getRedpacketUseCounts() {
		return redpacketUseCounts;
	}




	public void setRedpacketUseCounts(Integer redpacketUseCounts) {
		this.redpacketUseCounts = redpacketUseCounts;
	}




	public Double getRedpacketUseAmount() {
		return redpacketUseAmount;
	}




	public void setRedpacketUseAmount(Double redpacketUseAmount) {
		this.redpacketUseAmount = redpacketUseAmount;
	}




	public Double getRedpacketYearAmount() {
		return redpacketYearAmount;
	}




	public void setRedpacketYearAmount(Double redpacketYearAmount) {
		this.redpacketYearAmount = redpacketYearAmount;
	}




	@Override
	public String toString() {
		return  JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}