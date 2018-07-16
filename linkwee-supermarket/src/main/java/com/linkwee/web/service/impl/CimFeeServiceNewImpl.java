package com.linkwee.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.linkwee.api.response.tc.CfpFeeInfoResponse;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.dao.CimFeeMapper;
import com.linkwee.web.model.CimFee;
import com.linkwee.web.service.CimFeeServiceNew;


 /**
 * 
 * @描述：CimFeeService 服务实现类
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2017年10月19日 16:14:18
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("cimFeeServiceNew")
public class CimFeeServiceNewImpl extends GenericServiceImpl<CimFee, Long> implements CimFeeServiceNew{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CimFeeServiceNewImpl.class);
	
	@Resource
	private CimFeeMapper cimFeeMapper;
	
	@Override
    public GenericDao<CimFee, Long> getDao() {
        return cimFeeMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- CimFee -- 排序和模糊查询 ");
		Page<CimFee> page = new Page<CimFee>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<CimFee> list = this.cimFeeMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public List<CimFee> selectCimFeeByBillIdProfit(String billId,String profitCfplannerId) {
		CimFee cimFee = new CimFee();
		cimFee.setBillId(billId);
		cimFee.setProfitCfplannerId(profitCfplannerId);
		return cimFeeMapper.selectByCondition(cimFee);
	}

	@Override
	public List<CfpFeeInfoResponse> virtualFeeList() {
		return cimFeeMapper.virtualFeeList();
	}

}
