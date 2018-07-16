package com.linkwee.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.model.ActAddFeeCoupon;
import com.linkwee.web.response.act.AddFeeCouponResponse;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2017年10月20日 17:11:30
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface ActAddFeeCouponMapper extends GenericDao<ActAddFeeCoupon,Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<ActAddFeeCoupon> selectBySearchInfo(@Param("dt")DataTable dt,RowBounds page);

	/**
	 * 加拥券列表
	 * @param page
	 * @return
	 */
	List<AddFeeCouponResponse> getAddFeeCouponList(RowBounds page);
}
