package com.linkwee.web.service;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.CimOrgInvestStrategyA;

import java.util.List;

/**
*
* @描述： CimOrgInvestStrategyAService服务接口
*
* @创建人： Hxb
*
* @创建时间：2018年05月09日 18:01:30
*
* Copyright (c) 深圳领会科技有限公司-版权所有
*/
public interface CimOrgInvestStrategyAService extends GenericService<CimOrgInvestStrategyA,Long>{

   /**
    * 查询CimOrgInvestStrategyA列表,为data-tables封装
    * @param dataTable
    * @return
    */
   DataTableReturn selectByDatatables(DataTable dataTable);

    /**
     * 批量更新投资攻略
     * @param investStrategys
     */
   void batchUpdateInvestStrategy(List<CimOrgInvestStrategyA> investStrategys);

    /**
     * 批量插入投资攻略
     * @param newInvestStrategys
     */
    void insertBatch(List<CimOrgInvestStrategyA> newInvestStrategys);
}
