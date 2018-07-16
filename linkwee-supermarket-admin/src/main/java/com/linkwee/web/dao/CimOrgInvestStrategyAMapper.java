package com.linkwee.web.dao;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.web.model.CimOrgInvestStrategyA;
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
public interface CimOrgInvestStrategyAMapper extends GenericDao<CimOrgInvestStrategyA,Long>{

    /**
    * 封装DataTable对象查询
    * @param dt
    * @param page
    * @return
    */
   List<CimOrgInvestStrategyA> selectBySearchInfo(@Param("dt") DataTable dt, RowBounds page);

    /**
     * 批量更新投资攻略
     * @param investStrategys
     */
    void batchUpdateInvestStrategy(List<CimOrgInvestStrategyA> investStrategys);

    /**
     * 批量插入投资攻略
     * @param newInvestStrategys
     */
    void insertBatch(List<CimOrgInvestStrategyA> newInvestStrategys);
}
