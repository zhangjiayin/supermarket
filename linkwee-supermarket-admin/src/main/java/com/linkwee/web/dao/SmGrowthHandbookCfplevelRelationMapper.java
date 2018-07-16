package com.linkwee.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.web.model.SmGrowthHandbookCfplevelRelation;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2017年07月26日 09:38:45
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface SmGrowthHandbookCfplevelRelationMapper extends GenericDao<SmGrowthHandbookCfplevelRelation,Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<SmGrowthHandbookCfplevelRelation> selectBySearchInfo(@Param("dt")DataTable dt,RowBounds page);

	/**
	 * 更加成才手册的ID删除对应的关联关系
	 * @param id
	 */
	void deleteByGrowthHandbookId(Integer id);
}
