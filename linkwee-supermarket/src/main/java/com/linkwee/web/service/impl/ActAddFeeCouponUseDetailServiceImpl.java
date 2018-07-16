package com.linkwee.web.service.impl;

import java.util.List;
import java.lang.Long;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.model.ActAddFeeCouponUseDetail;
import com.linkwee.web.dao.ActAddFeeCouponUseDetailMapper;
import com.linkwee.web.service.ActAddFeeCouponUseDetailService;
import com.linkwee.web.service.impl.ActAddFeeCouponUseDetailServiceImpl;


 /**
 * 
 * @描述：ActAddFeeCouponUseDetailService 服务实现类
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2017年10月17日 18:42:45
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("actAddFeeCouponUseDetailService")
public class ActAddFeeCouponUseDetailServiceImpl extends GenericServiceImpl<ActAddFeeCouponUseDetail, Long> implements ActAddFeeCouponUseDetailService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ActAddFeeCouponUseDetailServiceImpl.class);
	
	@Resource
	private ActAddFeeCouponUseDetailMapper actAddFeeCouponUseDetailMapper;
	
	@Override
    public GenericDao<ActAddFeeCouponUseDetail, Long> getDao() {
        return actAddFeeCouponUseDetailMapper;
    }

	@Override
	public ActAddFeeCouponUseDetail queryNewestAddFee(String userId) {
		return actAddFeeCouponUseDetailMapper.queryNewestAddFee(userId);
	}

}
