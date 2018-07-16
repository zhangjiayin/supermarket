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
import com.linkwee.web.dao.CimFundInfoMapper;
import com.linkwee.web.model.CimFundInfo;
import com.linkwee.web.service.CimFundInfoService;


 /**
 * 
 * @描述：CimFundInfoService 服务实现类
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2017年08月14日 10:15:42
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("cimFundInfoService")
public class CimFundInfoServiceImpl extends GenericServiceImpl<CimFundInfo, Long> implements CimFundInfoService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CimFundInfoServiceImpl.class);
	
	@Resource
	private CimFundInfoMapper cimFundInfoMapper;
	
	@Override
    public GenericDao<CimFundInfo, Long> getDao() {
        return cimFundInfoMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- CimFundInfo -- 排序和模糊查询 ");
		Page<CimFundInfo> page = new Page<CimFundInfo>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<CimFundInfo> list = this.cimFundInfoMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public String getSelfSecretByOrgNumber(String orgNumber) {
		CimFundInfo cimFundInfo = new CimFundInfo();
		cimFundInfo.setOrgNumber(orgNumber);
		cimFundInfo = selectOne(cimFundInfo);
		if(cimFundInfo != null){
			return cimFundInfo.getPlatformSelfSecret();
		}
		return null;
	}

	@Override
	public CimFundInfo selectOneByOrgNumber(String orgNumber) {
		CimFundInfo cimFundInfo = new CimFundInfo();
		cimFundInfo.setOrgNumber(orgNumber);
		cimFundInfo = selectOne(cimFundInfo);
		return cimFundInfo;
	}
}
