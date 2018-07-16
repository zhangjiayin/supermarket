package com.linkwee.web.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.linkwee.core.base.BaseDao;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.model.crm.MonthSaleListAllowanceList;
import com.linkwee.web.model.crm.MonthSaleListResp;
import com.linkwee.web.model.crm.MonthSaleStatisticsResp;
import com.linkwee.web.model.crm.PartnerListResp;
import com.linkwee.web.model.crm.PartnerSaleRecordNewResp;
import com.linkwee.web.model.crm.PartnerSaleRecordResp;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年07月12日 10:11:24
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface PartnerMapper  extends BaseDao {

	/**
	 * 直接推荐人数
	 * @param userId
	 * @return
	 */
	public Integer queryMyRcCount(String userId);
	
	/**
	 * 间接推荐人数
	 * @param userId
	 * @return
	 */
	public Integer queryChildrenRcCount(String userId);
	
	/**
	 * 团队列表
	 * @param pageRequest
	 * @return
	 */
	public List<PartnerListResp> queryPartnerList(Map<String, Object> query, Page<PartnerListResp> page);

	/**
	 * 理财师销售首单时间
	 * @param userId
	 * @return
	 */
	public Date queryFirstRcpDate(@Param("parentId") String parentId, @Param("userId") String userId);

	/**
	 * 直接津贴
	 * @param parentId
	 * @param userId
	 * @return
	 */
	public Double queryAllowance(@Param("parentId") String parentId, @Param("userId") String userId);

	/**
	 * 间接津贴
	 * @param parentId
	 * @param userId
	 * @return
	 */
	public Double queryChildrenAllowance(@Param("parentId") String parentId, @Param("userId") String userId);

	/**
	 * 团队成员销售记录
	 * @param query
	 * @param page
	 * @return
	 */
	public List<PartnerSaleRecordResp> queryPartnerSaleRecord(Map<String, Object> query, Page<PartnerSaleRecordResp> page);

	/**
	 * 团队成员销售记录
	 * @param query
	 * @param page
	 * @return
	 */
	public List<PartnerSaleRecordNewResp> queryPartnerSaleRecordNew(Map<String, Object> query, Page<PartnerSaleRecordNewResp> page);
	
	/**
	 * 未读团队成员数量
	 * @param userId
	 * @param date
	 * @return
	 */
	public Integer queryNewPartnerCount(@Param("userId") String userId, @Param("date") Date date);

	/**
	 * 本月推荐奖励
	 * @param userId
	 * @return
	 */
	public Double queryThisMonthAllowance(String userId);

	/**
	 * 本月团队销售额
	 * @param userId
	 * @return
	 */
	public Double queryThisMonthTeamSaleAmount(String userId);

	/**
	 * 月份团队销售统计
	 * @param query
	 * @param page
	 * @return
	 */
	public MonthSaleStatisticsResp queryPartnerMonthSaleStatistics(Map<String, Object> query);

	/**
	 * 月份团队销售列表
	 * @param query
	 * @param page
	 * @return
	 */
	public List<MonthSaleListResp> queryPartnerMonthSaleList(Map<String, Object> query, Page<MonthSaleListResp> page);
	
	/**
	 * 月份团队销售统计3.0
	 * @param query
	 * @param page
	 * @return
	 */
	public MonthSaleStatisticsResp queryPartnerMonthSaleStatisticsNew(Map<String, Object> query);

	/**
	 * 月份团队销售列表3.0
	 * @param query
	 * @param page
	 * @return
	 */
	public List<MonthSaleListResp> queryPartnerMonthSaleListNew(Map<String, Object> query, Page<MonthSaleListResp> page);

	/**
	 * 津贴明细
	 * @param billId
	 * @return
	 */
	public List<MonthSaleListAllowanceList> queryAllwanceListByBillId(@Param("billId") String billId, @Param("userId") String userId);

	/**
	 * 团队销售总比数3.0
	 * @param userId
	 * @return
	 */
	public int queryTeamSalesCount(Map<String, Object> query);

	/**
	 * 团队销售总金额3.0
	 * @param userId
	 * @return
	 */
	public Double queryTeamSalesTotalAmount(Map<String, Object> query);

	/**
	 * 曾孙理财师数量
	 * @param userId
	 * @return
	 */
	public Integer queryGrandChildrenRcCount(String userId);
	
	
	
	
}
