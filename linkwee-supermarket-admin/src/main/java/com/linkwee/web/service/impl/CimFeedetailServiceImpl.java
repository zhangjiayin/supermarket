package com.linkwee.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.dao.CimFeedetailMapper;
import com.linkwee.web.model.CimFeedetail;
import com.linkwee.web.service.CimFeedetailService;


 /**
 * 
 * @描述：CimFeedetailService 服务实现类
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2017年03月31日 14:33:19
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("cimFeedetailService")
public class CimFeedetailServiceImpl extends GenericServiceImpl<CimFeedetail, Long> implements CimFeedetailService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CimFeedetailServiceImpl.class);
	
	@Resource
	private CimFeedetailMapper cimFeedetailMapper;
	
	@Override
    public GenericDao<CimFeedetail, Long> getDao() {
        return cimFeedetailMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- CimFeedetail -- 排序和模糊查询 ");
		Page<CimFeedetail> page = new Page<CimFeedetail>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<CimFeedetail> list = this.cimFeedetailMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}
    
	@Override
	public List<CimFeedetail> queryEveryDayCalcFeeDetailMapByBillId(String billId) {
		List<CimFeedetail> cimFeedetailList = new ArrayList<CimFeedetail>();
		//1001  1002  1005  最多一条
		List<CimFeedetail> cimFeedetailList125 = cimFeedetailMapper.queryEveryDayCalcFeeDetailMapByBillId(billId);
		if(CollectionUtils.isNotEmpty(cimFeedetailList125)){
			cimFeedetailList.addAll(cimFeedetailList125);
		}
		//1006  最多两条
		List<CimFeedetail> cimFeedetailList6 = cimFeedetailMapper.queryEveryDayCalcFeeDetail1006MapByBillId(billId);
		if(CollectionUtils.isNotEmpty(cimFeedetailList6)){
			cimFeedetailList.addAll(cimFeedetailList6);
		}
		return cimFeedetailList;
	}
}
