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
import com.linkwee.web.dao.ActHelpRaiseRateDetailMapper;
import com.linkwee.web.model.activity.ActHelpRaiseRateDetail;
import com.linkwee.web.service.ActHelpRaiseRateDetailService;


 /**
 * 
 * @描述：ActHelpRaiseRateDetailService 服务实现类
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2017年03月02日 10:39:20
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("actHelpRaiseRateDetailService")
public class ActHelpRaiseRateDetailServiceImpl extends GenericServiceImpl<ActHelpRaiseRateDetail, Long> implements ActHelpRaiseRateDetailService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ActHelpRaiseRateDetailServiceImpl.class);
	
	@Resource
	private ActHelpRaiseRateDetailMapper actHelpRaiseRateDetailMapper;
	
	@Override
    public GenericDao<ActHelpRaiseRateDetail, Long> getDao() {
        return actHelpRaiseRateDetailMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- ActHelpRaiseRateDetail -- 排序和模糊查询 ");
		Page<ActHelpRaiseRateDetail> page = new Page<ActHelpRaiseRateDetail>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<ActHelpRaiseRateDetail> list = this.actHelpRaiseRateDetailMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public List<ActHelpRaiseRateDetail> queryHelpDetailList(String userId) {
		return actHelpRaiseRateDetailMapper.queryHelpDetailList(userId);
	}

	@Override
	public List<ActHelpRaiseRateDetail> queryForUpdate(String userId, String openid) {
		return actHelpRaiseRateDetailMapper.queryForUpdate(userId, openid) ;
	}


	
}
