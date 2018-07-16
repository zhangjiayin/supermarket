package com.linkwee.api.response.crm;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linkwee.core.jackson.MoneySerializer;

public class RepaymentCalendarStatisticsResponse {
	
	/**
	 * 日历数量累计统计
	 */
	private List<CalendarStatisticsResponse> calendarStatisticsResponseList;
	
	/**
	 * 已回款金额
	 */
	@JsonSerialize(using = MoneySerializer.class)
	private BigDecimal havaRepaymentAmtTotal;
		
	/**
	 * 待回款金额
	 */
	@JsonSerialize(using = MoneySerializer.class)
	private BigDecimal waitRepaymentAmtTotal;

	public List<CalendarStatisticsResponse> getCalendarStatisticsResponseList() {
		return calendarStatisticsResponseList;
	}

	public void setCalendarStatisticsResponseList(
			List<CalendarStatisticsResponse> calendarStatisticsResponseList) {
		this.calendarStatisticsResponseList = calendarStatisticsResponseList;
	}

	public BigDecimal getHavaRepaymentAmtTotal() {
		return havaRepaymentAmtTotal;
	}

	public void setHavaRepaymentAmtTotal(BigDecimal havaRepaymentAmtTotal) {
		this.havaRepaymentAmtTotal = havaRepaymentAmtTotal;
	}

	public BigDecimal getWaitRepaymentAmtTotal() {
		return waitRepaymentAmtTotal;
	}

	public void setWaitRepaymentAmtTotal(BigDecimal waitRepaymentAmtTotal) {
		this.waitRepaymentAmtTotal = waitRepaymentAmtTotal;
	}
}
