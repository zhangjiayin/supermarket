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

import com.linkwee.web.model.CimOrgFresGradeType;
import com.linkwee.web.dao.CimOrgFresGradeTypeMapper;
import com.linkwee.web.service.CimOrgFresGradeTypeService;
import com.linkwee.web.service.impl.CimOrgFresGradeTypeServiceImpl;


 /**
 * 
 * @描述：CimOrgFresGradeTypeService 服务实现类
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2018年06月07日 17:06:03
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("cimOrgFresGradeTypeService")
public class CimOrgFresGradeTypeServiceImpl extends GenericServiceImpl<CimOrgFresGradeType, Long> implements CimOrgFresGradeTypeService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CimOrgFresGradeTypeServiceImpl.class);
	
	@Resource
	private CimOrgFresGradeTypeMapper cimOrgFresGradeTypeMapper;
	
	@Override
    public GenericDao<CimOrgFresGradeType, Long> getDao() {
        return cimOrgFresGradeTypeMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- CimOrgFresGradeType -- 排序和模糊查询 ");
		Page<CimOrgFresGradeType> page = new Page<CimOrgFresGradeType>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<CimOrgFresGradeType> list = this.cimOrgFresGradeTypeMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

}
