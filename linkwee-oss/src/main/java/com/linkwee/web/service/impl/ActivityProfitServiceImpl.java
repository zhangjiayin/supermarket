package com.linkwee.web.service.impl;

import java.util.List;
import java.util.regex.Pattern;
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
import com.linkwee.web.dao.ActivityProfitMapper;
import com.linkwee.web.model.ActivityProfit;
import com.linkwee.web.service.ActivityProfitService;


 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年06月24日 14:18:12
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("activityProfitService")
public class ActivityProfitServiceImpl extends GenericServiceImpl<ActivityProfit, Long> implements ActivityProfitService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ActivityProfitServiceImpl.class);
	
	@Resource
	private ActivityProfitMapper activityProfitMapper;
	
	@Override
    public GenericDao<ActivityProfit, Long> getDao() {
        return activityProfitMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- ActivityProfit -- 排序和模糊查询 ");
		Page<ActivityProfit> page = new Page<ActivityProfit>(dt.getStart()/dt.getLength()+1,dt.getLength());
		String mobile = null;
		String value = dt.getSearch().getValue();
		Pattern pattern = Pattern.compile("[0-9]*"); 
		if(value!=null&&value.length()==11&&pattern.matcher(value).matches()){
			mobile = dt.getSearch().getValue();
			dt.setSearch(null);
		};
		List<ActivityProfit> list = this.activityProfitMapper.selectBySearchInfo(dt,mobile,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

}
