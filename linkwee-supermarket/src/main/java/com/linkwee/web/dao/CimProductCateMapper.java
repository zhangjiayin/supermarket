package com.linkwee.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.web.model.CimProductCate;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： liqi
 * 
 * @创建时间：2016年07月14日 18:23:34
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CimProductCateMapper extends GenericDao<CimProductCate,Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<CimProductCate> selectBySearchInfo(@Param("dt")DataTable dt,RowBounds page);

	/**
	 * 根据cateId查询系统主推平台
	 * @param cateId
	 * @return
	 */
	List<String> selectRecommendationPlatform(@Param("cateId")String cateId);
}
