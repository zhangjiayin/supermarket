package com.linkwee.activity.service;

import com.linkwee.activity.model.GenerateRedPacketRule;
import com.linkwee.activity.model.GenerateRuleContext;
import com.linkwee.core.generic.GenericService;

public interface GenerateRedPacketRuleService extends GenericService<GenerateRedPacketRule, Long>{
	
	/**
	 * 根据活动编号查询生成规则
	 * @param activityId
	 * @return
	 */
	GenerateRedPacketRule getGenerateRedPacketRuleByActivityId(String activityId);
	

	
	/**
	 * 添加活动红包生成规则
	 * @param generateRuleContext
	 * @return
	 * @throws Exception
	 */
	boolean insertActivityGenerateRule(GenerateRuleContext generateRuleContext)  throws Exception;
	
	/**
	 * 根据活动id删除生成规则
	 * @param activityId
	 * @return
	 */
	boolean deleteActivityGenerateRule(String activityId);
}
