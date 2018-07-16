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
import com.linkwee.web.dao.ActActivityPrizeStrategyMapper;
import com.linkwee.web.model.ActActivityPrizeStrategy;
import com.linkwee.web.service.ActActivityPrizeStrategyService;


 /**
 * 
 * @描述：ActActivityPrizeStrategyService 服务实现类
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年12月04日 11:29:37
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("actActivityPrizeStrategyService")
public class ActActivityPrizeStrategyServiceImpl extends GenericServiceImpl<ActActivityPrizeStrategy, Long> implements ActActivityPrizeStrategyService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ActActivityPrizeStrategyServiceImpl.class);
	
	@Resource
	private ActActivityPrizeStrategyMapper actActivityPrizeStrategyMapper;
	
	@Override
    public GenericDao<ActActivityPrizeStrategy, Long> getDao() {
        return actActivityPrizeStrategyMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- ActActivityPrizeStrategy -- 排序和模糊查询 ");
		Page<ActActivityPrizeStrategy> page = new Page<ActActivityPrizeStrategy>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<ActActivityPrizeStrategy> list = this.actActivityPrizeStrategyMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

}
