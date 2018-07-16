package com.linkwee.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.api.request.cim.OrginfoaDetailRequest;
import com.linkwee.api.request.cim.OrginfoaListRequest;
import com.linkwee.api.request.cim.OrginfoaPageListRequest;
import com.linkwee.api.response.cim.OrginfoaDetailResponse;
import com.linkwee.api.response.cim.OrginfoaPageListResponse;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.model.CimOrgInfoA;

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
public interface CimOrgInfoAMapper extends GenericDao<CimOrgInfoA,Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<CimOrgInfoA> selectBySearchInfo(@Param("dt")DataTable dt,RowBounds page);

	/**
	 * 查询A平台机构列表
	 * @param orginfoaPageListRequest
	 * @param page
	 * @return
	 */
	List<OrginfoaPageListResponse> queryPageList(OrginfoaPageListRequest orginfoaPageListRequest,Page<OrginfoaPageListResponse> page);

	/**
	 * 根据机构编码查询A机构详情
	 * @param orgNumber
	 * @return
	 */
	OrginfoaDetailResponse queryOrginfoaDetail(String orgNumber);

	/**
	 * 查询A机构列表
	 * @param orginfoaListRequest
	 * @return
	 */
	List<CimOrgInfoA> queryOrginfoaList(OrginfoaListRequest orginfoaListRequest);

	/**
	 * 查询A机构详情-灰度
	 * @param orginfoaDetailRequest
	 * @return
	 */
	OrginfoaDetailResponse queryOrginfoaDetailGray(OrginfoaDetailRequest orginfoaDetailRequest);

	 /**
	  * 机构列表
	  * @param orginfoaPageListRequest
	  * @param rowBounds
	  * @return
	  */
	List<String> queryPageListOrgNumber(OrginfoaPageListRequest orginfoaPageListRequest, RowBounds rowBounds);

	 /**
	  * 机构列表（带返现计算策略）
	  * @param pageListOrgNumber
	  * @return
	  */
	List<OrginfoaPageListResponse> queryPageListWithFee(List<String> pageListOrgNumber);
 }
