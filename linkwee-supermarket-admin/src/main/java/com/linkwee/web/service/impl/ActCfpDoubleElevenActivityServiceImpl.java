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

import com.linkwee.web.model.ActCfpDoubleElevenActivity;
import com.linkwee.web.dao.ActCfpDoubleElevenActivityMapper;
import com.linkwee.web.service.ActCfpDoubleElevenActivityService;
import com.linkwee.web.service.impl.ActCfpDoubleElevenActivityServiceImpl;


 /**
 * 
 * @描述：ActCfpDoubleElevenActivityService 服务实现类
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2017年10月23日 11:23:07
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("actCfpDoubleElevenActivityService")
public class ActCfpDoubleElevenActivityServiceImpl extends GenericServiceImpl<ActCfpDoubleElevenActivity, Long> implements ActCfpDoubleElevenActivityService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ActCfpDoubleElevenActivityServiceImpl.class);
	
	@Resource
	private ActCfpDoubleElevenActivityMapper actCfpDoubleElevenActivityMapper;
	
	@Override
    public GenericDao<ActCfpDoubleElevenActivity, Long> getDao() {
        return actCfpDoubleElevenActivityMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- ActCfpDoubleElevenActivity -- 排序和模糊查询 ");
		Page<ActCfpDoubleElevenActivity> page = new Page<ActCfpDoubleElevenActivity>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<ActCfpDoubleElevenActivity> list = this.actCfpDoubleElevenActivityMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

}
