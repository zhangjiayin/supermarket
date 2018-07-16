package com.linkwee.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.model.CimProduct;
import com.linkwee.web.request.ProductsListRequest;
import com.linkwee.web.request.ProductsSalesStatisticsRequest;
import com.linkwee.web.response.ProductDetailResponse;
import com.linkwee.web.response.ProductsListResponse;
import com.linkwee.web.response.ProductsSalesStatisticsResponse;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： liqi
 * 
 * @创建时间：2016年08月03日 10:03:21
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CimProductMapper extends GenericDao<CimProduct,Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<ProductsListResponse> selectBySearchInfo(@Param("query")ProductsListRequest productsListRequest,Page<ProductsListResponse> page);

	/**
	 * 根据产品id查询产品详情
	 * @param productId
	 * @return
	 */
	ProductDetailResponse queryProductDetail(String productId);

	/**
	 * 查询产品销售统计  为data-tables封装
	 * @param productsSalesStatisticsRequest
	 * @param page
	 * @return
	 */
	List<ProductsSalesStatisticsResponse> selectSalesStatisticsByDatatables(@Param("query")ProductsSalesStatisticsRequest productsSalesStatisticsRequest,Page<ProductsSalesStatisticsResponse> page);
}
