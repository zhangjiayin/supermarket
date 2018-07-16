package com.linkwee.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.dao.SmGrowthHandbookClassifyMapper;
import com.linkwee.web.model.SmGrowthHandbookClassify;
import com.linkwee.web.service.SmGrowthHandbookClassifyService;


 /**
 * 
 * @描述：SmGrowthHandbookClassifyService 服务实现类
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2017年07月25日 14:30:29
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("smGrowthHandbookClassifyService")
public class SmGrowthHandbookClassifyServiceImpl extends GenericServiceImpl<SmGrowthHandbookClassify, Long> implements SmGrowthHandbookClassifyService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SmGrowthHandbookClassifyServiceImpl.class);
	
	@Resource
	private SmGrowthHandbookClassifyMapper smGrowthHandbookClassifyMapper;
	
	@Override
    public GenericDao<SmGrowthHandbookClassify, Long> getDao() {
        return smGrowthHandbookClassifyMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- SmGrowthHandbookClassify -- 排序和模糊查询 ");
		Page<SmGrowthHandbookClassify> page = new Page<SmGrowthHandbookClassify>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<SmGrowthHandbookClassify> list = this.smGrowthHandbookClassifyMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public List<SmGrowthHandbookClassify> classify(SmGrowthHandbookClassify condition) {
		return smGrowthHandbookClassifyMapper.classify(condition);
	}

}
