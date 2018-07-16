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

import com.linkwee.web.model.CimOrginfo;
import com.linkwee.web.dao.CimOrginfoMapper;
import com.linkwee.web.service.CimOrginfoService;
import com.linkwee.web.service.impl.CimOrginfoServiceImpl;


 /**
 * 
 * @描述：CimOrginfoService 服务实现类
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年07月20日 14:45:19
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("cimOrginfoService")
public class CimOrginfoServiceImpl extends GenericServiceImpl<CimOrginfo, Long> implements CimOrginfoService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CimOrginfoServiceImpl.class);
	
	@Resource
	private CimOrginfoMapper cimOrginfoMapper;
	
	@Override
    public GenericDao<CimOrginfo, Long> getDao() {
        return cimOrginfoMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- CimOrginfo -- 排序和模糊查询 ");
		Page<CimOrginfo> page = new Page<CimOrginfo>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<CimOrginfo> list = this.cimOrginfoMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

}
