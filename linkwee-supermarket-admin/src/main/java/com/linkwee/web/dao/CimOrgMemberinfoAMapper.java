package com.linkwee.web.dao;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.web.model.CimOrgMemberinfoA;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
*
* @描述： Dao接口
*
* @创建人： Hxb
*
* @创建时间：2018年06月07日 11:47:15
*
* Copyright (c) 深圳领会科技有限公司-版权所有
*/
public interface CimOrgMemberinfoAMapper extends GenericDao<CimOrgMemberinfoA,Long>{

    /**
    * 封装DataTable对象查询
    * @param dt
    * @param page
    * @return
    */
   List<CimOrgMemberinfoA> selectBySearchInfo(@Param("dt") DataTable dt, RowBounds page);

    /**
     * 批量更新团队成员信息
     * @param teams
     * @return
     */
   int updateBatchTeam(List<CimOrgMemberinfoA> teams);

    /**
     * 批量插入团队成员信息
     * @param newTeams
     * @return
     */
   int insertBatch(List<CimOrgMemberinfoA> newTeams);
}
