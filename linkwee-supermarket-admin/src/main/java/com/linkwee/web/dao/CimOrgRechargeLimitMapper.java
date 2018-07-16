package com.linkwee.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.web.model.CimOrgRechargeLimit;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2017年07月27日 18:31:28
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CimOrgRechargeLimitMapper extends GenericDao<CimOrgRechargeLimit,Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<CimOrgRechargeLimit> selectBySearchInfo(@Param("dt")DataTable dt,RowBounds page);

	/**
	 * 批量插入机构银行充值限额
	 * @param insertList
	 */
	void batchInsert(@Param("orgRechargeLimitList")List<CimOrgRechargeLimit> insertList);

	/**
	 * 删除已经存在的机构银行充值限额
	 * @param orgNumber
	 */
	void deleteOrgRechargeLimitBefore(String orgNumber);
}
