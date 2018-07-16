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
import com.linkwee.web.model.DtOrgMoneyInOutMonth;
import com.linkwee.web.model.cim.OrgMoneyDataDetail;
import com.linkwee.web.dao.DtOrgMoneyInOutMonthMapper;
import com.linkwee.web.service.DtOrgMoneyInOutMonthService;
import com.linkwee.web.service.impl.DtOrgMoneyInOutMonthServiceImpl;


 /**
 * 
 * @描述：DtOrgMoneyInOutMonthService 服务实现类
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2018年06月06日 16:49:42
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("dtOrgMoneyInOutMonthService")
public class DtOrgMoneyInOutMonthServiceImpl extends GenericServiceImpl<DtOrgMoneyInOutMonth, Long> implements DtOrgMoneyInOutMonthService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DtOrgMoneyInOutMonthServiceImpl.class);
	
	@Resource
	private DtOrgMoneyInOutMonthMapper dtOrgMoneyInOutMonthMapper;
	
	@Override
    public GenericDao<DtOrgMoneyInOutMonth, Long> getDao() {
        return dtOrgMoneyInOutMonthMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- DtOrgMoneyInOutMonth -- 排序和模糊查询 ");
		Page<DtOrgMoneyInOutMonth> page = new Page<DtOrgMoneyInOutMonth>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<DtOrgMoneyInOutMonth> list = this.dtOrgMoneyInOutMonthMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public List<OrgMoneyDataDetail> queryOrgdata(OrgMoneyDataRequest orgMoneyDataRequest) {
		// TODO Auto-generated method stub
		return dtOrgMoneyInOutMonthMapper.queryOrgdata(orgMoneyDataRequest);
	}

}
