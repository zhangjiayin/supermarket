package com.linkwee.activity.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.linkwee.activity.dao.ActivityMapper;
import com.linkwee.activity.dao.RedpacketAccountMapper;
import com.linkwee.activity.model.Activity;
import com.linkwee.activity.model.RedpacketAccount;
import com.linkwee.activity.service.ActivityService;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.util.GenerateNumberUtils;

/**
 * Service实现类
 *
 * @author Mignet
 * @since 2014年6月10日 下午4:16:33
 */
@Service
public class ActivityServiceImpl extends GenericServiceImpl<Activity, Integer> implements ActivityService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ActivityServiceImpl.class);
	
    @Resource
    private ActivityMapper activityMapper;
    
    @Resource
    private RedpacketAccountMapper redpacketAccountMapper;

    @Override
    public GenericDao<Activity, Integer> getDao() {
        return activityMapper;
    }

	@Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" 排序和模糊查询 ");
		Page<Activity> page = new Page<Activity>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<Activity> list = this.activityMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());

		return tableReturn;
	}

	@Override
	public Activity getActivity(String activityId) {
		return activityMapper.getActivity(activityId);
	}

	@Override
	public Activity insertActivity(String name, Date startDate, Date endDate) throws Exception {
		try{
			//红包活动
			Activity activity = new Activity();
			String fid = GenerateNumberUtils.generateKey();
			activity.setFid(fid);
			activity.setName(name);
			activity.setStartDate(startDate);
			activity.setEndDate(endDate);
			activity.setInitDate(startDate);
			activity.setUpdateDate(startDate);
			insert(activity);
			//红包账号
			RedpacketAccount redpacketAccount= new RedpacketAccount();
			redpacketAccount.setFid(GenerateNumberUtils.generateKey());
			redpacketAccount.setActivityId(fid);
			redpacketAccount.setSendMoney(0d);
			redpacketAccount.setRedeemMoney(0d);
			redpacketAccount.setMoney(0d);
			redpacketAccount.setStatus(1);
			redpacketAccount.setInitDate(startDate);
			redpacketAccount.setUpdateDate(startDate);
			redpacketAccountMapper.insertSelective(redpacketAccount);
			return activity;
		}catch(Exception e){
			LOGGER.error("insertActivity exception name={},startDate={},endDate={},exception={}", new Object[]{name,startDate,endDate,e});
			throw e;
		}
	}
	
	public boolean updateActivity(String activityId,String name,Date endDate,Date updateDate)throws Exception{
		try{
			Activity activity = new Activity();
			activity.setFid(activityId);
			activity.setName(name);
			activity.setEndDate(endDate);
			activity.setUpdateDate(updateDate);
			return update(activity)>0;
		}catch(Exception e){
			LOGGER.error("updateActivity exception activityId={},name={},endDate={},updateDate={},exception={}", new Object[]{activityId,name,endDate,updateDate,e});
			throw e;
		}
		
	}

}
