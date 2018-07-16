package com.linkwee.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.model.ActForgeInvestAmount;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2017年08月25日 10:10:09
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface ActForgeInvestAmountMapper extends GenericDao<ActForgeInvestAmount,Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<ActForgeInvestAmount> selectBySearchInfo(@Param("dt")DataTable dt,RowBounds page);

	/**
	 * 数据列表
	 * @param actForgeInvestAmount
	 * @param page
	 * @return
	 */
	List<ActForgeInvestAmount> findForgeinvestamountList(ActForgeInvestAmount actForgeInvestAmount,Page<ActForgeInvestAmount> page);
}
