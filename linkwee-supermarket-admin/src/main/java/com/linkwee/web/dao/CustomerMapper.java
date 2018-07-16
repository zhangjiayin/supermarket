package com.linkwee.web.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.linkwee.core.base.BaseDao;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.model.crm.MycustomersResp;

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
public interface CustomerMapper  extends BaseDao {

	/**
	 * 客户列表总数
	 * @param params
	 * @return
	 */
	public int queryMycustomersCount(Map<String,Object> params);
	/**
	 * 客户列表
	 * @param request
	 * @return
	 */
	public List<MycustomersResp> queryMycustomerList(Map<String, Object> query, Page<MycustomersResp> page);
	
	/**
	 * 客户累计投资统计
	 * @param userId
	 * @return
	 */
	public Double queryTotalInvestAmt(String userId);
	
	/**
	 * 客户本月投资统计
	 * @param userId
	 * @return
	 */
	public Double queryMonthInvestAmt(String userId);
	
	/**
	 * 客户今日投资统计
	 * @param userId
	 * @return
	 */
	public Double queryDayInvestAmt(String userId);
	
	/**
	 * 未读客户数量
	 * @param userId
	 * @param date
	 * @return
	 */
	public int queryNewCustomerCount(@Param("userId") String userId, @Param("date") Date date);
	
	/**
	 * 客户投资最早时间
	 * @param userId
	 * @return
	 */
	public Date queryInvestMinTime(String userId);
	
	/**
	 * 查理财师的某用户提供的佣金
	 * @param cfplanner
	 * @param investor
	 * @return
	 */
	public Double queryFeeAmtByCfpAndInvestor(@Param("cfplanner") String cfplanner, @Param("investor") String investor);
	
	
	
	
}
