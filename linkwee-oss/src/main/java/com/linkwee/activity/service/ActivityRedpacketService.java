package com.linkwee.activity.service;

import java.util.Date;

import com.linkwee.activity.model.ActivityRedpacket;
import com.linkwee.core.generic.GenericService;

public interface ActivityRedpacketService extends GenericService<ActivityRedpacket, Long>{

	/**
	 * 添加红包活动信息
	 * @param activityId
	 * @param redpacketTypId
	 * @param name
	 * @param remark
	 * @param date
	 * @return
	 * @throws Exception
	 */
	ActivityRedpacket insertActivityRedpacket(String activityId,String redpacketTypId,String name,String remark,Date date) throws Exception;
	
	
	/**
	 * 根据活动编号与类型编号获取 ActivityRedpacket 对象
	 * @param activityId
	 * @param redPaperTypeId
	 * @return
	 */
	public ActivityRedpacket getActivityRedPaperByActivityAndRedPaperTypeId(String activityId,String redPaperTypeId);
	
	boolean updateActivityRedpacket(String activityId,String redpacketTypId,String name,String remark,Date date)throws Exception;
}
