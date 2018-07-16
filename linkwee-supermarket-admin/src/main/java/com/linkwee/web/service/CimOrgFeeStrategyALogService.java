package com.linkwee.web.service;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.CimOrgFeeStrategyA;
import com.linkwee.web.model.CimOrgFeeStrategyALog;

import java.util.List;

/**
*
* @描述： CimOrgFeeStrategyALogService服务接口
*
* @创建人： Hxb
*
* @创建时间：2018年06月08日 18:56:39
*
* Copyright (c) 深圳领会科技有限公司-版权所有
*/
public interface CimOrgFeeStrategyALogService extends GenericService<CimOrgFeeStrategyALog,Long>{

   /**
    * 查询CimOrgFeeStrategyALog列表,为data-tables封装
    * @param dataTable
    * @return
    */
   DataTableReturn selectByDatatables(DataTable dataTable);

   /**
    * 记录上个返现策略
    * @param strategyASList
    * @return
    */
   int batchInsert(List<CimOrgFeeStrategyA> strategyASList);
}
