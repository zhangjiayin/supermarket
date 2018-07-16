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
import com.linkwee.web.model.DtOrgSynthesizeData;
import com.linkwee.web.dao.DtOrgSynthesizeDataMapper;
import com.linkwee.web.service.DtOrgSynthesizeDataService;
import com.linkwee.web.service.impl.DtOrgSynthesizeDataServiceImpl;


 /**
 * 
 * @描述：DtOrgSynthesizeDataService 服务实现类
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2018年06月06日 16:49:42
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("dtOrgSynthesizeDataService")
public class DtOrgSynthesizeDataServiceImpl extends GenericServiceImpl<DtOrgSynthesizeData, Long> implements DtOrgSynthesizeDataService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DtOrgSynthesizeDataServiceImpl.class);
	
	@Resource
	private DtOrgSynthesizeDataMapper dtOrgSynthesizeDataMapper;
	
	@Override
    public GenericDao<DtOrgSynthesizeData, Long> getDao() {
        return dtOrgSynthesizeDataMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- DtOrgSynthesizeData -- 排序和模糊查询 ");
		Page<DtOrgSynthesizeData> page = new Page<DtOrgSynthesizeData>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<DtOrgSynthesizeData> list = this.dtOrgSynthesizeDataMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public DtOrgSynthesizeData getNewestOrgData(String orgNumber) {
		// TODO Auto-generated method stub
		return dtOrgSynthesizeDataMapper.getNewestOrgData(orgNumber);
	}

}
