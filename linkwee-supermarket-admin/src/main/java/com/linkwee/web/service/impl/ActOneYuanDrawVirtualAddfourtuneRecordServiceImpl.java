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
import com.linkwee.web.dao.ActOneYuanDrawVirtualAddfourtuneRecordMapper;
import com.linkwee.web.model.ActOneYuanDrawVirtualAddfourtuneRecord;
import com.linkwee.web.request.PrizeSendRequest;
import com.linkwee.web.service.ActOneYuanDrawVirtualAddfourtuneRecordService;


 /**
 * 
 * @描述：ActOneYuanDrawVirtualAddfourtuneRecordService 服务实现类
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2018年04月09日 14:19:15
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("actOneYuanDrawVirtualAddfourtuneRecordService")
public class ActOneYuanDrawVirtualAddfourtuneRecordServiceImpl extends GenericServiceImpl<ActOneYuanDrawVirtualAddfourtuneRecord, Long> implements ActOneYuanDrawVirtualAddfourtuneRecordService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ActOneYuanDrawVirtualAddfourtuneRecordServiceImpl.class);
	
	@Resource
	private ActOneYuanDrawVirtualAddfourtuneRecordMapper actOneYuanDrawVirtualAddfourtuneRecordMapper;
	
	@Override
    public GenericDao<ActOneYuanDrawVirtualAddfourtuneRecord, Long> getDao() {
        return actOneYuanDrawVirtualAddfourtuneRecordMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- ActOneYuanDrawVirtualAddfourtuneRecord -- 排序和模糊查询 ");
		Page<ActOneYuanDrawVirtualAddfourtuneRecord> page = new Page<ActOneYuanDrawVirtualAddfourtuneRecord>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<ActOneYuanDrawVirtualAddfourtuneRecord> list = this.actOneYuanDrawVirtualAddfourtuneRecordMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public DataTableReturn addFourtuneHistory(PrizeSendRequest prizeSendRequest, DataTable dataTable) {
		Page<ActOneYuanDrawVirtualAddfourtuneRecord> page = new Page<ActOneYuanDrawVirtualAddfourtuneRecord>(dataTable.getStart() / dataTable.getLength() + 1,dataTable.getLength());
		List<ActOneYuanDrawVirtualAddfourtuneRecord> resultList = actOneYuanDrawVirtualAddfourtuneRecordMapper.addFourtuneHistory(prizeSendRequest,page);
		DataTableReturn dataTableReturn =new DataTableReturn();
		dataTableReturn.setRecordsFiltered(page.getTotalCount());
		dataTableReturn.setRecordsTotal(page.getTotalCount());
		dataTableReturn.setData(resultList);
		return dataTableReturn;
	}

}
