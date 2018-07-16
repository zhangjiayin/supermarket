package com.linkwee.web.service;

import java.util.List;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.CimFeedetail;
 /**
 * 
 * @描述： CimFeedetailService服务接口
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2017年03月31日 14:33:19
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CimFeedetailService extends GenericService<CimFeedetail,Long>{

	/**
	 * 查询CimFeedetail列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);

	/**
	 * 根据投资记录编号查询最初佣金,推荐津贴,直接管理津贴,团队管理津贴,计算明细     用于浮动期产品过了最小日期 每天津贴计算
	 * @param billId
	 * @return
	 */
	List<CimFeedetail> queryEveryDayCalcFeeDetailMapByBillId(String billId);
}
