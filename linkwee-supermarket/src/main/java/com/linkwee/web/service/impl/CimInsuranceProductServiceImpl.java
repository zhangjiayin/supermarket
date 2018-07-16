package com.linkwee.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.linkwee.api.request.insurance.qixin.QixinInsuranceListRequest;
import com.linkwee.api.request.insurance.qixin.QixinInsuranceSiftRequest;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.dao.CimInsuranceProductMapper;
import com.linkwee.web.model.CimInsuranceProduct;
import com.linkwee.web.model.CimInsuranceProductExtends;
import com.linkwee.web.service.CimInsuranceProductService;


 /**
 * 
 * @描述：CimInsuranceProductService 服务实现类
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2017年09月12日 14:37:38
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("cimInsuranceProductService")
public class CimInsuranceProductServiceImpl extends GenericServiceImpl<CimInsuranceProduct, Long> implements CimInsuranceProductService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CimInsuranceProductServiceImpl.class);
	
	@Resource
	private CimInsuranceProductMapper cimInsuranceProductMapper;
	
	@Override
    public GenericDao<CimInsuranceProduct, Long> getDao() {
        return cimInsuranceProductMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- CimInsuranceProduct -- 排序和模糊查询 ");
		Page<CimInsuranceProduct> page = new Page<CimInsuranceProduct>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<CimInsuranceProduct> list = this.cimInsuranceProductMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public List<CimInsuranceProductExtends> insuranceList(QixinInsuranceListRequest qixinInsuranceListRequest,Page<CimInsuranceProductExtends> page) {
		// TODO Auto-generated method stub
		return cimInsuranceProductMapper.insuranceList(qixinInsuranceListRequest,page);
	}

	@Override
	public CimInsuranceProductExtends selectinsuranceSift(QixinInsuranceSiftRequest qixinInsuranceSiftRequest) {
		// TODO Auto-generated method stub
		return cimInsuranceProductMapper.selectinsuranceSift(qixinInsuranceSiftRequest);
	}

	@Override
	public List<CimInsuranceProductExtends> insuranceSelect() {
		// TODO Auto-generated method stub
		return cimInsuranceProductMapper.insuranceSelect();
	}

}
