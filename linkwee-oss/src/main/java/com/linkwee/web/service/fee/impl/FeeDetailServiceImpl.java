package com.linkwee.web.service.fee.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.dao.FeeDetailDao;
import com.linkwee.web.dao.FeeSummaryDao;
import com.linkwee.web.model.fee.FeeDetail;
import com.linkwee.web.model.fee.FeeSummaryEntity;
import com.linkwee.web.service.FeeDetailService;

@Service("feeDetailService")
public class FeeDetailServiceImpl implements FeeDetailService {

	@Autowired
	private FeeDetailDao feeDetailDao;
	
	@Autowired
	private FeeSummaryDao feeSummaryDao;

	@Override
	public DataTableReturn queryFeeDetail(String saleUser, Page<FeeDetail> page) {
		FeeDetail feeDetailEntity = new FeeDetail();
		if (!StringUtils.isEmpty(saleUser)) {
			if (saleUser.length() == 8) //编码
				feeDetailEntity.setSaleusernumber(saleUser);
			else //手机号
				feeDetailEntity.setSaleusermobile(saleUser);
		}
		List<FeeDetail> list = feeDetailDao.queryFeeDetail(feeDetailEntity,
				page);
		DataTableReturn dataTableReturn = new DataTableReturn();
		dataTableReturn.setData(list);
		dataTableReturn.setDraw(0);
		dataTableReturn.setRecordsTotal(page.getTotalCount());
		dataTableReturn.setRecordsFiltered(page.getTotalCount());
		return dataTableReturn;
	}

	@Override
	public DataTableReturn queryFeeSummary(String saleUser, Page<FeeSummaryEntity> page) {
		FeeSummaryEntity feeSummaryEntity = new FeeSummaryEntity();
		if (!StringUtils.isEmpty(saleUser)) {
			if (saleUser.length() == 8) //编码
				feeSummaryEntity.setSaleusernumber(saleUser);
			else //手机号
				feeSummaryEntity.setSaleusermobile(saleUser);
		}
		feeSummaryEntity.setSaleusermobile(saleUser);
		List<FeeSummaryEntity> list = feeSummaryDao.queryFeeDetail(feeSummaryEntity,
				page);
		DataTableReturn dataTableReturn = new DataTableReturn();
		dataTableReturn.setData(list);
		dataTableReturn.setDraw(0);
		dataTableReturn.setRecordsTotal(page.getTotalCount());
		dataTableReturn.setRecordsFiltered(page.getTotalCount());
		return dataTableReturn;
	}
}
