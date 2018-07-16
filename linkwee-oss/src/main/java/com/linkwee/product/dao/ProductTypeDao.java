package com.linkwee.product.dao;

import com.linkwee.core.base.BasePageDao;
import com.linkwee.web.model.product.ProductType;

 /**
 * 
 * 描述： Dao接口
 * 
 * @创建人： chenchy
 * 
 * @创建时间：2016年06月20日 10:24:49
 * 
 * Copyright (c) 深圳市小牛科技有限公司-版权所有
 */
public interface ProductTypeDao extends BasePageDao<ProductType>{
	public Integer queryFloatTypeId();
	
	

}
