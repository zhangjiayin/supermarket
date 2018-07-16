package com.linkwee.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.model.ActForgeInvestAmount;
import com.linkwee.web.model.ActForgeMidautumInvestAmount;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2017年09月20日 17:25:13
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface ActForgeMidautumInvestAmountMapper extends GenericDao<ActForgeMidautumInvestAmount,Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<ActForgeMidautumInvestAmount> selectBySearchInfo(@Param("dt")DataTable dt,RowBounds page);
	
	/**
	 * 数据列表
	 * @param actForgeMidautumInvestAmount
	 * @param page
	 * @return
	 */
	List<ActForgeMidautumInvestAmount> findForgeinvestamountList(ActForgeMidautumInvestAmount actForgeMidautumInvestAmount,RowBounds page);
}
