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
import com.linkwee.web.model.CimOrgInfoToOur;
import com.linkwee.web.dao.CimOrgInfoToOurMapper;
import com.linkwee.web.service.CimOrgInfoToOurService;
import com.linkwee.web.service.impl.CimOrgInfoToOurServiceImpl;


 /**
 * 
 * @描述：CimOrgInfoToOurService 服务实现类
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2018年02月28日 14:21:13
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("cimOrgInfoToOurService")
public class CimOrgInfoToOurServiceImpl extends GenericServiceImpl<CimOrgInfoToOur, Long> implements CimOrgInfoToOurService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CimOrgInfoToOurServiceImpl.class);
	
	@Resource
	private CimOrgInfoToOurMapper cimOrgInfoToOurMapper;
	
	@Override
    public GenericDao<CimOrgInfoToOur, Long> getDao() {
        return cimOrgInfoToOurMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- CimOrgInfoToOur -- 排序和模糊查询 ");
		Page<CimOrgInfoToOur> page = new Page<CimOrgInfoToOur>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<CimOrgInfoToOur> list = this.cimOrgInfoToOurMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public CimOrgInfoToOur selectOneByOrgNumber(String orgNumber) {
		// TODO Auto-generated method stub
		CimOrgInfoToOur cimOrgInfoToOur = new CimOrgInfoToOur();
		cimOrgInfoToOur.setOrgNumber(orgNumber);
		return cimOrgInfoToOurMapper.selectOneByCondition(cimOrgInfoToOur);
	}

}
