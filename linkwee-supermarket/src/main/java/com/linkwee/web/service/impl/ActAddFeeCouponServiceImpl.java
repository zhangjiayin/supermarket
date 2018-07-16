package com.linkwee.web.service.impl;

import java.util.Date;
import java.util.List;
import java.lang.Long;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.linkwee.api.response.act.AddFeeCouponResponse;
import com.linkwee.api.response.act.RedpacketResponse;
import com.linkwee.core.base.api.PaginatorRequest;
import com.linkwee.core.base.api.PaginatorResponse;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.core.util.DateUtils;
import com.linkwee.web.model.ActAddFeeCoupon;
import com.linkwee.web.dao.ActAddFeeCouponMapper;
import com.linkwee.web.service.ActAddFeeCouponService;
import com.linkwee.web.service.impl.ActAddFeeCouponServiceImpl;


 /**
 * 
 * @描述：ActAddFeeCouponService 服务实现类
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2017年10月17日 18:42:26
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("actAddFeeCouponService")
public class ActAddFeeCouponServiceImpl extends GenericServiceImpl<ActAddFeeCoupon, Long> implements ActAddFeeCouponService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ActAddFeeCouponServiceImpl.class);
	
	@Resource
	private ActAddFeeCouponMapper actAddFeeCouponMapper;
	
	@Override
    public GenericDao<ActAddFeeCoupon, Long> getDao() {
        return actAddFeeCouponMapper;
    }

	@Override
	public PaginatorResponse<AddFeeCouponResponse> pageList(PaginatorRequest request, String userId) {
		PaginatorResponse<AddFeeCouponResponse> paginatorResponse = new PaginatorResponse<AddFeeCouponResponse>();
		Page<AddFeeCouponResponse> page  = new Page<AddFeeCouponResponse>(request.getPageIndex(),request.getPageSize()); //默认每页10条
		List<AddFeeCouponResponse> addFeeCouponResponses = actAddFeeCouponMapper.pageList(userId, page);	
		paginatorResponse.setDatas(addFeeCouponResponses);
		paginatorResponse.setValuesByPage(page);
		return paginatorResponse;
	}

	@Override
	public ActAddFeeCoupon queryNewestAddFeeCoupon() {
		return actAddFeeCouponMapper.queryNewestAddFeeCoupon();
	}

	@Override
	public List<ActAddFeeCoupon> queryUseableAddFeeCoupon(Date investTime,int couponType) {
		String investDate = DateUtils.format(investTime, DateUtils.FORMAT_LONG);
		return actAddFeeCouponMapper.queryUseableAddFeeCoupon(investDate,couponType);
	}

	@Override
	public int queryAddFeeCouponCount() {
		return actAddFeeCouponMapper.queryAddFeeCouponCount();
	}

}
