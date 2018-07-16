package com.linkwee.web.service;

import java.util.List;
import java.util.Map;

import com.linkwee.core.base.ServiceResponse;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.CrmCfpLevelRecord;
import com.linkwee.web.model.CrmCfpLevelRecordTemp;
import com.linkwee.web.model.CrmCfplanner;
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
	 * 更新销售机构编码
	 * @param salesOrgId
	 * @param mobile
	 */
	void updatesSalesOrgId(String salesOrgId, String mobile);
	
	/**
	 * 查询用户微信openId
	 */
	String queryWeiXinOpenId(String useId);

	/**
	 * 更新理财师职级
	 * @param startTime
	 * @param endTime
	 */
	void updatejobgradeByTime(String startTime, String endTime);

	/**
	 * 根据userid更新理财师表职级
	 * @param crmCfpLevelRecord
	 */
	void updatejobgradeByUserId(CrmCfpLevelRecord crmCfpLevelRecord);
	/**
	 * 根据userid更新理财师表职级
	 * @param crmCfpLevelRecordTemp
	 */
	void updatejobgradeTempByUserId(CrmCfpLevelRecordTemp crmCfpLevelRecordTemp);

	/**
	 * 根据理财师userid统计理财师下级职级
	 * @param userId
	 * @return
	 */
	Map<String, Integer> queryJobgradeCount(String userId);
	/**
	 * 根据理财师userid统计理财师(当前)下级职级
	 * @param userId
	 * @return
	 */
	Map<String, Integer> queryJobgradeTempCount(String userId);

	/**
	 * 根据理财师userid jobGrade统计理财师下级职级数量
	 * @param userId
	 * @param jobGrade
	 * @return
	 */
	int queryJobgradeCountByType(String userId, String jobGrade);

	/**
	 * 初始化理财师所有的职级
	 */
	void initJobgrade();
	
	/**
	 * 初始化理财师所有的职级(临时)
	 */
	void initJobgradeTemp();

}
