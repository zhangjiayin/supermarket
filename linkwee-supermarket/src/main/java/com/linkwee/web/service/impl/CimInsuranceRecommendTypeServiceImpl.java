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

import com.linkwee.web.model.CimInsuranceRecommendType;
import com.linkwee.web.dao.CimInsuranceRecommendTypeMapper;
import com.linkwee.web.service.CimInsuranceRecommendTypeService;
import com.linkwee.web.service.impl.CimInsuranceRecommendTypeServiceImpl;


 /**
 * 
 * @描述：CimInsuranceRecommendTypeService 服务实现类
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2018年01月07日 11:46:05
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("cimInsuranceRecommendTypeService")
public class CimInsuranceRecommendTypeServiceImpl extends GenericServiceImpl<CimInsuranceRecommendType, Long> implements CimInsuranceRecommendTypeService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CimInsuranceRecommendTypeServiceImpl.class);
	
	@Resource
	private CimInsuranceRecommendTypeMapper cimInsuranceRecommendTypeMapper;
	
	@Override
    public GenericDao<CimInsuranceRecommendType, Long> getDao() {
        return cimInsuranceRecommendTypeMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- CimInsuranceRecommendType -- 排序和模糊查询 ");
		Page<CimInsuranceRecommendType> page = new Page<CimInsuranceRecommendType>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<CimInsuranceRecommendType> list = this.cimInsuranceRecommendTypeMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

}
