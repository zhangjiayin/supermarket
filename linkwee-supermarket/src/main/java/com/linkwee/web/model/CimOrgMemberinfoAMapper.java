package com.linkwee.web.model;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
*
* @描述： Dao接口
*
* @创建人： Hxb
*
* @创建时间：2018年06月09日 09:30:07
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
}
