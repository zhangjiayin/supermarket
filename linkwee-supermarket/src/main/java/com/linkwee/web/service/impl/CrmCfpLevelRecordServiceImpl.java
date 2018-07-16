package com.linkwee.web.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.core.util.DateUtils;
import com.linkwee.web.dao.CrmCfpLevelRecordMapper;
import com.linkwee.web.enums.CfpJobGradeEnum;
import com.linkwee.web.model.CrmCfpLevelRecord;
import com.linkwee.web.model.CrmCfpLevelRecordTemp;
import com.linkwee.web.model.crm.CrmCfpLevelMonth;
import com.linkwee.web.service.CrmCfpLevelRecordService;
import com.linkwee.web.service.CrmCfpLevelRecordTempService;


 /**
 * 
 * @描述：CrmCfpLevelRecordService 服务实现类
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2017年03月31日 10:34:09
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("crmCfpLevelRecordService")
public class CrmCfpLevelRecordServiceImpl extends GenericServiceImpl<CrmCfpLevelRecord, Long> implements CrmCfpLevelRecordService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CrmCfpLevelRecordServiceImpl.class);
	
	@Resource
	private CrmCfpLevelRecordMapper crmCfpLevelRecordMapper;
	@Resource
	private CrmCfpLevelRecordTempService crmCfpLevelRecordTempService;
	
	@Override
    public GenericDao<CrmCfpLevelRecord, Long> getDao() {
        return crmCfpLevelRecordMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- CrmCfpLevelRecord -- 排序和模糊查询 ");
		Page<CrmCfpLevelRecord> page = new Page<CrmCfpLevelRecord>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<CrmCfpLevelRecord> list = this.crmCfpLevelRecordMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public CrmCfpLevelRecord initCfpLevel(String cfplannerId,Integer month) {
		
		if(month == null){	
			Date now = new Date();
			month = Integer.parseInt(new SimpleDateFormat("yyyyMM").format(DateUtils.getLastDayOfLastMonth(now)));
		}
		
		//初始化每月
		CrmCfpLevelRecord crmCfpLevelRecord = new CrmCfpLevelRecord();
		crmCfpLevelRecord.setUserId(cfplannerId);
		crmCfpLevelRecord.setMonth(month);
		crmCfpLevelRecord.setYearpurAmount(new BigDecimal(0));
		crmCfpLevelRecord.setCurLevel(CfpJobGradeEnum.TA.getValue());//当前职级预归为TA
		crmCfpLevelRecord.setCurLevelWeight(10);//对于已经确定为TA的  直接写写死其权重
		crmCfpLevelRecord.setPreLevel(CfpJobGradeEnum.TA.getValue());
		crmCfpLevelRecord.setTaCount(0);
		crmCfpLevelRecord.setSm1Count(0);
		crmCfpLevelRecord.setSm2Count(0);
		crmCfpLevelRecord.setSm3Count(0);
		crmCfpLevelRecord.setOptType(1);
		crmCfpLevelRecord.setOperator("system");
		crmCfpLevelRecord.setStatus(1);
		crmCfpLevelRecord.setCreateTime(new Date());
		insert(crmCfpLevelRecord);
		return crmCfpLevelRecord;
	}

	@Override
	public CrmCfpLevelRecordTemp initCfpLevelTemp(String cfplannerId,Integer month) {
		if(month == null){			
			Date now = new Date();
			month = Integer.parseInt(new SimpleDateFormat("yyyyMMdd").format(now));
		}
		
		CrmCfpLevelRecordTemp crmCfpLevelRecordTemp = new CrmCfpLevelRecordTemp();
		crmCfpLevelRecordTemp.setUserId(cfplannerId);
		crmCfpLevelRecordTemp.setMonth(month);
		crmCfpLevelRecordTemp.setYearpurAmount(0.0);
		crmCfpLevelRecordTemp.setCurLevel(CfpJobGradeEnum.TA.getValue());//当前职级预归为TA
		crmCfpLevelRecordTemp.setCurLevelWeight(10);
		crmCfpLevelRecordTemp.setPreLevel(CfpJobGradeEnum.TA.getValue());
		crmCfpLevelRecordTemp.setStatus(1);
		crmCfpLevelRecordTemp.setTaCount(0);
		crmCfpLevelRecordTemp.setSm1Count(0);
		crmCfpLevelRecordTemp.setSm2Count(0);
		crmCfpLevelRecordTemp.setSm3Count(0);
		crmCfpLevelRecordTemp.setOptType(1);
		crmCfpLevelRecordTemp.setOperator("system");
		crmCfpLevelRecordTemp.setCreateTime(new Date());
		crmCfpLevelRecordTempService.insert(crmCfpLevelRecordTemp);
		return crmCfpLevelRecordTemp;
	}

	@Override
	public CrmCfpLevelRecord selectCrmCfpLevelRecordByTime(String date) {
		// TODO Auto-generated method stub
		return crmCfpLevelRecordMapper.selectCrmCfpLevelRecordByTime(date);
	}

	@Override
	public CrmCfpLevelRecord selectMonthCfpLevel(CrmCfpLevelMonth crmCfpLevelMonth) {
		// TODO Auto-generated method stub
		return crmCfpLevelRecordMapper.selectMonthCfpLevel(crmCfpLevelMonth);
	}
}
