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

import com.linkwee.web.model.ActWheelWinningRecord;
import com.linkwee.web.dao.ActWheelWinningRecordMapper;
import com.linkwee.web.service.ActWheelWinningRecordService;
import com.linkwee.web.service.impl.ActWheelWinningRecordServiceImpl;


 /**
 * 
 * @描述：ActWheelWinningRecordService 服务实现类
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2018年04月09日 12:48:46
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("actWheelWinningRecordService")
public class ActWheelWinningRecordServiceImpl extends GenericServiceImpl<ActWheelWinningRecord, Long> implements ActWheelWinningRecordService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ActWheelWinningRecordServiceImpl.class);
	
	@Resource
	private ActWheelWinningRecordMapper actWheelWinningRecordMapper;
	
	@Override
    public GenericDao<ActWheelWinningRecord, Long> getDao() {
        return actWheelWinningRecordMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- ActWheelWinningRecord -- 排序和模糊查询 ");
		Page<ActWheelWinningRecord> page = new Page<ActWheelWinningRecord>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<ActWheelWinningRecord> list = this.actWheelWinningRecordMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

}
