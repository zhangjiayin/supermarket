package com.linkwee.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.linkwee.api.request.acc.AcSaveBind;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.dao.AcSaveBindMapper;
import com.linkwee.web.service.AcSaveBindService;


 /**
 * 
 * @描述：AcSaveBindService 服务实现类
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2018年06月29日 09:51:57
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("acSaveBindService")
public class AcSaveBindServiceImpl extends GenericServiceImpl<AcSaveBind, Long> implements AcSaveBindService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AcSaveBindServiceImpl.class);
	
	@Resource
	private AcSaveBindMapper acSaveBindMapper;
	
	@Override
    public GenericDao<AcSaveBind, Long> getDao() {
        return acSaveBindMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- AcSaveBind -- 排序和模糊查询 ");
		Page<AcSaveBind> page = new Page<AcSaveBind>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<AcSaveBind> list = this.acSaveBindMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public void saveBankCard(AcSaveBind req) {
		acSaveBindMapper.insertSelective(req);
	}

}
