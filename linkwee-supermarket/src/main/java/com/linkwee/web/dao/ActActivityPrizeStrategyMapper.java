package com.linkwee.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.web.model.ActActivityPrizeStrategy;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年12月04日 11:29:37
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface ActActivityPrizeStrategyMapper extends GenericDao<ActActivityPrizeStrategy,Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<ActActivityPrizeStrategy> selectBySearchInfo(@Param("dt")DataTable dt,RowBounds page);
}
