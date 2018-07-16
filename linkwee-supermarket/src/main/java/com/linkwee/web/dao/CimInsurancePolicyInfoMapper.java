package com.linkwee.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.web.model.CimInsurancePolicyInfo;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2017年09月14日 16:54:47
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CimInsurancePolicyInfoMapper extends GenericDao<CimInsurancePolicyInfo,Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<CimInsurancePolicyInfo> selectBySearchInfo(@Param("dt")DataTable dt,RowBounds page);

	/**
	 * 批量插入保单信息
	 * @param cimInsurancePolicyInfoList
	 */
	void insertList(List<CimInsurancePolicyInfo> cimInsurancePolicyInfoList);

	/**
	 * 根据投保单号删除对应的保单信息
	 * @param insureNum
	 */
	void deleteByInsureNum(@Param("insureNum")String insureNum);
}
