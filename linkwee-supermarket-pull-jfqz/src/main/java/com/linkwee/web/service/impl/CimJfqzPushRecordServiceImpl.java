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
import com.linkwee.web.dao.CimJfqzPushRecordMapper;
import com.linkwee.web.model.CimJfqzPushRecord;
import com.linkwee.web.service.CimJfqzPushRecordService;


 /**
 * 
 * @描述：CimJfqzPushRecordService 服务实现类
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2018年03月21日 18:23:57
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("cimJfqzPushRecordService")
public class CimJfqzPushRecordServiceImpl extends GenericServiceImpl<CimJfqzPushRecord, Long> implements CimJfqzPushRecordService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CimJfqzPushRecordServiceImpl.class);
	
	@Resource
	private CimJfqzPushRecordMapper cimJfqzPushRecordMapper;
	
	@Override
    public GenericDao<CimJfqzPushRecord, Long> getDao() {
        return cimJfqzPushRecordMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- CimJfqzPushRecord -- 排序和模糊查询 ");
		Page<CimJfqzPushRecord> page = new Page<CimJfqzPushRecord>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<CimJfqzPushRecord> list = this.cimJfqzPushRecordMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

}
