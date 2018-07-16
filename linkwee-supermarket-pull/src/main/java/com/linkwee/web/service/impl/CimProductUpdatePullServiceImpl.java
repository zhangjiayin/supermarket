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
import com.linkwee.web.dao.CimProductUpdatePullMapper;
import com.linkwee.web.model.CimProductUpdatePull;
import com.linkwee.web.service.CimProductUpdatePullService;


 /**
 * 
 * @描述：CimProductUpdatePullService 服务实现类
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2017年02月09日 17:21:31
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("cimProductUpdatePullService")
public class CimProductUpdatePullServiceImpl extends GenericServiceImpl<CimProductUpdatePull, Long> implements CimProductUpdatePullService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CimProductUpdatePullServiceImpl.class);
	
	@Resource
	private CimProductUpdatePullMapper cimProductUpdatePullMapper;
	
	@Override
    public GenericDao<CimProductUpdatePull, Long> getDao() {
        return cimProductUpdatePullMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- CimProductUpdatePull -- 排序和模糊查询 ");
		Page<CimProductUpdatePull> page = new Page<CimProductUpdatePull>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<CimProductUpdatePull> list = this.cimProductUpdatePullMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}
}
