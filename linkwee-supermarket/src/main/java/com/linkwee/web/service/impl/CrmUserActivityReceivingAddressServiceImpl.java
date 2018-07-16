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

import com.linkwee.web.model.CrmUserActivityReceivingAddress;
import com.linkwee.web.dao.CrmUserActivityReceivingAddressMapper;
import com.linkwee.web.service.CrmUserActivityReceivingAddressService;
import com.linkwee.web.service.impl.CrmUserActivityReceivingAddressServiceImpl;


 /**
 * 
 * @描述：CrmUserActivityReceivingAddressService 服务实现类
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2018年04月08日 10:20:14
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("crmUserActivityReceivingAddressService")
public class CrmUserActivityReceivingAddressServiceImpl extends GenericServiceImpl<CrmUserActivityReceivingAddress, Long> implements CrmUserActivityReceivingAddressService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CrmUserActivityReceivingAddressServiceImpl.class);
	
	@Resource
	private CrmUserActivityReceivingAddressMapper crmUserActivityReceivingAddressMapper;
	
	@Override
    public GenericDao<CrmUserActivityReceivingAddress, Long> getDao() {
        return crmUserActivityReceivingAddressMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- CrmUserActivityReceivingAddress -- 排序和模糊查询 ");
		Page<CrmUserActivityReceivingAddress> page = new Page<CrmUserActivityReceivingAddress>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<CrmUserActivityReceivingAddress> list = this.crmUserActivityReceivingAddressMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

}
