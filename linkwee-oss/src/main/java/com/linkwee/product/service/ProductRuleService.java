package com.linkwee.product.service;

import java.util.List;

import com.linkwee.core.base.ServiceResponse;
import com.linkwee.web.model.product.ProductRule;

/**
 * 用户 业务 接口
 * 
 * @author Mignet
 * @since 2014年7月5日 上午11:53:33
 **/
public interface ProductRuleService {
	
	public ServiceResponse<String> addProfitModel(List<ProductRule> list,String productTypeName);
}
