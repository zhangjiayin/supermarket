package com.linkwee.web.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.web.model.CrmCfplanner;
import com.linkwee.web.model.JobgradeCount;

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
	 * 修改销售机构编码
	 */
	void updatesSalesOrgId(@Param("salesOrgId")String salesOrgId, @Param("mobile")String mobile);

	/**
	 * 查询用户微信openId
	 */
	String queryWeiXinOpenId(String useId);

	/**
	 * 批量更新理财师职级
	 * @param updateRequestMap
	 */
	void batchUpdateJobGrade(@Param("query")Map<String, Object> updateRequestMap);

	/**
	 * 更新理财师的职级为见习理财师
	 * @param startTime
	 * @param endTime
	 */
	void updatejobgradeByTime(@Param("startTime")String startTime, @Param("endTime")String endTime);

	/**
	 * 根据userid更新理财师表职级
	 * @param userId
	 * @param level
	 */
	void updatejobgradeByUserId(@Param("userId")String userId, @Param("level")String level,@Param("levelWeight")Integer levelWeight);

	/**
	 * 根据理财师userid统计理财师下级职级
	 * @param userId
	 * @return
	 */
	List<JobgradeCount> queryJobgradeCount(@Param("userId")String userId);

	/**
	 * 根据理财师userid jobGrade统计理财师下级职级数量
	 * @param userId
	 * @param jobGrade
	 * @return
	 */
	int queryJobgradeCountByType(@Param("userId")String userId, @Param("jobGrade")String jobGrade);

	/**
	 * 初始化理财师所有的职级
	 */
	void initJobgrade();
	/**
	 * 初始化理财师所有的职级(临时)
	 */
	void initJobgradeTemp();

	/**
	 * 根据userid更新理财师表职级(临时)
	 * @param userId
	 * @param curLevel
	 * @param curLevelWeight
	 */
	void updatejobgradeTempByUserId(@Param("userId")String userId, @Param("level")String curLevel,@Param("levelWeight")Integer curLevelWeight);
	
	/**
	 * 根据理财师userid统计理财师(当前)下级职级
	 * @param userId
	 * @return
	 */
	List<JobgradeCount> queryJobgradeTempCount(String userId);

}
