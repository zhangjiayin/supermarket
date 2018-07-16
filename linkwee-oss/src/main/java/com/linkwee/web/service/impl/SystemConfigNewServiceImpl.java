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

import com.linkwee.web.model.SystemConfigNew;
import com.linkwee.web.dao.SystemConfigNewMapper;
import com.linkwee.web.service.SystemConfigNewService;
import com.linkwee.web.service.impl.SystemConfigNewServiceImpl;


 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年06月03日 10:05:19
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("systemConfigNewService")
public class SystemConfigNewServiceImpl extends GenericServiceImpl<SystemConfigNew, Long> implements SystemConfigNewService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SystemConfigNewServiceImpl.class);
	
	@Resource
	private SystemConfigNewMapper systemConfigNewMapper;
	
	@Override
    public GenericDao<SystemConfigNew, Long> getDao() {
        return systemConfigNewMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- SystemConfigNew -- 排序和模糊查询 ");
		Page<SystemConfigNew> page = new Page<SystemConfigNew>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<SystemConfigNew> list = this.systemConfigNewMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

}
