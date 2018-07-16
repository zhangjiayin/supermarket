package com.linkwee.web.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.api.request.NewsPageListRequest;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.model.SmNews;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年07月27日 19:22:57
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface SmNewsMapper extends GenericDao<SmNews,Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<SmNews> selectBySearchInfo(@Param("dt")DataTable dt,RowBounds page);
	
	/**
	 * 查询资讯翻页
	 * @param newsPageListRequest
	 * @param page
	 * @return
	 */
	List<SmNews> queryNewsPageList(NewsPageListRequest newsPageListRequest, Page<SmNews> page);

	/**
	 * 查询最新的资讯
	 * @param map
	 * @return
	 */
	SmNews queryNewest(Map<String, Object> map);

	/**
	 * 首页热门资讯Top
	 * @param newsPageListRequest
	 * @return
	 */
	List<SmNews> queryTop(NewsPageListRequest newsPageListRequest);

	/**
	 * 增加阅读量
	 * @param model
	 */
	void addReadingAmount(SmNews model);

}
