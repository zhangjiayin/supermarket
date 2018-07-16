package com.linkwee.web.service;

import java.util.List;
import java.util.Map;

import com.linkwee.api.activity.response.NewYearRewardResponse;
import com.linkwee.api.request.crm.CfplannerDataRequest;
import com.linkwee.api.request.crm.InvotationRequest;
import com.linkwee.api.request.crm.RegisterSevReq;
import com.linkwee.api.request.crm.UserTypeRequest;
import com.linkwee.api.response.crm.CfplannerDataResponse;
import com.linkwee.api.response.crm.CfplannerMemberDetailResponse;
import com.linkwee.api.response.crm.CfplannerMemberResponse;
import com.linkwee.api.response.crm.CustomerCfpmember;
import com.linkwee.api.response.crm.CustomerMemberInvestRecordResponse;
import com.linkwee.core.base.ServiceResponse;
import com.linkwee.core.base.api.PaginatorResponse;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.model.CrmCfplanner;
import com.linkwee.web.model.CrmInvestor;
import com.linkwee.web.model.crm.CfplannerInvestorPersonResp;
import com.linkwee.web.model.crm.InvotateUserListResp;

 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年07月12日 10:25:55
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CrmCfplannerService extends GenericService<CrmCfplanner,Long>{

	/**
	 * 查询CrmCfplanner列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);

	/**
	 * 号码是否已存在
	 * @param mobile
	 * @return
	 */
	boolean isExistsCfplanner(String mobile);
	
	/**
	 * 是否在被禁止登录90天内
	 * @param mobile
	 * @return
	 */
	boolean isDisabledLogin90days(String mobile);
	/**
	 * 禁止登录90天开始日期
	 * @param mobile
	 * @return
	 */
	public String queryDisabledLoginTime(String mobile);
	
	/**
	 * 根据电话号码获取用户
	 * @param mobile
	 * @return
	 */
	CrmCfplanner queryCfplannerByMobile(String mobile);
	
	/**
	 * 根据理财师编码号码获取用户
	 * @param number 理财师编码
	 * @return
	 */
	CrmCfplanner queryCfplannerByNumber(String number);

	/**
	 * 注册理财师
	 * @param registerSevReq
	 * @return
	 */
	ServiceResponse<Boolean> registerLcs(RegisterSevReq registerSevReq)throws Exception;

	/**
	 * 检测是否能被推荐
	 * @param number
	 * @param mobile
	 * @return
	 */
	ServiceResponse<Boolean> checkCfgRecommend(String recommendUserNumber, String mobile);
	
	/**
	 * 检测用户是否必须需要推荐才能注册
	 * @param mobile 被检测手机号
	 * @return
	 */
	boolean checkCfgNeedRc(String mobile);

	/**
	 * 查投资用户的理财师
	 * @param mobile
	 * @return
	 */
	CrmCfplanner queryCfplannerByInvestMobile(String investorMobile);
	
	/**
	 * 查投资用户的理财师
	 * @param mobileArray
	 * @return
	 */
	List<String> selectRegCfplanners(String[] mobileArray);
	
	/**
	 * 根据userId查理财师信息
	 * @param mobile
	 * @return
	 */
	public CrmCfplanner queryCfplannerByUserId(String userId);
	
	/**
	 * 更新理财师二维码字段
	 * @param userId
	 * @param qrcode
	 */
	public void updateCfpQrByUserId(String userId, String qrcode);
	
	/**
	 * 根据环信帐号查理财师信息
	 * @param easemobAcctList
	 * @return
	 */
	List<CrmCfplanner> queryCfplannerByEasemob(List<String> easemobAcctList);

	/**
	 * 查投资者的理财师
	 * @param investorUserId 投资人userId
	 * @return
	 */
	CrmCfplanner queryCfplannerByInvestor(String investorUserId);
	
	/**
	 * 查理财师的团队成员数量
	 * @param userId
	 * @return
	 */
	int queryTeamMemberCount(String userId);
	
	/**
	 * 更新理财师等级与经验
	 * @param userId 理财师用户编号
	 * @param level 理财师等级
	 * @param experience 理财师增加经验
	 */
	void updateCfplannerRankExperience(String userId,String level,Integer experience);

	/**
	 * 修改理财师
	 * @param bo
	 * @return
	 */
	int updateByUserId(CrmCfplanner bo);
	
	/**
	 * 查理财师的所有团队成员
	 * @param userId
	 * @return
	 */
	List<CrmCfplanner> queryTeamAllMember(String userId);
	
	/**
	 * 查理财师的一级下级成员
	 * @param userId
	 * @return
	 */
	List<CrmCfplanner> queryLowerLevelOne(String userId);

	/**
	 * 符合分配规则的理财师
	 * @return
	 */
	List<String> queryConformAllotRuleCfps();

	/**
	 * 查上级理财师
	 * @return
	 */
	CrmCfplanner queryParentByUserId(String userId);

	/**
	 * 理财师客户数量
	 * @param userId
	 * @return
	 */
	int queryCustomerCount(String userId);

	/**
	 * 用户是否锁定
	 * @param mobile
	 * @return
	 */
	boolean isLockedCfplanner(String mobile);

	/**
	 * 查规定分配自由用户的理财师
	 * @return
	 */
	List<String> querySpecifiedCfps();

	/**
	 * 邀请理财师记录
	 * @param req
	 * @param page
	 * @return
	 */
	PaginatorResponse<InvotateUserListResp> queryInvitationCfplannerRecord(InvotationRequest req,
			Page<InvotateUserListResp> page);

	/**
	 * 理财师邀请统计
	 * @param userId
	 * @return
	 */
	int queryInvitationCfplannerRecordStatistics(String userId);
	
	/**
	 * 是否新用户
	 * @param userId
	 * @return
	 */
	boolean isNew(String userId);

	/**
	 * V4.0个人中心
	 * @param userId
	 * @return
	 */
	CrmCfplanner personalCenter(String userId);
	
	/**
	 * V4.0个人名片--个人信息
	 * @param userId
	 * @return
	 */
	CrmCfplanner queryCfpUserInfo(String userId);

	/**
	 * V4.0邀请记录--推荐理财师
	 * @param userId
	 * @return
	 */
	PaginatorResponse<CrmCfplanner> invitationCfpList(Page<CrmCfplanner> page, Map<String, Object> conditions);

	/**
	 * V4.0邀请记录--客户
	 * @param userId
	 * @return
	 */
	PaginatorResponse<CrmCfplanner> invitationInvestorList(Page<CrmCfplanner> page, Map<String, Object> conditions);

	/**
	 * 从fromTime开始注册的理财师人数
	 * @param fromTime
	 * @return
	 */
	int queryNewCfpNumber(String fromTime);

	/**
	 * 理财师信息
	 * @param userId
	 * @return
	 */
	CrmCfplanner queryCfplannerInfo(String userId);

	/**
	 * 4.5.0我的-理财师团队成员分页
	 * @param page
	 * @return
	 */
	PaginatorResponse<CustomerCfpmember> queryCfpmemberPage(Page<CustomerCfpmember> page,
			UserTypeRequest req);

	/**
	 *  4.5.0我的-理财师成员详情-投资记录
	 * @param page
	 * @return
	 */
	PaginatorResponse<CustomerMemberInvestRecordResponse> cfpInvestRecordPage(
			Page<CustomerMemberInvestRecordResponse> page, Map<String, Object> conditions);
	/**
	 * 查理财师的客户成员数量
	 * @param userId
	 * @return
	 */
	int queryCustomerMember(String userId);

	/**
	 * 理财师的客户
	 * @param userId
	 * @return
	 */
	List<CrmInvestor> queryInvestorList(String userId);

	/**
	 *  4.5.0我的-理财师成员
	 * @param page
	 * @return
	 */
	PaginatorResponse<CfplannerMemberResponse> cfplannerMemberPage(Page<CfplannerMemberResponse> page,
			Map<String, Object> conditions);

	/**
	 *  4.5.0我的-理财师团队成员统计
	 * @param page
	 * @return
	 */
	CfplannerInvestorPersonResp queryCfplannerMemberNum(String userId);

	/**
	 * 4.5.0我的-理财师团队成员投资统计信息
	 */
	CfplannerMemberDetailResponse queryCfplannerDetail(String userId, String profitUserId, String thisMonth);

	/**
	 * 从fromTime开始到endTime注册的理财师人数
	 * @param fromTime endTime
	 * @return
	 */
	int query2017NewCfpNumber(String fromTime,String endTime);

	/**
	 * 伪造中奖名单
	 * @return
	 */
	List<NewYearRewardResponse> queryForgeRewardData();

	/**
	 * 理财师数量
	 * @return
	 */
	int lcsNumber();

	/**
	 * 最新注册的手机号码
	 * @return
	 */
	List<String> queryNewRegTop();

	/**
	 * 查询理财师顾问基础数据
	 * @param cfplannerDataRequest
	 * @return
	 */
	CfplannerDataResponse queryCfplannerData(CfplannerDataRequest cfplannerDataRequest);
}
