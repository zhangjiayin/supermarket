package com.linkwee.web.service.impl;

import java.util.List;
import java.lang.Long;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.core.orm.paging.Page;

import com.linkwee.web.model.ActivityList;
import com.linkwee.web.dao.ActivityListMapper;
import com.linkwee.web.service.ActivityListService;
import com.linkwee.web.service.impl.ActivityListServiceImpl;


 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年06月28日 16:08:06
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("activityListService")
public class ActivityListServiceImpl extends GenericServiceImpl<ActivityList, Long> implements ActivityListService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ActivityListServiceImpl.class);
	
	@Resource
	private ActivityListMapper activityListMapper;
	
	@Override
    public GenericDao<ActivityList, Long> getDao() {
        return activityListMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt, String actitityName) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- ActivityList -- 排序和模糊查询 ");
		Page<ActivityList> page = new Page<ActivityList>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<ActivityList> list = this.activityListMapper.selectBySearchInfo(actitityName,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

}
