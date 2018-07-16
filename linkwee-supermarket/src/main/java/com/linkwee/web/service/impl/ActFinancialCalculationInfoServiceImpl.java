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
import com.linkwee.web.model.ActFinancialCalculationInfo;
import com.linkwee.web.dao.ActFinancialCalculationInfoMapper;
import com.linkwee.web.service.ActFinancialCalculationInfoService;
import com.linkwee.web.service.impl.ActFinancialCalculationInfoServiceImpl;


 /**
 * 
 * @描述：ActFinancialCalculationInfoService 服务实现类
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2017年12月25日 10:17:03
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("actFinancialCalculationInfoService")
public class ActFinancialCalculationInfoServiceImpl extends GenericServiceImpl<ActFinancialCalculationInfo, Long> implements ActFinancialCalculationInfoService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ActFinancialCalculationInfoServiceImpl.class);
	
	@Resource
	private ActFinancialCalculationInfoMapper actFinancialCalculationInfoMapper;
	
	@Override
    public GenericDao<ActFinancialCalculationInfo, Long> getDao() {
        return actFinancialCalculationInfoMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- ActFinancialCalculationInfo -- 排序和模糊查询 ");
		Page<ActFinancialCalculationInfo> page = new Page<ActFinancialCalculationInfo>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<ActFinancialCalculationInfo> list = this.actFinancialCalculationInfoMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}
    
}
