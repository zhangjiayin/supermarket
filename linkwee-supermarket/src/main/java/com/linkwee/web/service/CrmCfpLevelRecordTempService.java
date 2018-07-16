package com.linkwee.web.service;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.CrmCfpLevelRecordTemp;
import com.linkwee.web.model.crm.CrmCfpLevelTemp;
import com.linkwee.web.response.CfpLevelWarningResp;
 /**
 * 
 * @描述： CrmCfpLevelRecordTempService服务接口
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2017年04月10日 13:51:28
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CrmCfpLevelRecordTempService extends GenericService<CrmCfpLevelRecordTemp,Long>{

	/**
	 * 查询CrmCfpLevelRecordTemp列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);

	/**
	 * 根据userId查询
	 * @param userId
	 * @return
	 */
	CrmCfpLevelRecordTemp queryByUserId(String userId);

	/**
	 * 根据userId查询理财师职级提醒
	 * @param userId
	 * @return
	 */
	CfpLevelWarningResp cfpLevelWarning(String userId);

	/**
	 * 查询理财师每天定级
	 * @param crmCfpLevelTemp
	 * @return
	 */
	CrmCfpLevelRecordTemp selectTempCfpLevel(CrmCfpLevelTemp crmCfpLevelTemp);
}
