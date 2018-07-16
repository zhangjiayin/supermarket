package com.linkwee.web.service;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.CimOrgFeeStrategyA;

import java.util.List;

/**
*
* @描述： CimOrgFeeStrategyAService服务接口
*
* @创建人： Hxb
*
* @创建时间：2018年06月07日 11:47:15
*
* Copyright (c) 深圳领会科技有限公司-版权所有
*/
public interface CimOrgFeeStrategyAService extends GenericService<CimOrgFeeStrategyA,Long>{

   /**
    * 查询CimOrgFeeStrategyA列表,为data-tables封装
    * @param dataTable
    * @return
    */
   DataTableReturn selectByDatatables(DataTable dataTable);

    /**
     * A专区机构返现策略
     * @param orgNumber
     * @return
     */
   List<CimOrgFeeStrategyA> queryOrgFeeStrategy(String orgNumber);

    /**
     * 更新A专区返现策略
     * @param feeRecordList
     */
   void updateBatchFee(List<CimOrgFeeStrategyA> feeRecordList,String orgNumber);
}
