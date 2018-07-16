package com.linkwee.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.api.request.crm.InvestCalendarDetailRequest;
import com.linkwee.api.request.crm.InvestCalendarRequest;
import com.linkwee.api.request.crm.InvestCalendarStatisticsRequest;
import com.linkwee.api.response.crm.CalendarStatisticsResponse;
import com.linkwee.api.response.crm.InsuranceInvestCalendarDetailResponse;
import com.linkwee.api.response.crm.InsuranceInvestCalendarResponse;
import com.linkwee.api.response.crm.InsuranceInvestCalendarStatisticsResponse;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.model.CimInsuranceNotify;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2017年09月14日 16:54:47
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CimInsuranceNotifyMapper extends GenericDao<CimInsuranceNotify,Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<CimInsuranceNotify> selectBySearchInfo(@Param("dt")DataTable dt,RowBounds page);

	/**
	 * 查询保险投资日历
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
