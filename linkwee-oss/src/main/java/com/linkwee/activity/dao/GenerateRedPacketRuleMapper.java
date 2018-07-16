package com.linkwee.activity.dao;

import org.apache.ibatis.annotations.Param;

import com.linkwee.activity.model.GenerateRedPacketRule;
import com.linkwee.core.generic.GenericDao;

public interface GenerateRedPacketRuleMapper extends GenericDao<GenerateRedPacketRule, Long>{
	
	
	GenerateRedPacketRule getGenerateRedPacketRuleByActivityId(@Param("activityId")String activityId);
	
	int deleteActivityGenerateRule(@Param("activityId")String activityId);
	
}
