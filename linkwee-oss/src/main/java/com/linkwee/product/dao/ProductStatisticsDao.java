package com.linkwee.product.dao;

import com.linkwee.core.base.BasePageDao;
import com.linkwee.web.model.product.ProductStatistics;

 /**
 * 
 * 描述： Dao接口
 * 
 * @创建人： chenchy
 * 
 * @创建时间：2016年05月31日 16:04:47
 * 
 * Copyright (c) 深圳市小牛科技有限公司-版权所有
 */
public interface ProductStatisticsDao extends BasePageDao<ProductStatistics>{
	
	public Double queryProRemaining(String productId);
	public ProductStatistics queryBuyedAmountByProId(String productId);

}
