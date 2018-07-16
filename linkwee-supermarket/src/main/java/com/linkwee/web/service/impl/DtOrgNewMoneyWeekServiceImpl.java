package com.linkwee.web.service.impl;

import java.util.List;
import java.lang.Long;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.linkwee.api.request.cim.OrgMoneyDataRequest;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.model.DtOrgNewMoneyWeek;
import com.linkwee.web.model.cim.OrgMoneyDataDetail;
import com.linkwee.web.dao.DtOrgNewMoneyWeekMapper;
import com.linkwee.web.service.DtOrgNewMoneyWeekService;
import com.linkwee.web.service.impl.DtOrgNewMoneyWeekServiceImpl;


 /**
 * 
 * @描述：DtOrgNewMoneyWeekService 服务实现类
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2018年06月06日 16:49:42
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("dtOrgNewMoneyWeekService")
public class DtOrgNewMoneyWeekServiceImpl extends GenericServiceImpl<DtOrgNewMoneyWeek, Long> implements DtOrgNewMoneyWeekService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DtOrgNewMoneyWeekServiceImpl.class);
	
	@Resource
	private DtOrgNewMoneyWeekMapper dtOrgNewMoneyWeekMapper;
	
	@Override
    public GenericDao<DtOrgNewMoneyWeek, Long> getDao() {
        return dtOrgNewMoneyWeekMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- DtOrgNewMoneyWeek -- 排序和模糊查询 ");
		Page<DtOrgNewMoneyWeek> page = new Page<DtOrgNewMoneyWeek>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<DtOrgNewMoneyWeek> list = this.dtOrgNewMoneyWeekMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public List<OrgMoneyDataDetail> queryOrgdata(OrgMoneyDataRequest orgMoneyDataRequest) {
		// TODO Auto-generated method stub
		return dtOrgNewMoneyWeekMapper.queryOrgdata(orgMoneyDataRequest);
	}

}
