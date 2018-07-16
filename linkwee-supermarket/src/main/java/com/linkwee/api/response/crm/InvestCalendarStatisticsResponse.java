package com.linkwee.api.response.crm;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linkwee.core.jackson.MoneySerializer;

public class InvestCalendarStatisticsResponse {
	
	/**
	 * 日历数量累计统计-网贷
	 */
	private List<CalendarStatisticsResponse> p2pCalendarStatisticsResponseList;
	
	/**
	 * 累计投资金额-网贷
	 */
	@JsonSerialize(using = MoneySerializer.class)
	private BigDecimal p2pInvestAmtTotal;
		
	/**
	 * 累计佣金-网贷
	 */
	@JsonSerialize(using = MoneySerializer.class)
	private BigDecimal p2pFeeAmountSumTotal;
	
	/**
	 * 日历数量累计统计-保险
	 */
	private List<CalendarStatisticsResponse> insuranceCalendarStatisticsResponseList;
	
	/**
	 * 累计投资金额-保险
	 */
	@JsonSerialize(using = MoneySerializer.class)
	private BigDecimal insuranceInvestAmtTotal;
		
	/**
	 * 累计佣金-保险
	 */
	@JsonSerialize(using = MoneySerializer.class)
	private BigDecimal insuranceFeeAmountSumTotal;
	
	/**
	 * 日历数量累计统计-最终
	 */
	private List<CalendarStatisticsResponse> calendarStatisticsResponseList;
	
	/**
	 * 累计投资金额-最终
	 */
	@JsonSerialize(using = MoneySerializer.class)
	private BigDecimal investAmtTotal;
		
	/**
	 * 累计佣金-最终
	 */
	@JsonSerialize(using = MoneySerializer.class)
	private BigDecimal feeAmountSumTotal;

	public List<CalendarStatisticsResponse> getP2pCalendarStatisticsResponseList() {
		return p2pCalendarStatisticsResponseList;
	}

	public void setP2pCalendarStatisticsResponseList(List<CalendarStatisticsResponse> p2pCalendarStatisticsResponseList) {
		this.p2pCalendarStatisticsResponseList = p2pCalendarStatisticsResponseList;
	}

	public BigDecimal getP2pInvestAmtTotal() {
		return p2pInvestAmtTotal;
	}

	public void setP2pInvestAmtTotal(BigDecimal p2pInvestAmtTotal) {
		this.p2pInvestAmtTotal = p2pInvestAmtTotal;
	}

	public BigDecimal getP2pFeeAmountSumTotal() {
		return p2pFeeAmountSumTotal;
	}

	public void setP2pFeeAmountSumTotal(BigDecimal p2pFeeAmountSumTotal) {
		this.p2pFeeAmountSumTotal = p2pFeeAmountSumTotal;
	}

	public List<CalendarStatisticsResponse> getInsuranceCalendarStatisticsResponseList() {
		return insuranceCalendarStatisticsResponseList;
	}

	public void setInsuranceCalendarStatisticsResponseList(
			List<CalendarStatisticsResponse> insuranceCalendarStatisticsResponseList) {
		this.insuranceCalendarStatisticsResponseList = insuranceCalendarStatisticsResponseList;
	}

	public BigDecimal getInsuranceInvestAmtTotal() {
		return insuranceInvestAmtTotal;
	}

	public void setInsuranceInvestAmtTotal(BigDecimal insuranceInvestAmtTotal) {
		this.insuranceInvestAmtTotal = insuranceInvestAmtTotal;
	}

	public BigDecimal getInsuranceFeeAmountSumTotal() {
		return insuranceFeeAmountSumTotal;
	}

	public void setInsuranceFeeAmountSumTotal(BigDecimal insuranceFeeAmountSumTotal) {
		this.insuranceFeeAmountSumTotal = insuranceFeeAmountSumTotal;
	}

	public List<CalendarStatisticsResponse> getCalendarStatisticsResponseList() {
		return calendarStatisticsResponseList;
	}

	public void setCalendarStatisticsResponseList(List<CalendarStatisticsResponse> calendarStatisticsResponseList) {
		this.calendarStatisticsResponseList = calendarStatisticsResponseList;
	}

	public BigDecimal getInvestAmtTotal() {
		investAmtTotal = p2pInvestAmtTotal.add(insuranceInvestAmtTotal);
		return investAmtTotal;
	}

	public void setInvestAmtTotal(BigDecimal investAmtTotal) {
		this.investAmtTotal = investAmtTotal;
	}

	public BigDecimal getFeeAmountSumTotal() {
		feeAmountSumTotal = p2pFeeAmountSumTotal.add(insuranceFeeAmountSumTotal);
		return feeAmountSumTotal;
	}

	public void setFeeAmountSumTotal(BigDecimal feeAmountSumTotal) {
		this.feeAmountSumTotal = feeAmountSumTotal;
	}
}
