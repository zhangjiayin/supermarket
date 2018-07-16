package com.linkwee.web.response.tc;

import java.math.BigDecimal;

public class FeeSummaryResponse {
	
	private BigDecimal totalFeeAmt;
	private Integer totalNumber;
	private String year;
	private String month;
	
	public BigDecimal getTotalFeeAmt() {
		return totalFeeAmt;
	}
	public void setTotalFeeAmt(BigDecimal totalFeeAmt) {
		this.totalFeeAmt = totalFeeAmt;
	}
	public Integer getTotalNumber() {
		return totalNumber;
	}
	public void setTotalNumber(Integer totalNumber) {
		this.totalNumber = totalNumber;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}

}
