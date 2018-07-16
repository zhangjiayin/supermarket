package com.linkwee.web.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.model.SmGrowthHandbook;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2017年07月25日 14:29:27
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface SmGrowthHandbookMapper extends GenericDao<SmGrowthHandbook,Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<SmGrowthHandbook> selectBySearchInfo(@Param("dt")DataTable dt,RowBounds page);

	/**
	 * 成长手册分类列表
	 * @param conditions
	 * @param page
	 * @return
	 */
	List<SmGrowthHandbook> queryClassifyPageList(Map<String, Object> conditions, Page<SmGrowthHandbook> page);

	/**
	 * 个人定制列表
	 * @param conditions
	 * @return
	 */
	List<SmGrowthHandbook> personalCustomizationList(Map<String, Object> conditions);
}
