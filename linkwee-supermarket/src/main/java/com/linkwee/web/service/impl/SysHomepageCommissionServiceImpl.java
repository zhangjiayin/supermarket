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
import com.linkwee.web.dao.SysHomepageCommissionMapper;
import com.linkwee.web.model.SysHomepageCommission;
import com.linkwee.web.service.SysHomepageCommissionService;


 /**
 * 
 * @描述：SysHomepageCommissionService 服务实现类
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2017年06月21日 10:34:25
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("sysHomepageCommissionService")
public class SysHomepageCommissionServiceImpl extends GenericServiceImpl<SysHomepageCommission, Long> implements SysHomepageCommissionService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SysHomepageCommissionServiceImpl.class);
	
	@Resource
	private SysHomepageCommissionMapper sysHomepageCommissionMapper;
	
	@Override
    public GenericDao<SysHomepageCommission, Long> getDao() {
        return sysHomepageCommissionMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- SysHomepageCommission -- 排序和模糊查询 ");
		Page<SysHomepageCommission> page = new Page<SysHomepageCommission>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<SysHomepageCommission> list = this.sysHomepageCommissionMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public SysHomepageCommission selectNewest() {
		return sysHomepageCommissionMapper.selectNewest();
	}

	@Override
	public SysHomepageCommission select2017Newest() {
		return sysHomepageCommissionMapper.select2017Newest();
	}

}
