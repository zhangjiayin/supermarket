package com.linkwee.web.service;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.CimOrgMemberinfoA;

import java.util.List;

/**
*
* @描述： CimOrgMemberinfoAService服务接口
*
* @创建人： Hxb
*
* @创建时间：2018年06月07日 11:47:15
*
* Copyright (c) 深圳领会科技有限公司-版权所有
*/
public interface CimOrgMemberinfoAService extends GenericService<CimOrgMemberinfoA,Long>{

   /**
    * 查询CimOrgMemberinfoA列表,为data-tables封装
    * @param dataTable
    * @return
    */
   DataTableReturn selectByDatatables(DataTable dataTable);

    /**
     * 批量更新团队成员信息
     * @param teams
     */
   public void updateBatchTeam(List<CimOrgMemberinfoA> teams);

    /**
     * 团队成员信息批量插入
     * @param newTeams
     */
   public void insertBatch(List<CimOrgMemberinfoA> newTeams);
}
