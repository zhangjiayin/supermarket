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
import com.linkwee.web.dao.SysThirdkeyConfigPullMapper;
import com.linkwee.web.model.SysThirdkeyConfigPull;
import com.linkwee.web.service.SysThirdkeyConfigPullService;


 /**
 * 
 * @描述：SysThirdkeyConfigPullService 服务实现类
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2017年02月09日 17:21:31
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("sysThirdkeyConfigPullService")
public class SysThirdkeyConfigPullServiceImpl extends GenericServiceImpl<SysThirdkeyConfigPull, Long> implements SysThirdkeyConfigPullService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SysThirdkeyConfigPullServiceImpl.class);
	
	@Resource
	private SysThirdkeyConfigPullMapper sysThirdkeyConfigPullMapper;
	
//	private static Map<String, SysThirdkeyConfigPull> orgCache = Maps.newHashMapWithExpectedSize(64);
	
	@Override
    public GenericDao<SysThirdkeyConfigPull, Long> getDao() {
        return sysThirdkeyConfigPullMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- SysThirdkeyConfigPull -- 排序和模糊查询 ");
		Page<SysThirdkeyConfigPull> page = new Page<SysThirdkeyConfigPull>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<SysThirdkeyConfigPull> list = this.sysThirdkeyConfigPullMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public SysThirdkeyConfigPull selectSysThirdkeyConfigPullByOrgNumber(String orgNumber) {
//		SysThirdkeyConfigPull thirdkeyConfig = orgCache.get(orgNumber);
//		if(thirdkeyConfig==null){
//			orgCache.put(orgNumber, thirdkeyConfig);
//		}
		SysThirdkeyConfigPull thirdkeyConfig = new SysThirdkeyConfigPull();
		thirdkeyConfig.setOrgNumber(orgNumber);
		thirdkeyConfig.setOrgStatus("y");
		thirdkeyConfig = sysThirdkeyConfigPullMapper.selectOneByCondition(thirdkeyConfig);
		return thirdkeyConfig;
	}

}
