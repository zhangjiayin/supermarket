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
import com.linkwee.web.dao.CrmOrgAcctRelMapper;
import com.linkwee.web.model.CrmOrgAcctRel;
import com.linkwee.web.service.CrmOrgAcctRelService;


 /**
 * 
 * @描述：CrmOrgAcctRelService 服务实现类
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2017年08月14日 10:15:42
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("crmOrgAcctRelService")
public class CrmOrgAcctRelServiceImpl extends GenericServiceImpl<CrmOrgAcctRel, Long> implements CrmOrgAcctRelService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CrmOrgAcctRelServiceImpl.class);
	
	@Resource
	private CrmOrgAcctRelMapper crmOrgAcctRelMapper;
	
	@Override
    public GenericDao<CrmOrgAcctRel, Long> getDao() {
        return crmOrgAcctRelMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- CrmOrgAcctRel -- 排序和模糊查询 ");
		Page<CrmOrgAcctRel> page = new Page<CrmOrgAcctRel>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<CrmOrgAcctRel> list = this.crmOrgAcctRelMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public boolean ifOrgAccountExist(String userId, String orgNumber) {
		CrmOrgAcctRel crmOrgAcctRelq = new CrmOrgAcctRel();
		crmOrgAcctRelq.setUserId(userId);
		crmOrgAcctRelq.setOrgNumber(orgNumber);
		crmOrgAcctRelq = selectOne(crmOrgAcctRelq);
		if(crmOrgAcctRelq != null){
			return true;
		} else {
			return false;
		}	
	}

	@Override
	public String getOrgAccount(String userId, String orgNumber) {
		CrmOrgAcctRel crmOrgAcctRelq = new CrmOrgAcctRel();
		crmOrgAcctRelq.setUserId(userId);
		crmOrgAcctRelq.setOrgNumber(orgNumber);
		crmOrgAcctRelq = selectOne(crmOrgAcctRelq);
		if(crmOrgAcctRelq != null){
			return crmOrgAcctRelq.getOrgAccount();
		} else {
			return null;
		}
	}

	@Override
	public boolean hasRegFund(String userId) {
		return crmOrgAcctRelMapper.hasRegFund(userId);
	}
}
