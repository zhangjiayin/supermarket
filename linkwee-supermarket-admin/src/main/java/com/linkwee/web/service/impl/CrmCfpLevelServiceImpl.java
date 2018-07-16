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
import com.linkwee.web.dao.CrmCfpLevelMapper;
import com.linkwee.web.model.CrmCfpLevel;
import com.linkwee.web.service.CrmCfpLevelService;


 /**
 * 
 * @描述：CrmCfpLevelService 服务实现类
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2017年03月31日 10:34:09
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("crmCfpLevelService")
public class CrmCfpLevelServiceImpl extends GenericServiceImpl<CrmCfpLevel, Long> implements CrmCfpLevelService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CrmCfpLevelServiceImpl.class);
	
	@Resource
	private CrmCfpLevelMapper crmCfpLevelMapper;
	
	@Override
    public GenericDao<CrmCfpLevel, Long> getDao() {
        return crmCfpLevelMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- CrmCfpLevel -- 排序和模糊查询 ");
		Page<CrmCfpLevel> page = new Page<CrmCfpLevel>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<CrmCfpLevel> list = this.crmCfpLevelMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}
}
