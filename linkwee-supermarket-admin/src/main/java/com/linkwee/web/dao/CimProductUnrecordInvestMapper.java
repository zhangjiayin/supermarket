package com.linkwee.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.model.CimProductUnrecordInvest;
import com.linkwee.web.model.cim.CimProductDataTable;
import com.linkwee.web.response.tc.UnrecordInvestListResponse;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： ch
 * 
 * @创建时间：2016年09月09日 14:27:14
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CimProductUnrecordInvestMapper extends GenericDao<CimProductUnrecordInvest,Long>{
	
	List<UnrecordInvestListResponse> getUnrecordInvestList(@Param("investorsUserName")String investorsUserName,@Param("investorsMobiel")String investorsMobiel,@Param("startTime")String startTime,@Param("endTime")String endTime,@Param("status")Integer status,RowBounds page);
	
	/**
    * 封装DataTable对象查询
    * @param dt
    * @param page
    * @return
    */
	List<CimProductUnrecordInvest> selectBySearchInfo(@Param("dt")DataTable dt,RowBounds page);

	List<CimProductUnrecordInvest> selectBySearchInfoLogs(@Param("dt")CimProductDataTable dt, RowBounds page);

	List<CimProductUnrecordInvest> selectByHuiKuan();
}
