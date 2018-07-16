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
import com.linkwee.web.dao.MsgMapper;
import com.linkwee.web.model.Msg;
import com.linkwee.web.service.MsgService;


 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： 陈佳良
 * 
 * @创建时间：2016年06月03日 17:34:00
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("msgService")
public class MsgServiceImpl extends GenericServiceImpl<Msg, Long> implements MsgService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MsgServiceImpl.class);
	
	@Resource
	private MsgMapper msgMapper;
	
	@Override
    public GenericDao<Msg, Long> getDao() {
        return msgMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt,Integer type,Integer appType) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- Msg -- 排序和模糊查询 ");
		Page<Msg> page = new Page<Msg>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<Msg> list = this.msgMapper.selectBySearchInfo(dt,type,appType,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public void addMsg(Msg msg) {
		msgMapper.add(msg);
	}

}
