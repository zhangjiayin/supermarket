package com.linkwee.web.dao;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.web.model.CimOrgInfoA;
import com.linkwee.web.response.CimOrginfoAWeb;
import com.linkwee.web.response.OrgNameNumber;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
*
* @描述： Dao接口
*
* @创建人： Hxb
*
* @创建时间：2018年05月09日 18:01:30
*
* Copyright (c) 深圳领会科技有限公司-版权所有
*/
public interface CimOrgInfoAMapper extends GenericDao<CimOrgInfoA,Long>{

    /**
    * 封装DataTable对象查询
    * @param dt
    * @param page
    * @return
    */
   List<CimOrginfoAWeb> selectBySearchInfo(@Param("dt") DataTable dt, RowBounds page);

    /**
     * 机构信息
     * @param orgNumber
     * @return
     */
    CimOrginfoAWeb findWebOrgInfo(@Param("orgNumber")String orgNumber);

    /**
     * 更新机构信息
     * @param request
     */
    void updateByOrgNumber(CimOrginfoAWeb request);

    /**
     * 新增机构
     * @param request
     */
    void insertOrgInfoA(CimOrginfoAWeb request);

    /**
     * 所有机构的名称和编码
     * @param status
     * @return
     */
    List<OrgNameNumber> queryAllOrgNameNumber(@Param("status")int status);
}
