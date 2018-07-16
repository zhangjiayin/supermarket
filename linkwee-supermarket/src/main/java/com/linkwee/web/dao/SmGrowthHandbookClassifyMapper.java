package com.linkwee.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.web.model.SmGrowthHandbookClassify;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2017年07月25日 14:30:29
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface SmGrowthHandbookClassifyMapper extends GenericDao<SmGrowthHandbookClassify,Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<SmGrowthHandbookClassify> selectBySearchInfo(@Param("dt")DataTable dt,RowBounds page);

	/**
	 * 成长手册分类
	 * @param condition
	 * @return
	 */
	List<SmGrowthHandbookClassify> classify(SmGrowthHandbookClassify condition);
}
