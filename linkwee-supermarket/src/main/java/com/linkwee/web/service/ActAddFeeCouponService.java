package com.linkwee.web.service;

import java.util.Date;
import java.util.List;

import com.linkwee.api.response.act.AddFeeCouponResponse;
import com.linkwee.core.base.api.PaginatorRequest;
import com.linkwee.core.base.api.PaginatorResponse;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.ActAddFeeCoupon;
 /**
 * 
 * @描述： ActAddFeeCouponService服务接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2017年10月17日 18:42:26
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface ActAddFeeCouponService extends GenericService<ActAddFeeCoupon,Long>{

	/**
	 * 加拥券列表
	 * @param request
	 * @param userId
	 * @return
	 */
	PaginatorResponse<AddFeeCouponResponse> pageList(PaginatorRequest request, String userId);

	/**
	 * 最新的加拥券
	 * @return
	 */
	ActAddFeeCoupon queryNewestAddFeeCoupon();

	/**
	 * 可使用的加拥券 couponType 1：加拥券  2：奖励券
	 * @param investTime
	 * @param couponType
	 * @return
	 */
	List<ActAddFeeCoupon> queryUseableAddFeeCoupon(Date investTime,int couponType);

	/**
	 * 可使用的加拥券数量
	 * @return
	 */
	int queryAddFeeCouponCount();
}
