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
import com.linkwee.web.model.CimOrgRiskManageSynthesize;
import com.linkwee.web.model.cim.OrgRiskManage;
import com.linkwee.web.dao.CimOrgRiskManageSynthesizeMapper;
import com.linkwee.web.service.CimOrgRiskManageSynthesizeService;
import com.linkwee.web.service.impl.CimOrgRiskManageSynthesizeServiceImpl;


 /**
 * 
 * @描述：CimOrgRiskManageSynthesizeService 服务实现类
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2018年06月07日 14:14:21
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("cimOrgRiskManageSynthesizeService")
public class CimOrgRiskManageSynthesizeServiceImpl extends GenericServiceImpl<CimOrgRiskManageSynthesize, Long> implements CimOrgRiskManageSynthesizeService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CimOrgRiskManageSynthesizeServiceImpl.class);
	
	@Resource
	private CimOrgRiskManageSynthesizeMapper cimOrgRiskManageSynthesizeMapper;
	
	@Override
    public GenericDao<CimOrgRiskManageSynthesize, Long> getDao() {
        return cimOrgRiskManageSynthesizeMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- CimOrgRiskManageSynthesize -- 排序和模糊查询 ");
		Page<CimOrgRiskManageSynthesize> page = new Page<CimOrgRiskManageSynthesize>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<CimOrgRiskManageSynthesize> list = this.cimOrgRiskManageSynthesizeMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public OrgRiskManage queryRiskManageEdit(String orgNumber) {
		// TODO Auto-generated method stub
		return cimOrgRiskManageSynthesizeMapper.queryRiskManageEdit(orgNumber);
	}

	@Override
	public void updateByOrgNumber(CimOrgRiskManageSynthesize cimOrgRiskManageSynthesize) {
		// TODO Auto-generated method stub
		cimOrgRiskManageSynthesizeMapper.updateByOrgNumber(cimOrgRiskManageSynthesize);
	}

	@Override
	public CimOrgRiskManageSynthesize selectOneByOrgNumber(String orgNumber) {
		// TODO Auto-generated method stub
		CimOrgRiskManageSynthesize cimOrgRiskManageSynthesize = new CimOrgRiskManageSynthesize();
		cimOrgRiskManageSynthesize.setOrgNumber(orgNumber);
		return cimOrgRiskManageSynthesizeMapper.selectOneByCondition(cimOrgRiskManageSynthesize);
	}

}
