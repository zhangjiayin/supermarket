package com.linkwee.activity.service.impl;

import java.util.Date;

import org.apache.commons.lang.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linkwee.activity.dao.ActivityRedpacketMapper;
import com.linkwee.activity.model.ActivityRedpacket;
import com.linkwee.activity.service.ActivityRedpacketService;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.web.util.GenerateNumberUtils;
@Service
public class ActivityRedpacketServiceImpl extends GenericServiceImpl<ActivityRedpacket, Long>
		implements ActivityRedpacketService {

	
	private static final Logger LOGGER = LoggerFactory.getLogger(ActivityRedpacketServiceImpl.class);
	@Autowired
	private ActivityRedpacketMapper activityRedpacketMapper;
	
	@Override
	public GenericDao<ActivityRedpacket, Long> getDao() {
		return activityRedpacketMapper;
	}

	@Override
	public ActivityRedpacket insertActivityRedpacket(String activityId,String redpacketTypId,String name,String remark,Date date) throws Exception {
		try{
			ActivityRedpacket activityRedpacket = new ActivityRedpacket();
			String activityRedpacketFid = GenerateNumberUtils.generateKey();
			activityRedpacket.setFid(activityRedpacketFid);
			activityRedpacket.setActivityId(activityId);
			activityRedpacket.setRedPaperId(redpacketTypId);
			activityRedpacket.setShowName(name);
			activityRedpacket.setUseRemark(remark);
			activityRedpacket.setInitDate(date);
			activityRedpacket.setUpdateDate(date);
			insert(activityRedpacket);
			return activityRedpacket;
		}catch(Exception e){
			LOGGER.error("insertRedpacketType Exception activityId={},redpacketTypId={},name={},remark={},date={},exception={}", new Object[]{activityId,redpacketTypId,name,remark,date,e});
			throw e;
		}
	}

	@Override
	public ActivityRedpacket getActivityRedPaperByActivityAndRedPaperTypeId(
			String activityId, String redPaperTypeId) {
		return activityRedpacketMapper.getActivityRedPaperByActivityAndRedPaperTypeId(activityId, redPaperTypeId);
	}

	@Override
	public boolean updateActivityRedpacket(String activityId,String redpacketTypId, String name, String remark, Date date) throws Exception{
		try{
			Validate.notNull(getActivityRedPaperByActivityAndRedPaperTypeId(activityId,redpacketTypId),"红包不存在");
			ActivityRedpacket activityRedpacket = new ActivityRedpacket();
			activityRedpacket.setActivityId(activityId);
			activityRedpacket.setRedPaperId(redpacketTypId);
			activityRedpacket.setShowName(name);
			activityRedpacket.setUseRemark(remark);
			activityRedpacket.setUpdateDate(date);
			return activityRedpacketMapper.updateRedPaperByActivityAndRedPaperTypeId(activityRedpacket)>0;
		}catch(Exception e){
			LOGGER.error("updateActivityRedpacket Exception activityId={},redpacketTypId={},name={},remark={},date={},exception={}", new Object[]{activityId,redpacketTypId,name,remark,date,e});
			throw e;
		}
	}
	
	
	



}
