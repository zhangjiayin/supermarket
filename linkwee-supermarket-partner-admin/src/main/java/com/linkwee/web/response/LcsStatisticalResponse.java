package com.linkwee.web.response;

import java.math.BigDecimal;

public class LcsStatisticalResponse {
	
	private Integer totalCount;
	private BigDecimal totalAmount;
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public BigDecimal getTotalAmount() {
		return totalAmount.setScale(2,BigDecimal.ROUND_DOWN);
	}
	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	
	
	
	
}
