package com.linkwee.web.service;

import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.model.fee.FeeDetail;
import com.linkwee.web.model.fee.FeeSummaryEntity;

public interface FeeDetailService {
	public DataTableReturn queryFeeDetail(String saleUser,Page<FeeDetail> page);
	
	public DataTableReturn queryFeeSummary(String saleUser,Page<FeeSummaryEntity> page);
}
