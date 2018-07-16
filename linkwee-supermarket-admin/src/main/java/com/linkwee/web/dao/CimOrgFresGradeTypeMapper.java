package com.linkwee.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.web.model.CimOrgFresGradeType;
import com.linkwee.web.model.cim.OrgGradeType;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2018年06月07日 14:14:21
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CimOrgFresGradeTypeMapper extends GenericDao<CimOrgFresGradeType,Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<CimOrgFresGradeType> selectBySearchInfo(@Param("dt")DataTable dt,RowBounds page);

	/**
	 * 根据机构编码查询机构FRES评分体系
	 * @param orgNumber
	 * @return
	 */
	List<OrgGradeType> queryGradeTypeEdit(String orgNumber);
}
