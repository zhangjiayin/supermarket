package com.linkwee.web.service;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.CrmCfpLevelRewardRate;
 /**
 * 
 * @描述： CrmCfpLevelRewardRateService服务接口
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2017年03月31日 10:34:09
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CrmCfpLevelRewardRateService extends GenericService<CrmCfpLevelRewardRate,Long>{

	/**
	 * 查询CrmCfpLevelRewardRate列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);
	
	/**
	 * 根据职级代码查询理财师职级奖励比率
	 * @param levelCode
	 * @return
	 */
	CrmCfpLevelRewardRate selectOneByLevelCode(String levelCode);
}
