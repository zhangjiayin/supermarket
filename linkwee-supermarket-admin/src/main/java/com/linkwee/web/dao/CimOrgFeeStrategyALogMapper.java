package com.linkwee.web.dao;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.web.model.CimOrgFeeStrategyA;
import com.linkwee.web.model.CimOrgFeeStrategyALog;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
*
* @描述： Dao接口
*
* @创建人： Hxb
*
* @创建时间：2018年06月08日 18:56:39
*
* Copyright (c) 深圳领会科技有限公司-版权所有
*/
public interface CimOrgFeeStrategyALogMapper extends GenericDao<CimOrgFeeStrategyALog,Long>{

    /**
    * 封装DataTable对象查询
    * @param dt
    * @param page
    * @return
    */
   List<CimOrgFeeStrategyALog> selectBySearchInfo(@Param("dt") DataTable dt, RowBounds page);

    /**
     * 插入上次的机构返现策略
     * @param strategyASList
     * @return
     */
    int batchInsert(@Param("strategyASList")List<CimOrgFeeStrategyA> strategyASList);
}
