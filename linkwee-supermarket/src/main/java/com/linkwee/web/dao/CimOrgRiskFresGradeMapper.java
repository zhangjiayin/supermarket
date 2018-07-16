package com.linkwee.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.web.model.CimOrgRiskFresGrade;
import com.linkwee.web.model.cim.CimOrgRiskFresGradeExtends;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2018年06月07日 17:06:03
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CimOrgRiskFresGradeMapper extends GenericDao<CimOrgRiskFresGrade,Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<CimOrgRiskFresGrade> selectBySearchInfo(@Param("dt")DataTable dt,RowBounds page);

	/**
	 * 根据机构编码查询机构风控FRES
	 * @param orgNumber
	 * @return
	 */
	List<CimOrgRiskFresGradeExtends> queryRiskFresGradeExtendsList(String orgNumber);
}
