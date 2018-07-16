package com.linkwee.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.web.model.crm.CimLeaderTree;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2017年03月06日 16:38:51
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CimLeaderTreeMapper extends GenericDao<CimLeaderTree,Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<CimLeaderTree> selectBySearchInfo(@Param("dt")DataTable dt,RowBounds page);
	
	
	/**
	 * 砍掉树的分枝
	 */
	void updateLeaderTreeIsOnelyPay(CimLeaderTree cimLeaderTree);
	/**
	 * 递归将下级计算isCalc置为0(不计入此树的统计)
	 */
	void updateLeaderTreeIsCalc(CimLeaderTree cimLeaderTree);
}
