package com.linkwee.web.dao;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.web.model.CrmOrgAcctRelA;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
*
* @描述： Dao接口
*
* @创建人： Hxb
*
* @创建时间：2018年06月11日 15:28:30
*
* Copyright (c) 深圳领会科技有限公司-版权所有
*/
public interface CrmOrgAcctRelAMapper extends GenericDao<CrmOrgAcctRelA,Long>{

    /**
    * 封装DataTable对象查询
    * @param dt
    * @param page
    * @return
    */
   List<CrmOrgAcctRelA> selectBySearchInfo(@Param("dt") DataTable dt, RowBounds page);

    /**
     * 查询是否已绑定
     * @param userId
     * @param platFromNumber
     * @return
     */
   int queryIsBindOrgAcct(@Param("userId")String userId, @Param("platFromNumber")String platFromNumber);

    /**
     * 第三方机构账号
     * @param userId
     * @param orgNo
     * @return
     */
   String queryThirdOrgAccountByUserId(@Param("userId")String userId, @Param("orgNo")String orgNo);

}
