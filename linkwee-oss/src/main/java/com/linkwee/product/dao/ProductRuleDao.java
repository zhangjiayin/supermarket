package com.linkwee.product.dao;

import java.util.List;

import com.linkwee.core.base.BasePageDao;
import com.linkwee.web.model.product.ProductRule;

 /**
 * 
 * 描述： Dao接口
 * 
 * @创建人： chenchy
 * 
 * @创建时间：2016年06月20日 10:18:50
 * 
 * Copyright (c) 深圳市小牛科技有限公司-版权所有
 */
public interface ProductRuleDao extends BasePageDao<ProductRule>{
	public List<ProductRule> queryModeDtlByTypeId(Integer productTypeId);

}
