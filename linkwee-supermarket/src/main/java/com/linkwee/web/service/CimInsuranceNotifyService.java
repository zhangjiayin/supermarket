package com.linkwee.web.service;

import java.util.List;

import com.linkwee.api.request.crm.InvestCalendarDetailRequest;
import com.linkwee.api.request.crm.InvestCalendarRequest;
import com.linkwee.api.request.crm.InvestCalendarStatisticsRequest;
import com.linkwee.api.response.crm.CalendarStatisticsResponse;
import com.linkwee.api.response.crm.InsuranceInvestCalendarDetailResponse;
import com.linkwee.api.response.crm.InsuranceInvestCalendarResponse;
import com.linkwee.api.response.crm.InsuranceInvestCalendarStatisticsResponse;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.model.CimInsuranceNotify;
import com.linkwee.web.service.CimInsuranceNotifyService;
 /**
 * 
 * @描述： CimInsuranceNotifyService服务接口
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2017年09月14日 16:54:47
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CimInsuranceNotifyService extends GenericService<CimInsuranceNotify,Long>{

	/**
	 * 查询CimInsuranceNotify列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);

	/**
	 * 根据机构代码和投保单号查询相关的保单
	 * @param orgNumber
	 * @param insureNum
	 * @return
	 */
	CimInsuranceNotify selectByOrgNumberInsureNum(String orgNumber, String insureNum);

	/**
	 * 根据保险订单通知更新保险审核状态
	 * @param cimInsuranceNotify
	 */
	void updateStatus(CimInsuranceNotify cimInsuranceNotify);

	/**
	 * 查询保险保日历
	 * @param investCalendarRequest
	 * @param page
	 * @return
	 */
	List<InsuranceInvestCalendarResponse> queryInsuranceInvestCalendarPageList(InvestCalendarRequest investCalendarRequest,Page<InsuranceInvestCalendarResponse> page);

	/**
	 * 交易日历统计金额-保险
	 * @param investCalendarStatisticsRequest
	 * @return
	 */
	InsuranceInvestCalendarStatisticsResponse investCalendarStatisticsTotal(InvestCalendarStatisticsRequest investCalendarStatisticsRequest);

	/**
	 * 交易日历统计数量-保险
	 * @param investCalendarStatisticsRequest
	 * @return
	 */
	List<CalendarStatisticsResponse> investCalendarStatisticsNumber(InvestCalendarStatisticsRequest investCalendarStatisticsRequest);

	/**
	 * 查询保险交易详情
	 * @param investCalendarDetailRequest
	 * @return
	 */
	InsuranceInvestCalendarDetailResponse queryInvestCalendarDetail(InvestCalendarDetailRequest investCalendarDetailRequest);
}
