package com.linkwee.web.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.core.util.StringUtils;
import com.linkwee.tc.fee.model.vo.InsuranceFeedetailWrapper;
import com.linkwee.web.dao.CimInsuranceFeeMapper;
import com.linkwee.web.model.CimInsuranceFee;
import com.linkwee.web.service.CimInsuranceFeeService;


 /**
 * 
 * @描述：CimInsuranceFeeService 服务实现类
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2017年12月01日 17:03:46
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("cimInsuranceFeeService")
public class CimInsuranceFeeServiceImpl extends GenericServiceImpl<CimInsuranceFee, Long> implements CimInsuranceFeeService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CimInsuranceFeeServiceImpl.class);
	
	@Resource
	private CimInsuranceFeeMapper cimInsuranceFeeMapper;
	
	@Override
    public GenericDao<CimInsuranceFee, Long> getDao() {
        return cimInsuranceFeeMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- CimInsuranceFee -- 排序和模糊查询 ");
		Page<CimInsuranceFee> page = new Page<CimInsuranceFee>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<CimInsuranceFee> list = this.cimInsuranceFeeMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public void saveFees(InsuranceFeedetailWrapper[] insuranceFeedetailWrappers) {
		Date date = new Date();
		for (InsuranceFeedetailWrapper insuranceFeedetailWrapper : insuranceFeedetailWrappers) {
			String billId= insuranceFeedetailWrapper.getBillId();
			CimInsuranceFee cimInsuranceFee = new CimInsuranceFee();
			BeanUtils.copyProperties(insuranceFeedetailWrapper, cimInsuranceFee);
			cimInsuranceFee.setBillId(billId);
			cimInsuranceFee.setUpdateTime(date);
			if(cimInsuranceFeeMapper.isExitFee(billId,insuranceFeedetailWrapper.getProfitCfplannerId(),insuranceFeedetailWrapper.getFeeType())){
				cimInsuranceFeeMapper.updateFee(cimInsuranceFee);
			}else{
				cimInsuranceFee.setBizId(StringUtils.getUUID());
				cimInsuranceFee.setCreateTime(date);
				insert(cimInsuranceFee);
			}
		}
	}

	@Override
	public List<CimInsuranceFee> selectByBillId(String billId) {
		// TODO Auto-generated method stub
		CimInsuranceFee cimInsuranceFee = new CimInsuranceFee();
		cimInsuranceFee.setBillId(billId);
		return cimInsuranceFeeMapper.selectByCondition(cimInsuranceFee);
	}

}
