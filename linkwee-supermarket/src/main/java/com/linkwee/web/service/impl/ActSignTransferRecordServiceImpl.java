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

import com.linkwee.web.model.ActSignTransferRecord;
import com.linkwee.web.dao.ActSignTransferRecordMapper;
import com.linkwee.web.service.ActSignTransferRecordService;
import com.linkwee.web.service.impl.ActSignTransferRecordServiceImpl;


 /**
 * 
 * @描述：ActSignTransferRecordService 服务实现类
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2017年11月14日 09:32:27
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("actSignTransferRecordService")
public class ActSignTransferRecordServiceImpl extends GenericServiceImpl<ActSignTransferRecord, Long> implements ActSignTransferRecordService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ActSignTransferRecordServiceImpl.class);
	
	@Resource
	private ActSignTransferRecordMapper actSignTransferRecordMapper;
	
	@Override
    public GenericDao<ActSignTransferRecord, Long> getDao() {
        return actSignTransferRecordMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- ActSignTransferRecord -- 排序和模糊查询 ");
		Page<ActSignTransferRecord> page = new Page<ActSignTransferRecord>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<ActSignTransferRecord> list = this.actSignTransferRecordMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

}
