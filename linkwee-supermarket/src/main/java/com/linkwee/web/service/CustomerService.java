package com.linkwee.web.service;

import java.util.List;
import java.util.Map;

import com.linkwee.api.request.crm.UserTypeRequest;
import com.linkwee.api.response.crm.CustomerCfpmember;
import com.linkwee.api.response.crm.CustomerMemberDetailResponse;
import com.linkwee.api.response.crm.CustomerMemberInvestRecordResponse;
import com.linkwee.core.base.PaginatorSevResp;
import com.linkwee.core.base.api.PaginatorResponse;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.model.crm.CfpCustomerCountResp;
import com.linkwee.web.model.crm.CfplannerInvestorPersonResp;
import com.linkwee.web.model.crm.MycustomersResp;
import com.linkwee.web.model.crm.OrgSimpleResp;
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
public interface CustomerService {

	/**
	 * 客户首页统计信息
	 * @param userId
	 * @return
	 */
	CfpCustomerCountResp queryCfpCustomerCountResp(String userId);
	
	/**
	 * 客户列表
	 * @param request
	 * @return
	 */
	PaginatorSevResp<MycustomersResp> queryMycustomerList(Map<String, Object> query, Page<MycustomersResp> page);

	/**
	 * 客户累计投资统计
	 * @param userId
	 * @return
	 */
	Double queryTotalInvestAmt(String userId);
	
	/**
	 * 客户本月投资统计
	 * @param userId
	 * @return
	 */
	Double queryMonthInvestAmt(String userId);
	
	/**
	 * 客户今日投资统计
	 * @param userId
	 * @return
	 */
	Double queryDayInvestAmt(String userId);

	/**
	 * 查理财师某客户提供的佣金收益
	 * @param userId
	 * @param userId2
	 * @return
	 */
	Double queryFeeAmtByCfpAndInvestor(String cfplanner, String investor);

	/**
	 * 客户已注册平台列表
	 * @param userId
	 * @return
	 */
	List<OrgSimpleResp> queryRegisteredOrgList(String userId);
	
	/**
	 *  4.5.0我的-客户成员统计信息
	 * @param page
	 * @return
	 */
	CfplannerInvestorPersonResp queryCustomerMemberNum(String userId);

	/**
	 * 4.5.0我的-理财师团队成员分页
	 */
	PaginatorResponse<CustomerCfpmember> queryCustomerMemberPage(Page<CustomerCfpmember> page,
			UserTypeRequest req);

	/**
	 * 4.5.0我的-客户成员详情-投资记录
	 */
	PaginatorResponse<CustomerMemberInvestRecordResponse> customerInvestRecordPage(
			Page<CustomerMemberInvestRecordResponse> page, Map<String, Object> conditions);

	/**
	 * 4.5.0我的-客户成员详情统计信息
	 */
	CustomerMemberDetailResponse queryCustomerDetail(String userId, String profitUserId, String thisMonth);

	/**
	 * 4.5.0我的-职级页面查询客户优惠券和职级体验券数量
	 */
	int queryVoucherNum(String userId);

	/**
	 * 4.5.0已注册平台
	 */
	List<OrgSimpleResp> querycustomerCfpRegisteredOrgList(String userId);

	
	
}
