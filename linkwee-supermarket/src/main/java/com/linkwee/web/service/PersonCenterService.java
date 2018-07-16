package com.linkwee.web.service;

import com.linkwee.api.request.crm.InvestCalendarDetailRequest;
import com.linkwee.api.request.crm.InvestCalendarRequest;
import com.linkwee.api.request.crm.InvestCalendarStatisticsRequest;
import com.linkwee.api.request.crm.RePaymentCalendarStatisticsRequest;
import com.linkwee.api.request.crm.RepamentCalendarRequest;
import com.linkwee.api.response.crm.InsuranceInvestCalendarDetailResponse;
import com.linkwee.api.response.crm.InsuranceInvestCalendarResponse;
import com.linkwee.api.response.crm.InvestCalendarDetailResponse;
import com.linkwee.api.response.crm.InvestCalendarResponse;
import com.linkwee.api.response.crm.InvestCalendarStatisticsResponse;
import com.linkwee.api.response.crm.RepamentCalendarResponse;
import com.linkwee.api.response.crm.RepaymentCalendarStatisticsResponse;
import com.linkwee.core.base.api.PaginatorResponse;
import com.linkwee.xoss.api.AppRequestHead;

public interface PersonCenterService {

	/**
	 * 查询回款日历列表
	 * @param appRequestHead
	 * @param repamentCalendarRequest
	 * @return
	 */
	PaginatorResponse<RepamentCalendarResponse> queryRepamentCalendarPageList(AppRequestHead appRequestHead,RepamentCalendarRequest repamentCalendarRequest);

	/**
	 * 查询交易日历
	 * @param appRequestHead
	 * @param investCalendarRequest
	 * @return
	 */
	PaginatorResponse<InvestCalendarResponse> queryInvestCalendar(AppRequestHead appRequestHead,InvestCalendarRequest investCalendarRequest);

	/**
	 * 查询保险交易日历
	 * @param appRequestHead
	 * @param investCalendarRequest
	 * @return
	 */
	PaginatorResponse<InsuranceInvestCalendarResponse> queryInsuranceCalendar(AppRequestHead appRequestHead,InvestCalendarRequest investCalendarRequest);
	
	/**
	 * 交易详情
	 * @param appRequestHead
	 * @param investCalendarDetailRequest
	 * @return
	 */
	InvestCalendarDetailResponse queryInvestCalendarDetail(AppRequestHead appRequestHead,InvestCalendarDetailRequest investCalendarDetailRequest);

	/**
	 * 交易日历统计
	 * @param investCalendarStatisticsRequest
	 * @return
	 */
	InvestCalendarStatisticsResponse investCalendarStatistics(InvestCalendarStatisticsRequest investCalendarStatisticsRequest);

	/**
	 * 回款日历统计
	 * @param rePaymentCalendarStatisticsRequest
	 * @return
	 */
	RepaymentCalendarStatisticsResponse repamentCalendarStatistics(RePaymentCalendarStatisticsRequest rePaymentCalendarStatisticsRequest);

	/**
	 * 查询保险交易详情
	 * @param appRequestHead
	 * @param investCalendarDetailRequest
	 * @return
	 */
	InsuranceInvestCalendarDetailResponse queryInsuranceInvestCalendarDetail(AppRequestHead appRequestHead,InvestCalendarDetailRequest investCalendarDetailRequest);
}
