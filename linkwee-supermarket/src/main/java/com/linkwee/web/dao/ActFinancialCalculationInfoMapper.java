package com.linkwee.web.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.web.model.ActFinancialCalculationInfo;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2017年12月25日 10:17:03
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface ActFinancialCalculationInfoMapper extends GenericDao<ActFinancialCalculationInfo,Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<ActFinancialCalculationInfo> selectBySearchInfo(@Param("dt")DataTable dt,RowBounds page);
}
