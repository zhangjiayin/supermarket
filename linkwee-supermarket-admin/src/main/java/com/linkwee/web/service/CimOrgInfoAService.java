package com.linkwee.web.service;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.CimOrgInfoA;
import com.linkwee.web.response.CimOrginfoAWeb;
import com.linkwee.web.response.OrgNameNumber;

import java.util.List;

/**
*
* @描述： CimOrgInfoAService服务接口
*
* @创建人： Hxb
*
* @创建时间：2018年05月09日 18:01:30
*
* Copyright (c) 深圳领会科技有限公司-版权所有
*/
public interface CimOrgInfoAService extends GenericService<CimOrgInfoA,Long>{

   /**
    * 查询CimOrgInfoA列表,为data-tables封装
    * @param dataTable
    * @return
    */
   DataTableReturn selectByDatatables(DataTable dataTable);

   /**
    * 机构信息
    * @param orgNumber
    * @return
    */
   CimOrginfoAWeb findWebOrgInfo(String orgNumber);

   /**
    * 编辑机构信息(有机构和投资攻略对应的Id则更新，否则添加)
    * @param request
    */
   void updateOrgFullInfo(CimOrginfoAWeb request);

    /**
     * 根据orgNumber更新机构信息
     * @param org
     */
   void updateByOrgNumber(CimOrginfoAWeb org);

   /**
    * 查询所有机构名称及对应的编码
    * @param status 机构状态
    * @return
    */
   List<OrgNameNumber> queryAllOrgNameNumber(int status);
}
