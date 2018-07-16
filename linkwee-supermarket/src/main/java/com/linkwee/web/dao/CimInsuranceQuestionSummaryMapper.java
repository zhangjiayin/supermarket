package com.linkwee.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.web.model.cim.CimInsuranceQuestionSummary;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2017年12月29日 15:37:10
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CimInsuranceQuestionSummaryMapper extends GenericDao<CimInsuranceQuestionSummary,Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<CimInsuranceQuestionSummary> selectBySearchInfo(@Param("dt")DataTable dt,RowBounds page);

	 /**
     * 保险评测分数汇总
     */
	Integer sumScore(@Param("familyEnsure")String familyEnsure, @Param("familyLoan")String familyLoan, @Param("yearIncome")String yearIncome);
}
