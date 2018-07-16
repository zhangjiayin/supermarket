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
* @创建时间：2018年06月09日 09:30:07
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
     * 机构团队信息
     * @param orgNumber
     * @return
     */
   List<CimOrgMemberinfoA> queryOrgTeamInfos(String orgNumber);
}
