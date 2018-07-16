package com.linkwee.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.web.model.CrmCfpPromotionCondition;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2017年03月31日 10:34:09
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CrmCfpPromotionConditionMapper extends GenericDao<CrmCfpPromotionCondition,Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<CrmCfpPromotionCondition> selectBySearchInfo(@Param("dt")DataTable dt,RowBounds page);
	/**
	 * 理财师职级考核标准
	 * @author yalin 
	 * @date 2017年4月24日 上午10:33:58  
	 * @return
	 */
	List<CrmCfpPromotionCondition> getCfpPromotionConditionList();
}
