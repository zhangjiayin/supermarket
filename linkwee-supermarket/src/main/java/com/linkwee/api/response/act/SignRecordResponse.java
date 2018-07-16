package com.linkwee.api.response.act;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linkwee.core.base.BaseEntity;
import com.linkwee.core.jackson.MoneySerializer;

public class SignRecordResponse extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2753811527770548218L;

	/**
     *红包编号
     */
	private String redpacketId;
	
    /**
     *签到金额
     */
	@JsonSerialize(using=MoneySerializer.class)
	private BigDecimal signAmount;
	
    /**
     *翻倍金额
     */
	@JsonSerialize(using=MoneySerializer.class)
	private BigDecimal timesAmount;
	
    /**
     *1分享翻倍，2连续签到翻倍
     */
	private Integer timesType;
	
    /**
     *签到时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date signTime;
	
	/**
	 * 翻倍倍数
	 */
	private Integer times;
	
	/**
	 * 签到序号
	 */
	private Integer rownum;

	public String getRedpacketId() {
		return redpacketId;
	}

	public void setRedpacketId(String redpacketId) {
		this.redpacketId = redpacketId;
	}

	public BigDecimal getSignAmount() {
		return signAmount;
	}

	public void setSignAmount(BigDecimal signAmount) {
		this.signAmount = signAmount;
	}

	public BigDecimal getTimesAmount() {
		return timesAmount;
	}

	public void setTimesAmount(BigDecimal timesAmount) {
		this.timesAmount = timesAmount;
	}

	public Integer getTimesType() {
		return timesType;
	}

	public void setTimesType(Integer timesType) {
		this.timesType = timesType;
	}

	public Date getSignTime() {
		return signTime;
	}

	public void setSignTime(Date signTime) {
		this.signTime = signTime;
	}

	public Integer getTimes() {
		if(signAmount != null && timesAmount != null){
			return timesAmount.divide(signAmount).toBigInteger().intValue();
		}else{
			return 0;
		}
	}

	public void setTimes(Integer times) {
		this.times = times;
	}

	public Integer getRownum() {
		return rownum;
	}

	public void setRownum(Integer rownum) {
		this.rownum = rownum;
	}
	
}
