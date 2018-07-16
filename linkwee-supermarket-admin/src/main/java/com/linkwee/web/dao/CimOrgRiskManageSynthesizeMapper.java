package com.linkwee.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.web.model.CimOrgRiskManageSynthesize;
import com.linkwee.web.model.cim.OrgRiskManage;

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
public interface CimOrgRiskManageSynthesizeMapper extends GenericDao<CimOrgRiskManageSynthesize,Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<CimOrgRiskManageSynthesize> selectBySearchInfo(@Param("dt")DataTable dt,RowBounds page);

	/**
	 * 根据机构编码查询机构风险信息
	 * @param orgNumber
	 * @return
	 */
	OrgRiskManage queryRiskManageEdit(String orgNumber);

	/**
	 * 根据机构编码更新风控信息
	 * @param cimOrgRiskManageSynthesize
	 */
	void updateByOrgNumber(CimOrgRiskManageSynthesize cimOrgRiskManageSynthesize);
}
