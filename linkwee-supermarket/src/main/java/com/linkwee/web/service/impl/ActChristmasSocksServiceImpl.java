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

import com.linkwee.web.model.ActChristmasSocks;
import com.linkwee.web.dao.ActChristmasSocksMapper;
import com.linkwee.web.service.ActChristmasSocksService;
import com.linkwee.web.service.impl.ActChristmasSocksServiceImpl;


 /**
 * 
 * @描述：ActChristmasSocksService 服务实现类
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2017年11月20日 13:52:59
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("actChristmasSocksService")
public class ActChristmasSocksServiceImpl extends GenericServiceImpl<ActChristmasSocks, Long> implements ActChristmasSocksService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ActChristmasSocksServiceImpl.class);
	
	@Resource
	private ActChristmasSocksMapper actChristmasSocksMapper;
	
	@Override
    public GenericDao<ActChristmasSocks, Long> getDao() {
        return actChristmasSocksMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- ActChristmasSocks -- 排序和模糊查询 ");
		Page<ActChristmasSocks> page = new Page<ActChristmasSocks>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<ActChristmasSocks> list = this.actChristmasSocksMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

}
