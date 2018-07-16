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
import com.linkwee.web.model.CrmInvestorOrgInfoToOur;
import com.linkwee.web.dao.CrmInvestorOrgInfoToOurMapper;
import com.linkwee.web.service.CrmInvestorOrgInfoToOurService;
import com.linkwee.web.service.impl.CrmInvestorOrgInfoToOurServiceImpl;


 /**
 * 
 * @描述：CrmInvestorOrgInfoToOurService 服务实现类
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2018年03月01日 17:22:14
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("crmInvestorOrgInfoToOurService")
public class CrmInvestorOrgInfoToOurServiceImpl extends GenericServiceImpl<CrmInvestorOrgInfoToOur, Long> implements CrmInvestorOrgInfoToOurService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CrmInvestorOrgInfoToOurServiceImpl.class);
	
	@Resource
	private CrmInvestorOrgInfoToOurMapper crmInvestorOrgInfoToOurMapper;
	
	@Override
    public GenericDao<CrmInvestorOrgInfoToOur, Long> getDao() {
        return crmInvestorOrgInfoToOurMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- CrmInvestorOrgInfoToOur -- 排序和模糊查询 ");
		Page<CrmInvestorOrgInfoToOur> page = new Page<CrmInvestorOrgInfoToOur>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<CrmInvestorOrgInfoToOur> list = this.crmInvestorOrgInfoToOurMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public boolean ifExistBindAccount(String orgNumber, String userId) {
		// TODO Auto-generated method stub
		CrmInvestorOrgInfoToOur crmInvestorOrgInfoToOur = new CrmInvestorOrgInfoToOur();
		crmInvestorOrgInfoToOur.setOrgNumber(orgNumber);
		crmInvestorOrgInfoToOur.setUserId(userId);
		crmInvestorOrgInfoToOur = crmInvestorOrgInfoToOurMapper.selectOneByCondition(crmInvestorOrgInfoToOur);
		if(crmInvestorOrgInfoToOur == null){
			return false;
		} else {
			return true;
		}
	}

}
