package com.linkwee.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.model.SmGrowthHandbookClassify;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2017年07月26日 09:37:14
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
	 * 成长手册分类列表
	 * @param request
	 * @param page
	 * @return
	 */
	List<SmGrowthHandbookClassify> findclassifyList(SmGrowthHandbookClassify request,Page<SmGrowthHandbookClassify> page);
}
