package com.linkwee.activity.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.linkwee.activity.model.UseRedPacketRule;
import com.linkwee.activity.model.UseRuleContext;
import com.linkwee.core.generic.GenericDao;

public interface UseRedPacketRuleMapper extends GenericDao<UseRedPacketRule, Long>{
	/**
	 * 批量插入
	 * @param useRedPacketRules
	 */
	public void inserts(List<UseRedPacketRule> useRedPacketRules);
	
	/**
	 * 获取活动红包使用规则
	 * @param activityId
	 * @return
	 */
	UseRedPacketRule getUseRuleByActivityId(@Param("activityId")String  activityId);
	
	/**
	 * 通过活动id查询红包使用规则
	 * @author yalin 
	 * @date 2016年6月30日 下午5:00:09  
	 * @param activityId
	 * @return
	 */
	List<UseRedPacketRule> queryAllUseRuleByActivityId(@Param("activityId")String  activityId);
	
	/**
	 * 获取所有产品编号
	 * @param activityId
	 * @return
	 */
	List<String> queryAllProductIdByActivityId(@Param("activityId")String  activityId);
	
	int deleteUseRedPacketRule(@Param("activityId")String  activityId) throws Exception;
	
	
}
