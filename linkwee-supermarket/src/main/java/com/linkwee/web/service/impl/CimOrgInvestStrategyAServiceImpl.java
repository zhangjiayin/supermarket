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
import com.linkwee.web.model.CimOrgInvestStrategyA;
import com.linkwee.web.model.CimOrgInvestStrategyAExtends;
import com.linkwee.web.dao.CimOrgInvestStrategyAMapper;
import com.linkwee.web.service.CimOrgInvestStrategyAService;
import com.linkwee.web.service.impl.CimOrgInvestStrategyAServiceImpl;


 /**
 * 
 * @描述：CimOrgInvestStrategyAService 服务实现类
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2018年05月09日 16:41:24
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("cimOrgInvestStrategyAService")
public class CimOrgInvestStrategyAServiceImpl extends GenericServiceImpl<CimOrgInvestStrategyA, Long> implements CimOrgInvestStrategyAService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CimOrgInvestStrategyAServiceImpl.class);
	
	@Resource
	private CimOrgInvestStrategyAMapper cimOrgInvestStrategyAMapper;
	
	@Override
    public GenericDao<CimOrgInvestStrategyA, Long> getDao() {
        return cimOrgInvestStrategyAMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- CimOrgInvestStrategyA -- 排序和模糊查询 ");
		Page<CimOrgInvestStrategyA> page = new Page<CimOrgInvestStrategyA>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<CimOrgInvestStrategyA> list = this.cimOrgInvestStrategyAMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public List<CimOrgInvestStrategyAExtends> queryCimOrgInvestStrategyAExtendsList(String orgNumber) {
		// TODO Auto-generated method stub
		return cimOrgInvestStrategyAMapper.queryCimOrgInvestStrategyAExtendsList(orgNumber);
	}

}
