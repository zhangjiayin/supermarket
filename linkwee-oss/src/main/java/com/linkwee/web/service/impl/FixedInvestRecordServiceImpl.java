package com.linkwee.web.service.impl;

import java.util.List;
import java.lang.Long;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.product.dao.FixedInvestRecordMapper;
import com.linkwee.web.model.FixedInvestRecord;
import com.linkwee.web.request.InvestRecordRequest;
import com.linkwee.web.service.FixedInvestRecordService;
import com.linkwee.web.service.impl.FixedInvestRecordServiceImpl;


 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年06月07日 10:42:59
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("fixedInvestRecordService")
public class FixedInvestRecordServiceImpl extends GenericServiceImpl<FixedInvestRecord, Long> implements FixedInvestRecordService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FixedInvestRecordServiceImpl.class);
	
	@Resource
	private FixedInvestRecordMapper fixedInvestRecordMapper;
	
	@Override
    public GenericDao<FixedInvestRecord, Long> getDao() {
        return fixedInvestRecordMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt,InvestRecordRequest investRecordRequest) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- FixedInvestRecord -- 排序和模糊查询 ");
		Page<FixedInvestRecord> page = new Page<FixedInvestRecord>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<FixedInvestRecord> list = this.fixedInvestRecordMapper.selectBySearchInfo(investRecordRequest, page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

}
