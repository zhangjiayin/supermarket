package com.linkwee.activity.dao;

import org.apache.ibatis.annotations.Param;

import com.linkwee.activity.model.ActivityRedpacket;
import com.linkwee.core.generic.GenericDao;

public interface ActivityRedpacketMapper extends GenericDao<ActivityRedpacket, Long>{
	/**
	 * 根据红包类型编号与活动编号查询红包描述
	 * @param activityId
	 * @param redPaperTypeId
	 * @return
	 */
	public ActivityRedpacket getActivityRedPaperByActivityAndRedPaperTypeId(@Param("activityId") String activityId, @Param("redPaperTypeId")String redPaperTypeId);
	
	
	public int updateRedPaperByActivityAndRedPaperTypeId(ActivityRedpacket activityRedpacket);

}
