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

import com.linkwee.web.model.ActLaborDayChangeFaceRecord;
import com.linkwee.web.dao.ActLaborDayChangeFaceRecordMapper;
import com.linkwee.web.service.ActLaborDayChangeFaceRecordService;
import com.linkwee.web.service.impl.ActLaborDayChangeFaceRecordServiceImpl;


 /**
 * 
 * @描述：ActLaborDayChangeFaceRecordService 服务实现类
 * 
 * @创建人： Hxb
 * 
 * @创建时间：2018年04月24日 16:51:03
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("actLaborDayChangeFaceRecordService")
public class ActLaborDayChangeFaceRecordServiceImpl extends GenericServiceImpl<ActLaborDayChangeFaceRecord, Long> implements ActLaborDayChangeFaceRecordService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ActLaborDayChangeFaceRecordServiceImpl.class);
	
	@Resource
	private ActLaborDayChangeFaceRecordMapper actLaborDayChangeFaceRecordMapper;
	
	@Override
    public GenericDao<ActLaborDayChangeFaceRecord, Long> getDao() {
        return actLaborDayChangeFaceRecordMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- ActLaborDayChangeFaceRecord -- 排序和模糊查询 ");
		Page<ActLaborDayChangeFaceRecord> page = new Page<ActLaborDayChangeFaceRecord>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<ActLaborDayChangeFaceRecord> list = this.actLaborDayChangeFaceRecordMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

}
