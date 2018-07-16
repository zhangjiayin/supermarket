package com.linkwee.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.model.SmGrowthHandbook;
import com.linkwee.web.model.SmGrowthHandbookClassify;
import com.linkwee.web.request.HandbookRequest;
import com.linkwee.web.response.SmGrowthHandbookResponse;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2017年07月26日 09:37:59
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
	 * 成长手册列表
	 * @param handbookRequest
	 * @param page
	 * @return
	 */
	List<SmGrowthHandbookResponse> findHandbookList(HandbookRequest handbookRequest, Page<SmGrowthHandbookResponse> page);

	/**
	 * 更新成才手册类别名称
	 * @param classify
	 */
	void updateHandbookTypeName(SmGrowthHandbookClassify classify);

	/**
	 * 按类别更新成长手册为失效
	 * @param parseInt
	 */
	void updateHandbookStatusByType(int parseInt);

}
