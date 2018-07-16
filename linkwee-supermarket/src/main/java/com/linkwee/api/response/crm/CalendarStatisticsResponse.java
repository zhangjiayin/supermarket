package com.linkwee.api.response.crm;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class CalendarStatisticsResponse {

	/**
	 * 日历时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd", timezone="GMT+8")
	private Date calendarTime;
	
	/**
	 * 日历数量
	 */
	private Integer calendarNumber;

	public Date getCalendarTime() {
		return calendarTime;
	}

	public void setCalendarTime(Date calendarTime) {
		this.calendarTime = calendarTime;
	}

	public Integer getCalendarNumber() {
		return calendarNumber;
	}

	public void setCalendarNumber(Integer calendarNumber) {
		this.calendarNumber = calendarNumber;
	}
}
