package com.linkwee.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.web.model.CimInsuranceRecommend;
import com.linkwee.web.model.CimInsuranceRecommendName;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2018年01月11日 13:56:31
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
	 * 根据产品id查询保险推荐种类
	 * @param caseCode
	 * @return
	 */
	List<CimInsuranceRecommendName> getCimInsuranceRecommendNameList(String caseCode);

	/**
	 * 根据产品id删除其对应的推荐关系
	 * @param productId
	 */
	void deleteByProductId(String productId);
}
