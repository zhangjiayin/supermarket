package com.linkwee.web.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.api.activity.response.NewYearRewardResponse;
import com.linkwee.api.request.crm.CfplannerDataRequest;
import com.linkwee.api.request.crm.InvotationRequest;
import com.linkwee.api.request.crm.UserTypeRequest;
import com.linkwee.api.response.crm.CfplannerMemberDetailResponse;
import com.linkwee.api.response.crm.CfplannerMemberResponse;
import com.linkwee.api.response.crm.CustomerCfpmember;
import com.linkwee.api.response.crm.CustomerMemberInvestRecordResponse;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.model.CrmCfplanner;
import com.linkwee.web.model.CrmInvestor;
import com.linkwee.web.model.crm.CfplannerInvestorPersonResp;
import com.linkwee.web.model.crm.InvotateUserListResp;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年07月12日 10:25:55
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CrmCfplannerMapper extends GenericDao<CrmCfplanner,Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<CrmCfplanner> selectBySearchInfo(@Param("dt")DataTable dt,RowBounds page);

	 /**
     * 封装对象条件查询
     * @param o
     * @return
     */
	List<CrmCfplanner> selectByCondition(CrmCfplanner o);

	/**
	 * 查投资用户的理财师
	 * @param mobile
	 * @return CrmCfplanner
	 */
	CrmCfplanner queryCfplannerByInvestMobile(String investorMobile);
	
	/**
	 * 根据环信帐号查用户信息
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
	 * 查询手机号码已注册的理财师userId
	 * @param mobiles
	 * @return
	 */
	List<String> selectRegCfplanners(String[] mobiles);
	
	/**
	 * 更新理财师二维码字段
	 * @param record
	 * @return
	 */
	int updateCfpQrByUserId(CrmCfplanner record);

	/**
	 * 查理财师的团队人数
	 * @param userId
	 * @return
	 */
	int queryTeamMemberCount(String userId);

	/**
	 * 修改理财师信息
	 * @param crmCfplanner
	 * @return
	 */
	int updateByUserId(CrmCfplanner crmCfplanner);

	
	/**
	 * 更新理财师等级与经验
	 * @param userId 理财师用户编号
	 * @param level 理财师等级
	 * @param experience 理财师增加经验
	 */
	void updateCfplannerRankExperience(@Param("userId")String userId,@Param("level")String level,@Param("experience")Integer experience);

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
	 * 查理财师的一级下级成员-分页
	 * @param query
	 * @param page
	 * @return
	 */
	public List<CrmCfplanner> queryLowerLevelOneList(Map<String, Object> query, Page<CrmCfplanner> page);

	/**
	 * 符合分配规则的理财师
	 * @return
	 */
	List<String> queryConformAllotRuleCfps();
	
	/**
	 * 7天内登录过的理财师
	 * @return
	 */
	List<String> queryLoginInSevenDaysCfp();

	/**
	 * 理财师客户数量
	 * @param userId
	 * @return
	 */
	int queryCustomerCount(String userId);

	/**
	 * 查规定分配自由用户的理财师
	 * @return
	 */
	List<String> querySpecifiedCfps();

	/**
	 * 理财师邀请记录列表
	 * @param req
	 * @param page
	 * @return
	 */
	List<InvotateUserListResp> queryInvitationCfplannerRecord(InvotationRequest req, Page<InvotateUserListResp> page);

	/**
	 * 理财师邀请记录统计
	 * @param userId
	 * @return
	 */
	int queryInvitationCfplannerRecordStatistics(String userId);

	/**
	 * 查销售机构数量
	 * @param salesOrgId
	 * @return
	 */
	int querySalesOrgCount(String salesOrgId);
	
	/**
	 * 是否新用户
	 * @param userId
	 */
	int isNew(@Param("userId")String userId);

	/**
	 * 增加邀请人数
	 * @param userId
	 */
	void updateInvitNumber(@Param("userId")String userId);

	/**
	 * V4.0个人中心
	 * @param userId
	 */
	CrmCfplanner personalCenter(String userId);

	/**
	 * V4.0个人名片--个人信息
	 * @param userId
	 */
	CrmCfplanner queryCfpUserInfo(String userId);

	/**
	 * V4.0邀请记录--推荐理财师
	 */
	List<CrmCfplanner> invitationCfpList(Page<CrmCfplanner> page, Map<String, Object> conditions);

	/**
	 * V4.0邀请记录--邀请客户
	 */
	List<CrmCfplanner> invitationInvestorList(Page<CrmCfplanner> page, Map<String, Object> conditions);

	/**
	 * 从fromTime开始注册的理财师人数
	 * @param fromTime
	 * @return
	 */
	int queryNewCfpNumber(@Param("fromTime")String fromTime);

	/**
	 * 理财师信息
	 * @param userId
	 * @return
	 */
	CrmCfplanner queryCfplannerInfo(String userId);

	/**
	 * 理财师的客户数量
	 * @param userId
	 * @return
	 */
	int queryCustomerMember(@Param("userId")String userId);

	/**
	 * 理财师的客户
	 * @param userId
	 * @return
	 */
	List<CrmInvestor> queryInvestorList(@Param("userId")String userId);

	/**
	 *  4.5.0我的-理财师团队成员统计信息
	 * @param page
	 * @return
	 */
	CfplannerInvestorPersonResp queryCfplannerMemberNum(@Param("userId")String userId);

	/**
	 *  4.5.0我的-理财师团队成员分页查询
	 */
	List<CustomerCfpmember> queryCfpmemberPage(Page<CustomerCfpmember> page, UserTypeRequest req);
	
	/**
	 *  4.5.0我的-直接下级理财师成员
	 */
	List<CfplannerMemberResponse> cfplannerMemberPage(Page<CfplannerMemberResponse> page,
			Map<String, Object> conditions);

	/**
	 *  4.5.0我的-理财师团队详情-投资记录分页
	 */
	List<CustomerMemberInvestRecordResponse> cfpInvestRecordPage(Page<CustomerMemberInvestRecordResponse> page,
			Map<String, Object> conditions);
	/**
	 *  4.5.0我的-理财师团队详情-投资记录分页-统计佣金
	 */
	BigDecimal queryFeeAmountTotal(@Param("investId")String investId,@Param("userId")String userId);
	
	/**
	 * 4.5.0我的-理财师团队成员详情统计信息
	 */
	CfplannerMemberDetailResponse queryCfplannerDetail(@Param("userId")String userId, @Param("profitUserId")String profitUserId, @Param("thisMonth")String thisMonth);

	/**
	 *  4.5.0我的-理财师团队成员分页查询
	 */
	List<CustomerCfpmember> queryCfpmemberPageOther(Page<CustomerCfpmember> page, UserTypeRequest req);

	/**
	 * 从fromTime开始到endTime注册的理财师人数
	 * @param fromTime endTime
	 * @return
	 */
	int query2017NewCfpNumber(@Param("fromTime")String fromTime, @Param("endTime")String endTime);

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
	 * 最新注册的理财师
	 * @return
	 */
	List<String> queryNewRegTop();

	/**
	 * 查询理财师当月理财业绩
	 * @param cfplannerDataRequest
	 * @return
	 */
	BigDecimal queryCfplannerMonthInvestAmt(CfplannerDataRequest cfplannerDataRequest);
	
	/**
	 * 查询理财师当月收入
	 * @param cfplannerDataRequest
	 * @return
	 */
	BigDecimal queryCfplannerMonthIncome(CfplannerDataRequest cfplannerDataRequest);
}
