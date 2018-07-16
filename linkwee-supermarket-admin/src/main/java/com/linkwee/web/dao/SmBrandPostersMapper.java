package com.linkwee.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.model.SmBrandPosters;
import com.linkwee.web.response.acc.SmBrandPosterResponse;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2018年01月06日 15:13:33
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface SmBrandPostersMapper extends GenericDao<SmBrandPosters,Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<SmBrandPosters> selectBySearchInfo(@Param("typeValue")String integer,@Param("dt")DataTable dt,RowBounds page);

	List<SmBrandPosters> findBrandPosters(SmBrandPosters pageRequest,  RowBounds bounds);

	List<SmBrandPosters> selectBySearchInfo(@Param("dt")DataTable dt, Page<SmBrandPosters> page);

	void overheadSmBrandPosters(@Param("typeValue")String integer);

	List<SmBrandPosterResponse> selectBrandPosterList();
}
