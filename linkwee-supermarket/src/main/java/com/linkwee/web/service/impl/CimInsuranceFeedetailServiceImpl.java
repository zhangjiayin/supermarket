package com.linkwee.web.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.core.util.StringUtils;
import com.linkwee.tc.fee.model.vo.InsuranceFeedetailWrapper;
import com.linkwee.web.dao.CimInsuranceFeedetailMapper;
import com.linkwee.web.model.CimInsuranceFeedetail;
import com.linkwee.web.model.CimInsuranceFeedetailExtends;
import com.linkwee.web.service.CimInsuranceFeeService;
import com.linkwee.web.service.CimInsuranceFeedetailService;


 /**
 * 
 * @描述：CimInsuranceFeedetailService 服务实现类
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2017年12月01日 17:03:46
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("cimInsuranceFeedetailService")
public class CimInsuranceFeedetailServiceImpl extends GenericServiceImpl<CimInsuranceFeedetail, Long> implements CimInsuranceFeedetailService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CimInsuranceFeedetailServiceImpl.class);
	
	@Resource
	private CimInsuranceFeedetailMapper cimInsuranceFeedetailMapper;
	
	@Autowired
	private CimInsuranceFeeService cimInsuranceFeeService;
	
	@Override
    public GenericDao<CimInsuranceFeedetail, Long> getDao() {
        return cimInsuranceFeedetailMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- CimInsuranceFeedetail -- 排序和模糊查询 ");
		Page<CimInsuranceFeedetail> page = new Page<CimInsuranceFeedetail>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<CimInsuranceFeedetail> list = this.cimInsuranceFeedetailMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public void insertFeedetail(InsuranceFeedetailWrapper[] insuranceFeedetailWrappers) throws Exception{
		// TODO Auto-generated method stub
		try{
			Date date = new Date();
			List<CimInsuranceFeedetail> cimInsuranceFeedetailList = Lists.newArrayListWithCapacity(insuranceFeedetailWrappers.length);
			CimInsuranceFeedetail cimInsuranceFeedetail = null;
			for (InsuranceFeedetailWrapper insuranceFeedetailWrapper : insuranceFeedetailWrappers) {
				cimInsuranceFeedetail = new CimInsuranceFeedetail();
				cimInsuranceFeedetail.setBizId(StringUtils.getUUID());
                 BeanUtils.copyProperties(insuranceFeedetailWrapper, cimInsuranceFeedetail);
				cimInsuranceFeedetail.setCreateTime(date);
				cimInsuranceFeedetail.setUpdateTime(date);
				
				cimInsuranceFeedetailList.add(cimInsuranceFeedetail);
			}
			if(!cimInsuranceFeedetailList.isEmpty()){
				cimInsuranceFeedetailMapper.inserts(cimInsuranceFeedetailList);
				cimInsuranceFeeService.saveFees(insuranceFeedetailWrappers);
			}
			
		}catch(Exception e){
			LOGGER.error("insertFeedetail Exception feedetailWrappers={},exception={}", JSONObject.toJSONString(insuranceFeedetailWrappers),e);
			throw e;
		}
	}

	@Override
	public List<CimInsuranceFeedetailExtends> queryInitInsuranceFeeDetailByBillId(String billId) {
		// TODO Auto-generated method stub
		List<CimInsuranceFeedetailExtends> cimInsuranceFeedetailList = new ArrayList<CimInsuranceFeedetailExtends>();
		//1001  1002  1005  最多一条
		List<CimInsuranceFeedetailExtends> cimFeedetailList125 = cimInsuranceFeedetailMapper.queryInitInsuranceFeeDetailByBillId1005(billId);
		if(CollectionUtils.isNotEmpty(cimFeedetailList125)){
			cimInsuranceFeedetailList.addAll(cimFeedetailList125);
		}
		//1006  最多两条
		List<CimInsuranceFeedetailExtends> cimFeedetailList6 = cimInsuranceFeedetailMapper.queryInitInsuranceFeeDetailByBillId1006(billId);
		if(CollectionUtils.isNotEmpty(cimFeedetailList6)){
			cimInsuranceFeedetailList.addAll(cimFeedetailList6);
		}
		return cimInsuranceFeedetailList;
	}

	@Override
	public List<CimInsuranceFeedetail> selectCimFeeByBillIdProfit(String billId, String userId) {
		CimInsuranceFeedetail cimInsuranceFeedetail = new CimInsuranceFeedetail();
		cimInsuranceFeedetail.setBillId(billId);
		cimInsuranceFeedetail.setProfitCfplannerId(userId);
		return cimInsuranceFeedetailMapper.selectByCondition(cimInsuranceFeedetail);
	}

}
