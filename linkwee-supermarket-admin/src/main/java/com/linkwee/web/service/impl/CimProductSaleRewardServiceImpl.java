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
import com.linkwee.web.dao.CimProductSaleRewardMapper;
import com.linkwee.web.model.CimProductSaleReward;
import com.linkwee.web.service.CimProductSaleRewardService;


 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： liqi
 * 
 * @创建时间：2016年07月14日 18:23:34
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("cimProductSaleRewardService")
public class CimProductSaleRewardServiceImpl extends GenericServiceImpl<CimProductSaleReward, Long> implements CimProductSaleRewardService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CimProductSaleRewardServiceImpl.class);
	
	@Resource
	private CimProductSaleRewardMapper cimProductSaleRewardMapper;
	
	@Override
    public GenericDao<CimProductSaleReward, Long> getDao() {
        return cimProductSaleRewardMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- CimProductSaleReward -- 排序和模糊查询 ");
		Page<CimProductSaleReward> page = new Page<CimProductSaleReward>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<CimProductSaleReward> list = this.cimProductSaleRewardMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

}
