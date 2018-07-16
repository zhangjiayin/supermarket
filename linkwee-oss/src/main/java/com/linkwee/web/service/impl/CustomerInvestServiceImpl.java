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
import com.linkwee.web.dao.CustomerInvestMapper;
import com.linkwee.web.model.CustomerInvest;
import com.linkwee.web.service.CustomerInvestService;


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
@Service("customerInvestService")
public class CustomerInvestServiceImpl extends GenericServiceImpl<CustomerInvest, Long> implements CustomerInvestService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerInvestServiceImpl.class);
	
	@Resource
	private CustomerInvestMapper customerInvestMapper;
	
	@Override
    public GenericDao<CustomerInvest, Long> getDao() {
        return customerInvestMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- InvestorUserInfo -- 排序和模糊查询 ");
		Page<CustomerInvest> page = new Page<CustomerInvest>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<CustomerInvest> list = this.customerInvestMapper.selectBySearchInfo(dt,page);
		for (int i = 0; i < list.size(); i++) {
			CustomerInvest customerInvest = list.get(i);
			Double totalUsedHongbao = customerInvestMapper.queryTotalUsedHongbao(customerInvest.getUserId());
			list.get(i).setTotalUsedHongbao(totalUsedHongbao);
		}
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

}
