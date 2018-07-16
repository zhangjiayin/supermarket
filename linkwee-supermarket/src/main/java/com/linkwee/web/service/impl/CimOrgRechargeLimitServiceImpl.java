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
import com.linkwee.web.dao.CimOrgRechargeLimitMapper;
import com.linkwee.web.model.CimOrgRechargeLimit;
import com.linkwee.web.service.CimOrgRechargeLimitService;


 /**
 * 
 * @描述：CimOrgRechargeLimitService 服务实现类
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2017年07月27日 18:31:28
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("cimOrgRechargeLimitService")
public class CimOrgRechargeLimitServiceImpl extends GenericServiceImpl<CimOrgRechargeLimit, Long> implements CimOrgRechargeLimitService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CimOrgRechargeLimitServiceImpl.class);
	
	@Resource
	private CimOrgRechargeLimitMapper cimOrgRechargeLimitMapper;
	
	@Override
    public GenericDao<CimOrgRechargeLimit, Long> getDao() {
        return cimOrgRechargeLimitMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- CimOrgRechargeLimit -- 排序和模糊查询 ");
		Page<CimOrgRechargeLimit> page = new Page<CimOrgRechargeLimit>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<CimOrgRechargeLimit> list = this.cimOrgRechargeLimitMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

}
