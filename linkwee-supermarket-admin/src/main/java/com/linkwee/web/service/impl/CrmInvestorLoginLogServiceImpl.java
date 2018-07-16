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
import com.linkwee.web.dao.CrmInvestorLoginLogMapper;
import com.linkwee.web.model.CrmInvestorLoginLog;
import com.linkwee.web.service.CrmInvestorLoginLogService;


 /**
 * 
 * @描述：CrmInvestorLoginLogService 服务实现类
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年08月17日 19:11:18
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("crmInvestorLoginLogService")
public class CrmInvestorLoginLogServiceImpl extends GenericServiceImpl<CrmInvestorLoginLog, Long> implements CrmInvestorLoginLogService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CrmInvestorLoginLogServiceImpl.class);
	
	@Resource
	private CrmInvestorLoginLogMapper crmInvestorLoginLogMapper;
	
	@Override
    public GenericDao<CrmInvestorLoginLog, Long> getDao() {
        return crmInvestorLoginLogMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- CrmInvestorLoginLog -- 排序和模糊查询 ");
		Page<CrmInvestorLoginLog> page = new Page<CrmInvestorLoginLog>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<CrmInvestorLoginLog> list = this.crmInvestorLoginLogMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

}
