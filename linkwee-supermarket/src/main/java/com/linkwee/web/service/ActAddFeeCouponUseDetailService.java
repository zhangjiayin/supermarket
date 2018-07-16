package com.linkwee.web.service;

import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.ActAddFeeCouponUseDetail;
 /**
 * 
 * @描述： ActAddFeeCouponUseDetailService服务接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2017年10月17日 18:42:45
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface ActAddFeeCouponUseDetailService extends GenericService<ActAddFeeCouponUseDetail,Long>{

	/**
	 * 加拥券最新使用记录
	 * @param userId
	 * @return
	 */
	ActAddFeeCouponUseDetail queryNewestAddFee(String userId);
}
