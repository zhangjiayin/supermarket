package com.linkwee.web.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableEditorOption;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.dao.CimInsuranceProductMapper;
import com.linkwee.web.model.CimInsuranceProduct;
import com.linkwee.web.service.CimInsuranceCateService;
import com.linkwee.web.service.CimInsuranceProductService;
import com.linkwee.web.service.CimInsuranceRecommendService;
import com.linkwee.web.service.CimInsuranceRecommendTypeService;


 /**
 * 
 * @描述：CimInsuranceProductService 服务实现类
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2017年09月21日 13:51:34
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("cimInsuranceProductService")
public class CimInsuranceProductServiceImpl extends GenericServiceImpl<CimInsuranceProduct, Long> implements CimInsuranceProductService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CimInsuranceProductServiceImpl.class);
	
	@Resource
	private CimInsuranceProductMapper cimInsuranceProductMapper;
	@Resource
	private CimInsuranceRecommendService  cimInsuranceRecommendService;
	@Resource
	private CimInsuranceRecommendTypeService  cimInsuranceRecommendTypeService;
	@Resource
	private CimInsuranceCateService cimInsuranceCateService;
	
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
		//页面显示checkbox
		if(CollectionUtils.isNotEmpty(list)){
			for (CimInsuranceProduct cimInsuranceProduct : list) {
				cimInsuranceProduct.setCimInsuranceRecommendNameList(cimInsuranceRecommendService.getCimInsuranceRecommendNameList(cimInsuranceProduct.getProductId()));
			}
		}
		tableReturn.setData(list);
		//编辑页面 checkbox  select 选项	
		Map<String, List<DataTableEditorOption>> dataTableEditorOptionMap =  new HashMap<String, List<DataTableEditorOption>>();
		dataTableEditorOptionMap.put("fristCategory", cimInsuranceCateService.getDataTableEditorOptions());
		dataTableEditorOptionMap.put("cimInsuranceRecommendNameList[].recommendType", cimInsuranceRecommendTypeService.getDataTableEditorOptions());
		tableReturn.setOptions(dataTableEditorOptionMap);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

}
