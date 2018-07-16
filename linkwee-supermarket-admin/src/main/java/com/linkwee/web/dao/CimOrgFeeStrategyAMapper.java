package com.linkwee.web.dao;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.web.model.CimOrgFeeStrategyA;
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
public interface CimOrgFeeStrategyAMapper extends GenericDao<CimOrgFeeStrategyA,Long>{

    /**
    * 封装DataTable对象查询
    * @param dt
    * @param page
    * @return
    */
   List<CimOrgFeeStrategyA> selectBySearchInfo(@Param("dt") DataTable dt, RowBounds page);

    /**
     * 删除机构对应的返现策略
     * @param orgNumber
     * @return
     */
   int deleteByOrgNumber(@Param("orgNumber")String orgNumber);

    /**
     * 插入机构返现策略
     * @param strategyASList
     * @return
     */
   int batchInsert(@Param("strategyASList")List<CimOrgFeeStrategyA> strategyASList);
}
