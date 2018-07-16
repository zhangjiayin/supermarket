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
import com.linkwee.web.dao.CimFundBaseDefinedMapper;
import com.linkwee.web.model.CimFundBaseDefined;
import com.linkwee.web.service.CimFundBaseDefinedService;


 /**
 * 
 * @描述：CimFundBaseDefinedService 服务实现类
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2017年08月16日 10:00:12
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("cimFundBaseDefinedService")
public class CimFundBaseDefinedServiceImpl extends GenericServiceImpl<CimFundBaseDefined, Long> implements CimFundBaseDefinedService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CimFundBaseDefinedServiceImpl.class);
	
	@Resource
	private CimFundBaseDefinedMapper cimFundBaseDefinedMapper;
	
	@Override
    public GenericDao<CimFundBaseDefined, Long> getDao() {
        return cimFundBaseDefinedMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- CimFundBaseDefined -- 排序和模糊查询 ");
		Page<CimFundBaseDefined> page = new Page<CimFundBaseDefined>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<CimFundBaseDefined> list = this.cimFundBaseDefinedMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

}
