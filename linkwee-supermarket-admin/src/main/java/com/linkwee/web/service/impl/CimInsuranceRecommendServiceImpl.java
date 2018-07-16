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
import com.linkwee.web.model.CimInsuranceRecommend;
import com.linkwee.web.model.CimInsuranceRecommendName;
import com.linkwee.web.dao.CimInsuranceRecommendMapper;
import com.linkwee.web.service.CimInsuranceRecommendService;
import com.linkwee.web.service.impl.CimInsuranceRecommendServiceImpl;


 /**
 * 
 * @描述：CimInsuranceRecommendService 服务实现类
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2018年01月11日 13:56:31
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("cimInsuranceRecommendService")
public class CimInsuranceRecommendServiceImpl extends GenericServiceImpl<CimInsuranceRecommend, Long> implements CimInsuranceRecommendService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CimInsuranceRecommendServiceImpl.class);
	
	@Resource
	private CimInsuranceRecommendMapper cimInsuranceRecommendMapper;
	
	@Override
    public GenericDao<CimInsuranceRecommend, Long> getDao() {
        return cimInsuranceRecommendMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- CimInsuranceRecommend -- 排序和模糊查询 ");
		Page<CimInsuranceRecommend> page = new Page<CimInsuranceRecommend>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<CimInsuranceRecommend> list = this.cimInsuranceRecommendMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public List<CimInsuranceRecommendName> getCimInsuranceRecommendNameList(String productId) {
		// TODO Auto-generated method stub
		return cimInsuranceRecommendMapper.getCimInsuranceRecommendNameList(productId);
	}

	@Override
	public void deleteByProductId(String productId) {
		// TODO Auto-generated method stub
		cimInsuranceRecommendMapper.deleteByProductId(productId);
	}

}
