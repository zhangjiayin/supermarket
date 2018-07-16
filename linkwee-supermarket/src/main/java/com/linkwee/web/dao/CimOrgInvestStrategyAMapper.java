package com.linkwee.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.web.model.CimOrgInvestStrategyA;
import com.linkwee.web.model.CimOrgInvestStrategyAExtends;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2018年05月09日 16:41:24
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CimOrgInvestStrategyAMapper extends GenericDao<CimOrgInvestStrategyA,Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<CimOrgInvestStrategyA> selectBySearchInfo(@Param("dt")DataTable dt,RowBounds page);

	/**
	 * 根据机构编码查询机构理财收益率列表
	 * @param orgNumber
	 * @return
	 */
	List<CimOrgInvestStrategyAExtends> queryCimOrgInvestStrategyAExtendsList(String orgNumber);
}
