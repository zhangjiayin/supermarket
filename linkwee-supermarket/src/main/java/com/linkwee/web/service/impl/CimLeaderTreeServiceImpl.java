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
import com.linkwee.web.dao.CimLeaderTreeMapper;
import com.linkwee.web.model.crm.CimLeaderTree;
import com.linkwee.web.service.CimLeaderTreeService;


 /**
 * 
 * @描述：CimLeaderTreeService 服务实现类
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2017年03月06日 16:38:51
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("cimLeaderTreeService")
public class CimLeaderTreeServiceImpl extends GenericServiceImpl<CimLeaderTree, Long> implements CimLeaderTreeService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CimLeaderTreeServiceImpl.class);
	
	@Resource
	private CimLeaderTreeMapper cimLeaderTreeMapper;
	
	@Override
    public GenericDao<CimLeaderTree, Long> getDao() {
        return cimLeaderTreeMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- CimLeaderTree -- 排序和模糊查询 ");
		Page<CimLeaderTree> page = new Page<CimLeaderTree>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<CimLeaderTree> list = this.cimLeaderTreeMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

}
