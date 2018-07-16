package com.linkwee.product.dao;

import com.linkwee.core.base.BasePageDao;
import com.linkwee.web.model.product.ProductSaleReward;

 /**
 * 
 * 描述： Dao接口	
 * 
 * @创建人： chenchy
 * 
 * @创建时间：2016年05月26日 17:17:45
 * 
 * Copyright (c) 深圳市小牛科技有限公司-版权所有
 */
public interface ProductSaleRewardDao extends BasePageDao<ProductSaleReward>{
	public ProductSaleReward findfeeRaioByProId(String productId);

	public void deleteByProId(String productId);
	

}
