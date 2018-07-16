package com.linkwee.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.web.model.CimInsuranceProductExtends;
import com.linkwee.web.model.CimInsuranceRecommend;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2018年01月07日 11:46:05
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CimInsuranceRecommendMapper extends GenericDao<CimInsuranceRecommend,Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<CimInsuranceRecommend> selectBySearchInfo(@Param("dt")DataTable dt,RowBounds page);

	/**
	 * 根据保险种类和查询类型查询保险产品
	 * @param category
	 * @param queryType
	 * @return
	 */
	List<CimInsuranceProductExtends> getInsuranceProductByType(@Param("category")String category,@Param("queryType")String queryType);
}
