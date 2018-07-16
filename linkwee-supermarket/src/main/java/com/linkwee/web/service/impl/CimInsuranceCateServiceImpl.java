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
import com.linkwee.web.CimInsuranceCategory;
import com.linkwee.web.model.CimInsuranceCate;
import com.linkwee.web.dao.CimInsuranceCateMapper;
import com.linkwee.web.service.CimInsuranceCateService;
import com.linkwee.web.service.impl.CimInsuranceCateServiceImpl;


 /**
 * 
 * @描述：CimInsuranceCateService 服务实现类
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2018年01月18日 15:49:59
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("cimInsuranceCateService")
public class CimInsuranceCateServiceImpl extends GenericServiceImpl<CimInsuranceCate, Long> implements CimInsuranceCateService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CimInsuranceCateServiceImpl.class);
	
	@Resource
	private CimInsuranceCateMapper cimInsuranceCateMapper;
	
	@Override
    public GenericDao<CimInsuranceCate, Long> getDao() {
        return cimInsuranceCateMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- CimInsuranceCate -- 排序和模糊查询 ");
		Page<CimInsuranceCate> page = new Page<CimInsuranceCate>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<CimInsuranceCate> list = this.cimInsuranceCateMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public List<CimInsuranceCategory> getCategoryList() {
		// TODO Auto-generated method stub
		return cimInsuranceCateMapper.getCategoryList();
	}

}
