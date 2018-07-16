package com.linkwee.product.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.linkwee.core.base.BasePageDao;
import com.linkwee.web.model.product.ProCateSortResponse;
import com.linkwee.web.model.product.ProductInfoCate;

 /**
 * 
 * 描述： Dao接口
 * 
 * @创建人： chenchy
 * 
 * @创建时间：2016年05月26日 11:31:15
 * 
 * Copyright (c) 深圳市小牛科技有限公司-版权所有
 */
public interface ProductInfoCateDao extends BasePageDao<ProductInfoCate>{
	
	public List<ProCateSortResponse> findProCateSort(String productId);
	public ProductInfoCate findByProIdAndCateId(String productId,String cateId);
	public Integer updateByProId(ProductInfoCate proInfoCate);
	public void deleteByProIdAndCateId(String productId, String cateId);

	public ProductInfoCate findByCateIdAndSort(@Param("cateId") String cateId,@Param("sort")Integer sort);
	public Integer updateByCateIdAndSort(@Param("productId")String productId,@Param("cateId")String cateId,@Param("sort")Integer sort);
	public Integer deleteByProAndSortAndCate(@Param("productId")String productId,@Param("cateId")String cateId);
	
	
	public ProductInfoCate findByCateIdAndProId(@Param("cateId") String cateId,@Param("proId")String proId);
	public Integer  updateProCateSort(ProductInfoCate productInfoCate);

}
