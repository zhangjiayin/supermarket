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
import com.linkwee.web.dao.SysErrorLogMapper;
import com.linkwee.web.model.SysErrorLog;
import com.linkwee.web.service.SysErrorLogService;


 /**
 * 
 * @描述：SysErrorLogService 服务实现类
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2018年03月22日 19:16:19
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("sysErrorLogService")
public class SysErrorLogServiceImpl extends GenericServiceImpl<SysErrorLog, Long> implements SysErrorLogService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SysErrorLogServiceImpl.class);
	
	@Resource
	private SysErrorLogMapper sysErrorLogMapper;
	
	@Override
    public GenericDao<SysErrorLog, Long> getDao() {
        return sysErrorLogMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- SysErrorLog -- 排序和模糊查询 ");
		Page<SysErrorLog> page = new Page<SysErrorLog>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<SysErrorLog> list = this.sysErrorLogMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

}
