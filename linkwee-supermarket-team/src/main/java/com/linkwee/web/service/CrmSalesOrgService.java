package com.linkwee.web.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.CrmSalesOrg;
import com.linkwee.web.request.CustomerStatisticalRequest;
import com.linkwee.web.request.DataStatisticsRequest;
import com.linkwee.web.request.LcsDetailRequest;
import com.linkwee.web.request.LcsFouseRequest;
import com.linkwee.web.request.LcsSaleRequest;
import com.linkwee.web.request.LcsSearchRequest;
import com.linkwee.web.request.LcsStatisticalRequest;
import com.linkwee.web.request.PlatformSaleRequest;
import com.linkwee.web.request.TeamSaleRequest;
import com.linkwee.web.request.TeamStatisticalRequest;
import com.linkwee.web.response.LcsDetailResponse;
import com.linkwee.web.response.LcsSaleInfoResponse;
import com.linkwee.web.response.LcsStatisticalResponse;
import com.linkwee.web.response.TeamStatisticalResponse;
import com.linkwee.web.response.TotalStatisticResponse;
import com.linkwee.web.response.UnRepaymentTotalResponse;
 /**
 * 
 * @描述： CrmSalesOrgService服务接口
 * 
 * @创建人： ch
 * 
 * @创建时间：2016年11月07日 11:41:24
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CrmSalesOrgService extends GenericService<CrmSalesOrg,Long>{
	
	List<Map<String, String>> getPlatfroms();

	LcsStatisticalResponse getLcsStatistical(String salesOrgId);
	
	DataTableReturn getLcsStatisticalList(String salesOrgId,LcsStatisticalRequest req);
	
	DataTableReturn getCustomerStatisticalList(String salesOrgId,CustomerStatisticalRequest req);
	
	
	TeamStatisticalResponse getTeamStatistical(String salesOrgId,TeamStatisticalRequest req);
	
	DataTableReturn getTeamStatisticalList(String salesOrgId,TeamStatisticalRequest req);
	
	//平台投资统计
	BigDecimal getInvestmentStatisticsTotal(String salesOrgId,String platfrom,String startTime,String endTime);
	
	List<Map<String, BigDecimal>> getInvestmentStatisticsList(String salesOrgId,String platfrom,String startTime,String endTime);

	/**
	 * 总体数据
	 * @param request
	 * @return
	 */
	TotalStatisticResponse queryTotalStatisticData(DataStatisticsRequest request);

	/**
	 * 平台出单
	 * @param salesOrgId
	 * @param req
	 * @return
	 */
	DataTableReturn getPlatformSale(PlatformSaleRequest req);

	/**
	 * 各层级出单
	 * @param req
	 * @return
	 */
	DataTableReturn getLevelSale(PlatformSaleRequest req);

	/**
	 * 团队出单记录
	 * @param req
	 * @return
	 */
	DataTableReturn lcsTeamSaleRecordList(TeamSaleRequest req);

	/**
	 * 团队最大层级
	 * @param salesOrgId
	 * @return
	 */
	int getTeamMaxDepth(String salesOrgId);

	/**
	 * 理财师详情
	 * @param request
	 * @return
	 */
	LcsDetailResponse lcsDetail(LcsDetailRequest request);

	/**
	 * 理财师出单信息
	 * @param request
	 * @return
	 */
	LcsSaleInfoResponse lcsSaleInfo(LcsSaleRequest request);
	
	/**
	 * 生成查询团队下级的SQL
	 * @param salesOrg
	 * @return
	 */
	public String generateTeamSql(LcsSaleRequest request);

	/**
	 * 理财师出单列表
	 * @param request
	 * @return
	 */
	DataTableReturn lcsSaleList(LcsSaleRequest request);

	/**
	 * 搜索理财师信息列表
	 * @param request
	 * @return
	 */
	DataTableReturn lcsSearchInfoList(LcsSearchRequest request);

	/**
	 * 记录搜索记录
	 * @param request
	 * @return
	 */
	BaseResponse lcsSearchRecord(LcsSearchRequest request);

	/**
	 * 搜索记录历史
	 * @param request
	 * @return
	 */
	BaseResponse lcsSearchHistory(LcsSearchRequest request);

	/**
	 * 关注理财师（更新状态）
	 * @param request
	 * @return
	 */
	BaseResponse lcsFouseRecord(LcsFouseRequest request);

	/**
	 * 关注的理财师信息列表
	 * @param request
	 * @return
	 */
	DataTableReturn lcsFouseList(LcsSearchRequest request);

	/**
	 * 未出单的理财师信息列表
	 * @param request
	 * @return
	 */
	DataTableReturn lcsUnSaleList(LcsSearchRequest request);

	/**
	 * 浮动期未回款出单记录
	 * @param req
	 * @return
	 */
	DataTableReturn lcsFlowDeadLineUnRepaymentList(TeamSaleRequest req);

	/**
	 * 浮动期未回款出单记录汇总
	 * @param salesOrgId
	 * @param req
	 * @return
	 */
	UnRepaymentTotalResponse unRepaymentListTotal(TeamSaleRequest req);
	
}
