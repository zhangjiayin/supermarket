package com.linkwee.web.service;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.CrmOrgAcctRelA;

/**
*
* @描述： CrmOrgAcctRelAService服务接口
*
* @创建人： Hxb
*
* @创建时间：2018年06月11日 15:28:30
*
* Copyright (c) 深圳领会科技有限公司-版权所有
*/
public interface CrmOrgAcctRelAService extends GenericService<CrmOrgAcctRelA,Long>{

   /**
    * 查询CrmOrgAcctRelA列表,为data-tables封装
    * @param dataTable
    * @return
    */
   DataTableReturn selectByDatatables(DataTable dataTable);

    /**
     * 是否绑定机构
     * @param userIdByToken
     * @param platFromNumber
     * @return
     */
   boolean isBindOrgAcct(String userIdByToken, String platFromNumber);

    /**
     * 查询A专区第三方机构账号
     * @param userId
     * @param orgNo
     * @return
     */
   String queryThirdOrgAccountByUserId(String userId, String orgNo);
}
