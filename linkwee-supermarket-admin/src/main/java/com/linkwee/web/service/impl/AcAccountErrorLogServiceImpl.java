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
import com.linkwee.web.dao.AcAccountErrorLogMapper;
import com.linkwee.web.model.acc.AcAccountErrorLog;
import com.linkwee.web.service.AcAccountErrorLogService;


 /**
 * 
 * @描述：AcAccountErrorLogService 服务实现类
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2016年08月30日 18:03:38
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("acAccountErrorLogService")
public class AcAccountErrorLogServiceImpl extends GenericServiceImpl<AcAccountErrorLog, Long> implements AcAccountErrorLogService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AcAccountErrorLogServiceImpl.class);
	
	@Resource
	private AcAccountErrorLogMapper acAccountErrorLogMapper;
	
	@Override
    public GenericDao<AcAccountErrorLog, Long> getDao() {
        return acAccountErrorLogMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- AcAccountErrorLog -- 排序和模糊查询 ");
		Page<AcAccountErrorLog> page = new Page<AcAccountErrorLog>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<AcAccountErrorLog> list = this.acAccountErrorLogMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

}
