package com.linkwee.web.response.tc;

import java.io.Serializable;
import java.math.BigDecimal;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class CurrentMonthPayFeeStatistics implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4630298232946812745L;

	/**
	 * 佣金
	 */
	private BigDecimal feeAmt;

	/**
	 * 佣金类型
	 */
	private String type;

	/**
	 * 人数
	 */
	private Integer number;

	public CurrentMonthPayFeeStatistics() {}

	public CurrentMonthPayFeeStatistics(BigDecimal feeAmt, String type,
			Integer number) {
		super();
		this.feeAmt = feeAmt;
		this.type = type;
		this.number = number;
	}

	public BigDecimal getFeeAmt() {
		return feeAmt;
	}

	public void setFeeAmt(BigDecimal feeAmt) {
		this.feeAmt = feeAmt;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}

}
