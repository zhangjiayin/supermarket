package com.linkwee.web.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.linkwee.api.request.crm.UserTypeRequest;
import com.linkwee.api.response.crm.CustomerCfpmember;
import com.linkwee.api.response.crm.CustomerMemberDetailResponse;
import com.linkwee.api.response.crm.CustomerMemberInvestRecordResponse;
import com.linkwee.core.base.BaseDao;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.model.crm.CfplannerInvestorPersonResp;
import com.linkwee.web.model.crm.MycustomersResp;
import com.linkwee.web.model.crm.OrgSimpleResp;

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
	
	/**
	 * 客户已注册平台列表
	 * @param userId
	 * @return
	 */
	public List<OrgSimpleResp> queryRegisteredOrgList(@Param("userId") String userId);
	
	/**
	 * 本月佣金
	 * @param userId
	 * @return
	 */
	public Double queryThisMonthFee(String userId);
	
	/**
	 *  4.5.0我的-客户成员统计信息
	 * @param page
	 * @return
	 */	
	public CfplannerInvestorPersonResp queryCustomerMemberNum(@Param("userId") String userId);
	
	/**
	 *  4.5.0我的-客户成员统计信息
	 * @param page
	 * @return
	 */	
	public List<CustomerCfpmember> queryCustomerMemberPage(Page<CustomerCfpmember> page, UserTypeRequest req);
	
	/**
	 * 4.5.0我的-客户成员详情-投资记录
	 */
	public List<CustomerMemberInvestRecordResponse> customerInvestRecordPage(
			Page<CustomerMemberInvestRecordResponse> page, Map<String, Object> conditions);
	
	/**
	 * 4.5.0我的-客户成员详情-投资记录-统计佣金
	 */
	public BigDecimal queryFeeAmount(@Param("investId")String investId);
	
	/**
	 * 4.5.0我的-客户成员详情统计信息
	 */
	public CustomerMemberDetailResponse queryCustomerDetail(@Param("userId")String userId, @Param("profitUserId")String profitUserId, @Param("thisMonth")String thisMonth);
	
	/**
	 * 4.5.0我的-职级页面查询客户优惠券和职级体验券数量
	 */
	public int queryVoucherNum(String userId);
	
	/**
	 *  4.5.0我的-客户成员排序
	 */
	public List<CustomerCfpmember> queryCustomerMemberPageOther(Page<CustomerCfpmember> page, UserTypeRequest req);
	
	/**
	 * 4.5.0已注册平台
	 */
	public List<OrgSimpleResp> querycustomerCfpRegisteredOrgList(String userId);
}
