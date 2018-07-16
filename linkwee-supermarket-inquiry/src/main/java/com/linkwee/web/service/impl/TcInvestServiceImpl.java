package com.linkwee.web.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.dao.TcInvestMapper;
import com.linkwee.web.model.CustomerInvestDetail;
import com.linkwee.web.model.CustomerInvestStatistics;
import com.linkwee.web.request.CustomerInvestRequest;
import com.linkwee.web.service.TcInvestService;

@Service("TcInvestService")
public class TcInvestServiceImpl implements TcInvestService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TcInvestServiceImpl.class);
	
	@Autowired
	private TcInvestMapper investMapper;
	
	@Override
	public DataTableReturn queryCustomerInvestStatistics(CustomerInvestRequest req) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(req.getDraw()+1);
		Page<CustomerInvestStatistics> page = new Page<CustomerInvestStatistics>(req.getStart()/req.getLength()+1,req.getLength());
		List<CustomerInvestStatistics> list = investMapper.queryCustomerInvestStatistics(req.getPlatfrom(),req.getNameOrMobile(), page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public DataTableReturn queryCustomerInvestDetail(CustomerInvestRequest req) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(req.getDraw()+1);
		Page<CustomerInvestDetail> page = new Page<CustomerInvestDetail>(req.getStart()/req.getLength()+1,req.getLength());
		List<CustomerInvestDetail> list = investMapper.queryCustomerInvestDetail(req.getPlatfrom(),req.getUserId(), page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

}
