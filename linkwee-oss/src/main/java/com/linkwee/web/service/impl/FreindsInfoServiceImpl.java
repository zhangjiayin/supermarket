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

import com.linkwee.web.model.FreindsInfo;
import com.linkwee.web.dao.FreindsInfoMapper;
import com.linkwee.web.service.FreindsInfoService;
import com.linkwee.web.service.impl.FreindsInfoServiceImpl;


 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年06月03日 15:53:27
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("freindsInfoService")
public class FreindsInfoServiceImpl extends GenericServiceImpl<FreindsInfo, Long> implements FreindsInfoService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FreindsInfoServiceImpl.class);
	
	@Resource
	private FreindsInfoMapper investorUserInfoMapper;
	
	@Override
    public GenericDao<FreindsInfo, Long> getDao() {
        return investorUserInfoMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt,String userId) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- InvestorUserInfo -- 排序和模糊查询 ");
		Page<FreindsInfo> page = new Page<FreindsInfo>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<FreindsInfo> list = this.investorUserInfoMapper.selectBySearchInfo(dt,userId,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

}
