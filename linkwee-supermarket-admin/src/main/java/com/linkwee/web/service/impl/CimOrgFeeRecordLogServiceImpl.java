package com.linkwee.web.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.dao.CimOrgFeeRecordLogMapper;
import com.linkwee.web.model.CimOrgFeeRecordLog;
import com.linkwee.web.model.cim.CimOrgFeeRecord;
import com.linkwee.web.service.CimOrgFeeRecordLogService;
import com.linkwee.web.service.CimOrgFeeRecordService;


 /**
 * 
 * @描述：CimOrgFeeRecordLogService 服务实现类
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2017年05月03日 18:30:26
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("cimOrgFeeRecordLogService")
public class CimOrgFeeRecordLogServiceImpl extends GenericServiceImpl<CimOrgFeeRecordLog, Long> implements CimOrgFeeRecordLogService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CimOrgFeeRecordLogServiceImpl.class);
	
	@Resource
	private CimOrgFeeRecordLogMapper cimOrgFeeRecordLogMapper;
	
	@Resource
	private CimOrgFeeRecordService cimOrgFeeRecordService; //机构服务
	
	@Override
    public GenericDao<CimOrgFeeRecordLog, Long> getDao() {
        return cimOrgFeeRecordLogMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- CimOrgFeeRecordLog -- 排序和模糊查询 ");
		Page<CimOrgFeeRecordLog> page = new Page<CimOrgFeeRecordLog>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<CimOrgFeeRecordLog> list = this.cimOrgFeeRecordLogMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public void insertLog(String orgNumber, String username, String remark) {
		// TODO Auto-generated method stub
		CimOrgFeeRecord cimOrgFeeRecord = new CimOrgFeeRecord();
		cimOrgFeeRecord.setOrgNumber(orgNumber);
		List<CimOrgFeeRecord> cimOrgFeeRecordList = cimOrgFeeRecordService.selectListByCondition(cimOrgFeeRecord);
		if(CollectionUtils.isNotEmpty(cimOrgFeeRecordList)){
			for (CimOrgFeeRecord cimOrgFeeRecord2 : cimOrgFeeRecordList) {
				CimOrgFeeRecordLog cimOrgFeeRecordLog = new CimOrgFeeRecordLog();
				BeanUtils.copyProperties(cimOrgFeeRecord2, cimOrgFeeRecordLog);
				cimOrgFeeRecordLog.setId(null);
				cimOrgFeeRecordLog.setUpdater(username);
				cimOrgFeeRecordLog.setUpdatetime(new Date());
				cimOrgFeeRecordLog.setRemark(remark);
				insert(cimOrgFeeRecordLog);
			}
		}
	}

}
