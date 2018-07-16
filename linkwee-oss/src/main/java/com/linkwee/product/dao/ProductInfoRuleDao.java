package com.linkwee.product.dao;

import java.util.List;
import java.util.Map;

import com.linkwee.core.base.BasePageDao;
import com.linkwee.web.model.product.ProductInfoRule;

 /**
 * 
 * 描述： Dao接口
 * 
 * @创建人： chenchy
 * 
 * @创建时间：2016年06月20日 10:29:59
 * 
 * Copyright (c) 深圳市小牛科技有限公司-版权所有
 */
public interface ProductInfoRuleDao extends BasePageDao<ProductInfoRule>{
	public List<Map<String,Object>> queryFloatRate(Integer productTypeId);
	public List<Map<String,Object>> queryFloatDays(Integer productTypeId);
	

}
