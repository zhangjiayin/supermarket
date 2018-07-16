package com.linkwee.product.dao;

import java.util.List;

import com.linkwee.core.base.BasePageDao;
import com.linkwee.web.model.product.ProductProtocal;

 /**
 * 
 * 描述： Dao接口
 * 
 * @创建人： chenchy
 * 
 * @创建时间：2016年05月26日 17:36:52
 * 
 * Copyright (c) 深圳市小牛科技有限公司-版权所有
 */
public interface ProductProtocalDao extends BasePageDao<ProductProtocal>{
	public List<ProductProtocal> findProtocals(); 
	public Integer addAndGetId(ProductProtocal productProtocal);
	public Integer findNullNameProtocal();
	
	

}
