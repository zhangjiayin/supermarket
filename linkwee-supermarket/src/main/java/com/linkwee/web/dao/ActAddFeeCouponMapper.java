package com.linkwee.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.api.response.act.AddFeeCouponResponse;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.model.ActAddFeeCoupon;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2017年10月17日 18:42:26
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
	 * @param userId
	 * @param page
	 * @return
	 */
	List<AddFeeCouponResponse> pageList(@Param("userId")String userId,RowBounds page);

	/**
	 * 最新的加拥券
	 * @return
	 */
	ActAddFeeCoupon queryNewestAddFeeCoupon();

	/**
	 * 可使用的加拥券 couponType 1：加拥券  2：奖励券
	 * @param investDate
	 * @param couponType
	 * @return
	 */
	List<ActAddFeeCoupon> queryUseableAddFeeCoupon(@Param("investDate")String investDate,@Param("couponType")int couponType);

	/**
	 * 可使用的加拥券数量
	 * @return
	 */
	int queryAddFeeCouponCount();
}
