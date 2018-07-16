package com.linkwee.web.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.core.generic.GenericDao;
import com.linkwee.web.model.CrmSalesOrg;
import com.linkwee.web.request.DataStatisticsRequest;
import com.linkwee.web.request.LcsDetailRequest;
import com.linkwee.web.request.LcsFouseRequest;
import com.linkwee.web.request.LcsSaleRequest;
import com.linkwee.web.request.LcsSearchRequest;
import com.linkwee.web.request.PlatformSaleRequest;
import com.linkwee.web.request.TeamSaleRequest;
import com.linkwee.web.request.TeamStatisticalRequest;
import com.linkwee.web.response.CustomerStatisticalListResponse;
import com.linkwee.web.response.LcsDetailResponse;
import com.linkwee.web.response.LcsSaleInfoResponse;
import com.linkwee.web.response.LcsSaleListResponse;
import com.linkwee.web.response.LcsSearchInfoResponse;
import com.linkwee.web.response.LcsStatisticalListResponse;
import com.linkwee.web.response.LcsStatisticalResponse;
import com.linkwee.web.response.LevelSaleResponse;
import com.linkwee.web.response.PlatformSaleResponse;
import com.linkwee.web.response.TeamSaleRecordResponse;
import com.linkwee.web.response.TeamStatisticalListResponse;
import com.linkwee.web.response.TeamStatisticalResponse;
import com.linkwee.web.response.TotalStatisticResponse;
import com.linkwee.web.response.UnRepaymentTotalResponse;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： ch
 * 
 * @创建时间：2016年11月07日 11:41:24
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CrmSalesOrgMapper extends GenericDao<CrmSalesOrg,Long>{
	
	List<Map<String, String>> getPlatfroms();
	
	/**
	 * 获取存量
	 * @param salesOrgId
	 * @param req
	 * @return
	 */
	Map<String, BigDecimal> getStockYearpurAmt(@Param("salesOrgId")String salesOrgId,@Param("req")TeamStatisticalRequest req);
	
	LcsStatisticalResponse getLcsStatistical(@Param("salesOrgId")String salesOrgId);
	
	List<LcsStatisticalListResponse> getLcsStatisticalList(@Param("salesOrgId")String salesOrgId,@Param("nameOrMobile")String nameOrMobile,@Param("city")String city,RowBounds page);
	
	List<CustomerStatisticalListResponse> getCustomerStatisticalList(@Param("salesOrgId")String salesOrgId,@Param("mobile")String mobile,@Param("nameOrMobile")String nameOrMobile,RowBounds page);
	
	List<TeamStatisticalResponse> getTeamStatistical(@Param("salesOrgId")String salesOrgId,@Param("req")TeamStatisticalRequest req);
	
	List<TeamStatisticalListResponse> getTeamStatisticalList(@Param("salesOrgId")String salesOrgId,@Param("req")TeamStatisticalRequest req,RowBounds page);
	
	/**
	 * 查询机构在投
	 * @param platfrom
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	BigDecimal getInvestmentStatisticsTotal(@Param("salesOrgId")String salesOrgId,@Param("platfrom")String platfrom,@Param("startTime")String startTime,@Param("endTime")String endTime);
	
	List<Map<String, BigDecimal>> getInvestmentStatisticsList(@Param("salesOrgId")String salesOrgId,@Param("platfrom")String platfrom,@Param("startTime")String startTime,@Param("endTime")String endTime);

	/**
	 * 总体数据
	 * @param request
	 * @return
	 */
	TotalStatisticResponse queryTotalStatisticData(DataStatisticsRequest request);

	/**
	 * 平台出单情况
	 * @param req
	 * @param page
	 * @return
	 */
	List<PlatformSaleResponse> getPlatformSale(PlatformSaleRequest req,RowBounds page);

	/**
	 * 团队各层级出单情况
	 * @param req
	 * @param page
	 * @return
	 */
	List<LevelSaleResponse> getLevelSale(PlatformSaleRequest req,RowBounds page);

	/**
	 * 团队出单记录
	 * @param req
	 * @param page
	 * @return
	 */
	List<TeamSaleRecordResponse> lcsTeamSaleRecordList(TeamSaleRequest req,RowBounds page);

	/**
	 * 团队最大层级
	 * @param salesOrgId
	 * @return
	 */
	int getTeamMaxDepth(@Param("salesOrgId")String salesOrgId);

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
	 * 理财师出单列表
	 * @param request
	 * @param page
	 * @return
	 */
	List<LcsSaleListResponse> lcsSaleList(LcsSaleRequest request,RowBounds page);

	/**
	 * 按手机查理财师userId
	 * @param lcsMobile
	 * @return
	 */
	String queryLcsUserId(@Param("lcsMobile")String lcsMobile);

	/**
	 * 理财师信息列表
	 * @param request
	 * @param page
	 * @return
	 */
	List<LcsSearchInfoResponse> lcsSearchInfoList(LcsSearchRequest request,RowBounds page);
	
	/**
	 * 理财师下级人数
	 * @param request
	 * @return
	 */
	int lcsTeamNumber(LcsSaleRequest request);

	/**
	 * 记录搜索记录
	 * @param request
	 */
	void lcsSearchRecord(LcsSearchRequest request);

	/**
	 * 搜索记录历史
	 * @param salesOrgId
	 * @return
	 */
	List<String> searchHistory(@Param("salesOrgId")String salesOrgId);

	/**
	 * 是否关注过
	 * @param request
	 * @return
	 */
	int hasFoused(LcsFouseRequest request);

	/**
	 * 更新关注状态
	 * @param request
	 */
	void updateLcsFouseStatus(LcsFouseRequest request);

	/**
	 * 插入关注记录
	 * @param request
	 */
	void insertFouseStatus(LcsFouseRequest request);

	/**
	 * 关注的理财师信息列表
	 * @param request
	 * @param page
	 * @return
	 */
	List<LcsSearchInfoResponse> lcsFouseList(LcsSearchRequest request,RowBounds page);

	/**
	 * 未出单的理财师信息列表
	 * @param request
	 * @param page
	 * @return
	 */
	List<LcsSearchInfoResponse> lcsUnSaleList(LcsSearchRequest request,RowBounds page);

	/**
	 * 浮动期未回款出单记录
	 * @param req
	 * @param page
	 * @return
	 */
	List<TeamSaleRecordResponse> lcsFlowDeadLineUnRepaymentList(TeamSaleRequest req, RowBounds page);

	/**
	 * 浮动期未回款出单记录汇总
	 * @param req
	 * @return
	 */
	UnRepaymentTotalResponse unRepaymentListTotal(TeamSaleRequest req);
	
}
