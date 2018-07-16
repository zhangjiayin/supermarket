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

import com.linkwee.web.model.SmGrowthHandbookCfplevelRelation;
import com.linkwee.web.dao.SmGrowthHandbookCfplevelRelationMapper;
import com.linkwee.web.service.SmGrowthHandbookCfplevelRelationService;
import com.linkwee.web.service.impl.SmGrowthHandbookCfplevelRelationServiceImpl;


 /**
 * 
 * @描述：SmGrowthHandbookCfplevelRelationService 服务实现类
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2017年07月26日 09:38:45
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("smGrowthHandbookCfplevelRelationService")
public class SmGrowthHandbookCfplevelRelationServiceImpl extends GenericServiceImpl<SmGrowthHandbookCfplevelRelation, Long> implements SmGrowthHandbookCfplevelRelationService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SmGrowthHandbookCfplevelRelationServiceImpl.class);
	
	@Resource
	private SmGrowthHandbookCfplevelRelationMapper smGrowthHandbookCfplevelRelationMapper;
	
	@Override
    public GenericDao<SmGrowthHandbookCfplevelRelation, Long> getDao() {
        return smGrowthHandbookCfplevelRelationMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- SmGrowthHandbookCfplevelRelation -- 排序和模糊查询 ");
		Page<SmGrowthHandbookCfplevelRelation> page = new Page<SmGrowthHandbookCfplevelRelation>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<SmGrowthHandbookCfplevelRelation> list = this.smGrowthHandbookCfplevelRelationMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

}
