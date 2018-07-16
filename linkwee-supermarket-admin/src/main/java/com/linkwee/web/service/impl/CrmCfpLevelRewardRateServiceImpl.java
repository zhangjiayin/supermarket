package com.linkwee.web.service.impl;

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
import com.linkwee.web.dao.CrmCfpLevelRewardRateMapper;
import com.linkwee.web.model.CrmCfpLevelRewardRate;
import com.linkwee.web.service.CrmCfpLevelRewardRateService;


 /**
 * 
 * @描述：CrmCfpLevelRewardRateService 服务实现类
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2017年03月31日 10:34:09
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("crmCfpLevelRewardRateService")
public class CrmCfpLevelRewardRateServiceImpl extends GenericServiceImpl<CrmCfpLevelRewardRate, Long> implements CrmCfpLevelRewardRateService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CrmCfpLevelRewardRateServiceImpl.class);
	
	@Resource
	private CrmCfpLevelRewardRateMapper crmCfpLevelRewardRateMapper;
	
	@Override
    public GenericDao<CrmCfpLevelRewardRate, Long> getDao() {
        return crmCfpLevelRewardRateMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- CrmCfpLevelRewardRate -- 排序和模糊查询 ");
		Page<CrmCfpLevelRewardRate> page = new Page<CrmCfpLevelRewardRate>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<CrmCfpLevelRewardRate> list = this.crmCfpLevelRewardRateMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public List<CrmCfpLevelRewardRate> getCfpLevelRewardRateList() {
		return crmCfpLevelRewardRateMapper.getCfpLevelRewardRateList();
	}

}
