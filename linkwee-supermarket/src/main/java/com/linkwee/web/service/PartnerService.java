package com.linkwee.web.service;

import java.util.Date;
import java.util.Map;

import com.linkwee.api.response.crm.JobGradeVoucherResponse;
import com.linkwee.api.response.crm.PartnerJobGradeResponse;
import com.linkwee.core.base.PaginatorSevResp;
import com.linkwee.core.base.api.PaginatorResponse;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.model.CrmCfplanner;
import com.linkwee.web.model.crm.JobGradeVoucherPopupResponse;
import com.linkwee.web.model.crm.MonthSaleListResp;
import com.linkwee.web.model.crm.MonthSaleStatisticsResp;
import com.linkwee.web.model.crm.PartnerDetailResp;
import com.linkwee.web.model.crm.PartnerListResp;
import com.linkwee.web.model.crm.PartnerSaleRecordNewResp;
import com.linkwee.web.model.crm.PartnerSaleRecordResp;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年07月12日 10:11:24
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface PartnerService {

	/**
	 * 理财师直接推荐人数
	 * @param userId
	 * @return
	 */
	Integer queryMyRcCount(String userId);

	/**
	 * 理财师间接推荐人数
	 * @param userId
	 * @return
	 */
	Integer queryChildrenRcCount(String userId);

	/**
	 * 团队列表
	 * @param pageRequest
	 * @return
	 */
	PaginatorSevResp<PartnerListResp> queryPartnerList(Map<String, Object> query, Page<PartnerListResp> page);

	/**
	 * 团队成员详情
	 * @param userId
	 * @return
	 */
	PartnerDetailResp queryDetailResp(String userId);

	/**
	 * 团队成员销售记录
	 * @param query
	 * @param page
	 * @return
	 */
	PaginatorSevResp<PartnerSaleRecordResp> queryPartnerSaleRecord(Map<String, Object> query,
			Page<PartnerSaleRecordResp> page);
	
	/**
	 * 团队成员销售记录3.0
	 * @param query
	 * @param page
	 * @return
	 */
	PaginatorSevResp<PartnerSaleRecordNewResp> queryPartnerSaleRecordNew(Map<String, Object> query,
			Page<PartnerSaleRecordNewResp> page);

	/**
	 * 未读团队成员数量
	 * @param userId
	 * @param date
	 * @return
	 */
	Integer queryNewPartnerCount(String userId, Date date);

	/**
	 * 本月推荐奖励
	 * @param userId
	 * @return
	 */
	Double queryThisMonthAllowance(String userId);

	/**
	 * 本月团队销售额
	 * @param userId
	 * @return
	 */
	Double queryThisMonthTeamSaleAmount(String userId);

	/**
	 * 月份团队销售统计
	 * @param query
	 * @param page
	 * @return
	 */
	MonthSaleStatisticsResp queryPartnerMonthSaleStatistics(Map<String, Object> query);

	/**
	 * 月份团队销售列表
	 */
	PaginatorSevResp<MonthSaleListResp> queryPartnerMonthSaleList(Map<String, Object> query,
			Page<MonthSaleListResp> page);
	
	/**
	 * 月份团队销售统计3.0
	 * @param query
	 * @param page
	 * @return
	 */
	MonthSaleStatisticsResp queryPartnerMonthSaleStatisticsNew(Map<String, Object> query);

	/**
	 * 月份团队销售列表3.0
	 */
	PaginatorSevResp<MonthSaleListResp> queryPartnerMonthSaleListNew(Map<String, Object> query,
			Page<MonthSaleListResp> page);

	/**
	 * 直属理财师团队
	 */
	PaginatorSevResp<CrmCfplanner> queryCfpList(Map<String, Object> query,
			Page<CrmCfplanner> page);

	/**
	 * 团队销售总笔数
	 * @param userId
	 * @return
	 */
	int queryTeamSalesCount(Map<String, Object> query);

	/**
	 * 团队销售总金额
	 * @param userId
	 * @return
	 */
	Double queryTeamSalesTotalAmount(Map<String, Object> query);

	/**
	 * 曾孙理财师数量
	 * @param userId
	 * @return
	 */
	Integer queryGrandChildrenRcCount(String userId);

	/**
	 * 理财师职级详情查询
	 * @param userId
	 * @return
	 */
	PartnerJobGradeResponse jobGrade(String userId);
	
	/**
	 * 4.5.0职级体验券列表
	 * @param userId
	 * @return
	 */
	PaginatorResponse<JobGradeVoucherResponse> jobGradeVoucherPage(Page<JobGradeVoucherResponse> page,
			Map<String, Object> conditions);

	/**
	 *  4.5.0职级体验券弹出框判断时间
	 * @param userId
	 * @return
	 */
	JobGradeVoucherPopupResponse queryNewJobGradeVoucherPopupDate(String userId);
}
